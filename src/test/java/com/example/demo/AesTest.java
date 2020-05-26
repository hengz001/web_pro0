package com.example.demo;

import com.sun.crypto.provider.AESKeyGenerator;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.List;

public class AesTest {
    @Test
    public void testOne(){
//        byte key[] = {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,};
//        byte key[] = {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x22,0x22,0x22,0x22,0x22,0x22,0x22,0x22,};
//        byte key[] = {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x22,0x22,0x22,0x22,0x22,0x22,0x22,0x22,0x33,0x33,0x33,0x33,0x33,0x33,0x33,0x33};
        byte key[] = {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x22,0x22,0x22,0x22,0x22,0x22,0x22,0x22,0x33,0x33,0x33,0x33,0x33,0x33,0x33,0x33,0x44,0x44,0x44,0x44,0x44,0x44,0x44,0x44};
        byte iv[] = {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,};
        byte data[] = {0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,0x11,};
        try {
//            byte[] ret = ecb_enc_dec(data,key,1);
            byte[] ret = cbc_enc_dec(data,key,iv,1);
//            byte[] ret = cfb_enc_dec(data,key,iv,1);
//            byte[] ret = ofb_enc_dec(data,key,iv,1);


//            System.out.println(ret.length);
            for(int i=0; i<ret.length; i++){
                System.out.printf("%02X",ret[i]);
            }
            System.out.println();
//            String in = byte2hex(ret,ret.length);
//            System.out.println(in);
//            Byte[] bs = hex2byte(in,in.length());
//            for(int i=0; i<bs.length; i++){
//                System.out.printf("%02X",bs[i]);
//            }
//            System.out.println();

//            byte ciphertext[] = new byte[data.length];
//            for(int i=0; i<data.length; i++){
//                ciphertext[i] = ret[i];
//            }
//            byte[] ret2 = decrypt(ciphertext,key);
//            byte[] ret2 = ecb_enc_dec(ret,key,0);
            byte[] ret2 = cbc_enc_dec(ret,key,iv,0);
//            byte[] ret2 = cfb_enc_dec(ret,key,iv,0);
//            byte[] ret2 = ofb_enc_dec(ret,key,iv,0);

            for(int i=0; i<ret2.length; i++){
                System.out.printf("%02X",ret2[i]);
            }
            System.out.println();
//            String in2 = byte2hex(ret2,ret2.length);
//            System.out.println(in2);
//            Byte[] bs2 = hex2byte(in2,in2.length());
//            for(int i=0; i<bs2.length; i++){
//                System.out.printf("%02X",bs2[i]);
//            }
//            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(1);
    }

//    public byte[] enc_dec(byte data[],byte key[], byte iv[], int enc, String mode) throws Exception {
//        //key spec
//        DESKeySpec dks = new DESKeySpec(key);
//        //key factory
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//        SecretKey secretKey = keyFactory.generateSecret(dks);
//        //cipher
//        Cipher cipher = Cipher.getInstance("DES/"+mode+"/NoPadding");
//        cipher.init(enc==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey,new IvParameterSpec(iv));
//        return cipher.doFinal(data);
//    }
//    public SecretKey keyGenerateDES(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
//        //key spec
//        KeySpec dks;
//
//        if(key.length==8){
//            dks = new DESKeySpec(key);
//        }else if(key.length==16){
//            byte[] key2 = new byte[24];
//            for(int i=0; i<key2.length; i++){
//                key2[i] = i<16?(key[i]):(key2[i-16]);
//            }
//            dks = new DESedeKeySpec(key2);
//        }else{
//            dks = new DESedeKeySpec(key);
//        }
//        //key factory
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(key.length==8?("DES"):("DESede"));
//        SecretKey secretKey = keyFactory.generateSecret(dks);
//        return secretKey;
//    }

    public SecretKey keyGenerate(byte[] key,String keyAlgo) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        //key spec
        KeySpec dks;

        return new SecretKeySpec(key,keyAlgo);
        //key factory
//        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(keyAlgo);
//        SecretKey secretKey = keyFactory.generateSecret(dks);
//        return secretKey;
    }

    public SecretKey keyGenerateAES(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return keyGenerate(key,"AES");
    }

    public byte[] enc_dec(byte data[],byte key[],byte iv[], int enc, String mode) throws Exception {
        String algo = "";
        SecretKey secretKey = keyGenerateAES(key);
        //cipher
        algo = "AES/"+mode+"/NoPadding";
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

//    public byte[] enc_dec(byte data[],byte key[], int mode) throws Exception {
////        //random
//////        SecureRandom sr = new SecureRandom();
////        //key spec
////        DESKeySpec dks = new DESKeySpec(key);
////        //key factory
////        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
////        SecretKey secretKey = keyFactory.generateSecret(dks);
//        String algo_mode = "";
//        System.out.println("------------------------>");
//        SecretKey secretKey = keyGenerateDES(key);
//        System.out.println("------------------------>");
//        //cipher
//        if(key.length==8){
//            algo_mode = "DES/ECB/NoPadding";
//        }else{
//            algo_mode = "DESede/ECB/NoPadding";
//        }
//        Cipher cipher = Cipher.getInstance(algo_mode);
////        cipher.init(mode==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey,sr);
//        cipher.init(mode==1?(Cipher.ENCRYPT_MODE):(Cipher.DECRYPT_MODE),secretKey);
//        return cipher.doFinal(data);
//    }

//    public byte[] encrypt(byte data[],byte key[]) throws Exception {
//        return enc_dec(data,key,1);
//    }
//
//    public byte[] decrypt(byte data[],byte key[]) throws Exception {
//        return enc_dec(data,key,0);
//    }

//////////////////////////////////////////////////////
    public String byte2hex(byte[] in, int inl){
        StringBuffer sb = new StringBuffer();
        byte h,l;
        for(int i=0; i<inl; i++){
            h = (byte) ((in[i]&0xf0)>>4);
            l = (byte) (in[i]&0x0f);
            sb.append(h>9?(char)(h-10+'A'):(char)(h+'0'));
            sb.append(l>9?(char)(l-10+'A'):(char)(l+'0'));
        }
        return sb.toString();
    }
    public Byte[] hex2byte(String in, int inl){
        if(inl%2!=0) return null;
        in = in.toUpperCase();
//        byte[] out = new byte[inl/2];
        List<Byte> list = new ArrayList<Byte>();
        byte h = 0,l=0;
        char c;
        for(int i=0; i<inl; i++){
            c = in.charAt(i);
            if(i%2==0){
                h = c>'9'?((byte) (c-'A'+10)):((byte) (c-'0'));
            }else{
                l = c>'9'?((byte) (c-'A'+10)):((byte) (c-'0'));
//                out[i/2] = (byte) ((h<<4)|l);
                list.add((byte) ((h<<4)|l));
            }
        }
        return list.toArray(new Byte[inl/2]);
    }
}
