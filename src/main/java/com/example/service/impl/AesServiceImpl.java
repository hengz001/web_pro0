package com.example.service.impl;

import com.example.service.AesService;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service("aesService")
public class AesServiceImpl implements AesService {
    public SecretKey keyGenerateAES(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(key,"AES");
    }

    public byte[] enc_dec(byte data[],byte key[],byte iv[], int enc, String mode) throws Exception {
        String algo = "AES/"+mode+"/NoPadding";
        SecretKey secretKey = keyGenerateAES(key);
        //cipher
        Cipher cipher = Cipher.getInstance(algo);

        if(mode.equals("ECB")){
            cipher.init(enc==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey);
        }else{
            cipher.init(enc==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey,new IvParameterSpec(iv));
        }
        return cipher.doFinal(data);
    }

    public byte[] ecb_enc_dec_aes(byte data[],byte key[], int enc) throws Exception {
        return enc_dec(data,key,null, enc,"ECB");
    }

    public byte[] cbc_enc_dec_aes(byte data[],byte key[], byte iv[], int enc) throws Exception {
        return enc_dec(data,key,iv,enc,"CBC");
    }

    public byte[] cfb_enc_dec_aes(byte data[],byte key[], byte iv[], int enc) throws Exception {
        return enc_dec(data,key,iv,enc,"CFB");
    }

    public byte[] ofb_enc_dec_aes(byte data[],byte key[], byte iv[], int enc) throws Exception {
        return enc_dec(data,key,iv,enc,"OFB");
    }

}
