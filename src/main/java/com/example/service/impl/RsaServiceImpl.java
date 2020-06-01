package com.example.service.impl;


import com.example.service.RsaService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

@Service("rsaService")
public class RsaServiceImpl implements RsaService {

    //20200525zhuheng key generate
    public KeyPair rsaGenerate(int keyLen) throws NoSuchAlgorithmException{
        //generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLen, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    //20200525zhuheng key split
    public Map<String,String> rsaSplit(KeyPair keyPair){
        RSAPrivateCrtKey pri = (RSAPrivateCrtKey) keyPair.getPrivate();
        Map map = new HashMap<String,String>();
//        map.put("n",pri.getModulus().toString(16));
//        map.put("e",pri.getPublicExponent().toString(16));
//        map.put("d",pri.getPrivateExponent().toString(16));
//        map.put("p",pri.getPrimeP().toString(16));
//        map.put("q",pri.getPrimeQ().toString(16));
//        map.put("dp",pri.getPrimeExponentP().toString(16));
//        map.put("dq",pri.getPrimeExponentQ().toString(16));
//        map.put("qmp",pri.getCrtCoefficient().toString(16));

        map.put("n",pri.getModulus().toString(16).toUpperCase());
        map.put("e",pri.getPublicExponent().toString(16).toUpperCase());
        map.put("d",pri.getPrivateExponent().toString(16).toUpperCase());
        map.put("p",pri.getPrimeP().toString(16).toUpperCase());
        map.put("q",pri.getPrimeQ().toString(16).toUpperCase());
        map.put("dp",pri.getPrimeExponentP().toString(16).toUpperCase());
        map.put("dq",pri.getPrimeExponentQ().toString(16).toUpperCase());
        map.put("qmp",pri.getCrtCoefficient().toString(16).toUpperCase());
        return map;
    }

    //20200525zhuheng key combination
    public Map<String, RSAKey> rsaCombination(Map<String, String> map) throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger n = new BigInteger((String)map.get("n"),16);
        BigInteger e = new BigInteger((String)map.get("e"),16);
        BigInteger d = new BigInteger((String)map.get("d"),16);
        BigInteger p = new BigInteger((String)map.get("p"),16);
        BigInteger q = new BigInteger((String)map.get("q"),16);
        BigInteger dp = new BigInteger((String)map.get("dp"),16);
        BigInteger dq = new BigInteger((String)map.get("dq"),16);
        BigInteger qmp = new BigInteger((String)map.get("qmp"),16);

        RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(n,e,d,p,q,dp,dq,qmp);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(spec);
        RSAPublicKeySpec spec1 = new RSAPublicKeySpec(n,e);
        KeyFactory factory1 = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) factory1.generatePublic(spec1);

        Map<String, RSAKey> keyMap = new HashMap();
        keyMap.put("privateKey",privateKey);
        keyMap.put("publicKey",publicKey);
        return keyMap;
    }

    //20200525zhuheng key combination
    public Map<String, RSAKey> rsaCombination(String n0, String e0,String d0,String p0,String q0,
                                              String dp0,String dq0,String qmp0) throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger n = new BigInteger(n0,16);
        BigInteger e = new BigInteger(e0,16);
        BigInteger d = new BigInteger(d0,16);
        BigInteger p = new BigInteger(p0,16);
        BigInteger q = new BigInteger(q0,16);
        BigInteger dp = new BigInteger(dp0,16);
        BigInteger dq = new BigInteger(dq0,16);
        BigInteger qmp = new BigInteger(qmp0,16);

        RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(n,e,d,p,q,dp,dq,qmp);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(spec);
        RSAPublicKeySpec spec1 = new RSAPublicKeySpec(n,e);
        KeyFactory factory1 = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey) factory1.generatePublic(spec1);

        Map<String, RSAKey> keyMap = new HashMap();
        keyMap.put("privateKey",privateKey);
        keyMap.put("publicKey",publicKey);
        return keyMap;
    }


    //20200525zhuheng rsa encrypt decrypt 1 encrypt 0 decrypt
    public byte[] rsaEncryptDecrypt(RSAKey key, int mode, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(mode == Cipher.ENCRYPT_MODE? Cipher.ENCRYPT_MODE: Cipher.DECRYPT_MODE, (Key)key);
        return cipher.doFinal(in);
    }

    //20200601zhuheng
    public byte[] rsaSign(RSAKey key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return rsaEncryptDecrypt(key,Cipher.ENCRYPT_MODE,in);
    }
    public boolean rsaVerify(RSAKey key, byte data[], byte sign[]) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        boolean bn =true;
        byte out[] = rsaEncryptDecrypt(key,Cipher.DECRYPT_MODE,sign);
       //compare
        if(data.length != out.length){
            bn = false;
//            System.out.println("长度不一致: "+(data.length-out.length));
        }else{
            for(int i=0; i<data.length; i++){
                if(data[i] != out[i]){
//                    System.out.println("数据不一致: "+i);
                    bn = false;
                    break;
                }
            }
        }
        return bn;
    }
    
//    @Test
//    public void testRsa() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
//        KeyPair keyPair = rsaGenerate(4096);
//        byte[] ciphertext = rsaEncryptDecrypt((RSAKey) keyPair.getPublic(),1,"zhuheng".getBytes());
//        System.out.println(Base64.encodeBase64String(ciphertext));
//        byte[] plaintext = rsaEncryptDecrypt((RSAKey) keyPair.getPrivate(),0,ciphertext);
//        System.out.println(new String(plaintext));
//    }

}
