package com.example.service.impl;

import com.example.service.DesService;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Service("desService")
public class DesServiceImpl implements DesService {

    public SecretKey keyGenerateDES(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        //key spec
        KeySpec dks;

        if(key.length==8){
            dks = new DESKeySpec(key);
        }else if(key.length==16){
            byte[] key2 = new byte[24];
            for(int i=0; i<key2.length; i++){
                key2[i] = i<16?(key[i]):(key2[i-16]);
            }
            dks = new DESedeKeySpec(key2);
        }else{
            dks = new DESedeKeySpec(key);
        }
        //key factory
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(key.length==8?("DES"):("DESede"));
        SecretKey secretKey = keyFactory.generateSecret(dks);
        return secretKey;
    }

    public byte[] enc_dec(byte data[],byte key[],byte iv[], int enc, String mode) throws Exception {
        String algo = "";
        SecretKey secretKey = keyGenerateDES(key);
        //cipher
        if(key.length==8){
            algo = "DES/"+mode+"/NoPadding";
        }else{
            algo = "DESede/"+mode+"/NoPadding";
        }
        Cipher cipher = Cipher.getInstance(algo);
        if(mode.equals("ECB")){
            cipher.init(enc==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey);
        }else{
            cipher.init(enc==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey,new IvParameterSpec(iv));
        }
        return cipher.doFinal(data);
    }

    public byte[] ecb_enc_dec(byte data[],byte key[], int enc) throws Exception {
        return enc_dec(data,key,null, enc,"ECB");
    }

    public byte[] cbc_enc_dec(byte data[],byte key[], byte iv[], int enc) throws Exception {
        return enc_dec(data,key,iv,enc,"CBC");
    }

    public byte[] cfb_enc_dec(byte data[],byte key[], byte iv[], int enc) throws Exception {
        return enc_dec(data,key,iv,enc,"CFB");
    }

    public byte[] ofb_enc_dec(byte data[],byte key[], byte iv[], int enc) throws Exception {
        return enc_dec(data,key,iv,enc,"OFB");
    }

}
