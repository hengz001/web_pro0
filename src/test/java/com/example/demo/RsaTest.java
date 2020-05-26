package com.example.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAMultiPrimePrivateCrtKeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;


public class RsaTest {
    @Test
    public void rsaTest() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        System.out.println(new String(Base64.encodeBase64(publicKey.getEncoded())));
        System.out.println(new String(Base64.encodeBase64(privateKey.getEncoded())));
        //encrypt
        String in = "zhuheng";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte out[] = cipher.doFinal(in.getBytes());
        System.out.println(Base64.encodeBase64String(out));

        //decrypt
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE,privateKey);
        byte out1[] = cipher1.doFinal(out);
        System.out.println(new String(out1));
    }

    @Test
    public void rsaTest2() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        //generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        System.out.println("public= " + new String(Base64.encodeBase64(publicKey.getEncoded())));
        System.out.println("private= " + new String(Base64.encodeBase64(privateKey.getEncoded())));
//        BigInteger big = privateKey.getPrivateExponent();
//        System.out.println("e=" + big.toString(16));
        RSAPrivateCrtKey pri = (RSAPrivateCrtKey) privateKey;
        System.out.println("n= "+pri.getModulus().toString(16));
        System.out.println("e= "+pri.getPublicExponent().toString(16));
        System.out.println("d= "+pri.getPrivateExponent().toString(16));
        System.out.println("p= "+pri.getPrimeP().toString(16));
        System.out.println("q= "+pri.getPrimeQ().toString(16));
        System.out.println("dp= "+pri.getPrimeExponentP().toString(16));
        System.out.println("dq= "+pri.getPrimeExponentQ().toString(16));
        System.out.println("qmp= "+pri.getCrtCoefficient().toString(16));

        RSAPrivateCrtKeySpec spec = new RSAPrivateCrtKeySpec(pri.getModulus(),pri.getPublicExponent(),pri.getPrivateExponent(),pri.getPrimeP(),pri.getPrimeQ(),pri.getPrimeExponentP(),pri.getPrimeExponentQ(),pri.getCrtCoefficient());
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPrivateKey priK = (RSAPrivateKey) factory.generatePrivate(spec);
        RSAPublicKeySpec spec1 = new RSAPublicKeySpec(pri.getModulus(),pri.getPublicExponent());
        KeyFactory factory1 = KeyFactory.getInstance("RSA");
        RSAPublicKey pubK = (RSAPublicKey) factory1.generatePublic(spec1);

        System.out.println(priK.equals(privateKey));
        System.out.println(pubK.equals(publicKey));

        //encrypt
        String in = "zhuheng";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte out[] = cipher.doFinal(in.getBytes());
        System.out.println(Base64.encodeBase64String(out));

        //decrypt
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE,priK);
        byte out1[] = cipher1.doFinal(out);
        System.out.println(new String(out1));
    }

    public void rsaCombination(Map<String,String> map) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
//        System.out.println("n= "+pri.getModulus().toString(16));
//        System.out.println("e= "+pri.getPublicExponent().toString(16));
//        System.out.println("d= "+pri.getPrivateExponent().toString(16));
//        System.out.println("p= "+pri.getPrimeP().toString(16));
//        System.out.println("q= "+pri.getPrimeQ().toString(16));
//        System.out.println("dp= "+pri.getPrimeExponentP().toString(16));
//        System.out.println("dq= "+pri.getPrimeExponentQ().toString(16));
//        System.out.println("qmp= "+pri.getCrtCoefficient().toString(16));
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
        RSAPrivateKey priK = (RSAPrivateKey) factory.generatePrivate(spec);
        RSAPublicKeySpec spec1 = new RSAPublicKeySpec(n,e);
        KeyFactory factory1 = KeyFactory.getInstance("RSA");
        RSAPublicKey pubK = (RSAPublicKey) factory1.generatePublic(spec1);

        //encrypt
        String in = "zhuheng";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubK);
        byte out[] = cipher.doFinal(in.getBytes());
        System.out.println(Base64.encodeBase64String(out));

        //decrypt
        Cipher cipher1 = Cipher.getInstance("RSA");
        cipher1.init(Cipher.DECRYPT_MODE,priK);
        byte out1[] = cipher1.doFinal(out);
        System.out.println(new String(out1));
    }


    @Test
    public void rsaGenerate() throws NoSuchAlgorithmException{
        int keyLen = 1024;

        //generate key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLen, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAPrivateCrtKey pri = (RSAPrivateCrtKey) privateKey;
        System.out.println("n= "+pri.getModulus().toString(16));
        System.out.println("e= "+pri.getPublicExponent().toString(16));
        System.out.println("d= "+pri.getPrivateExponent().toString(16));
        System.out.println("p= "+pri.getPrimeP().toString(16));
        System.out.println("q= "+pri.getPrimeQ().toString(16));
        System.out.println("dp= "+pri.getPrimeExponentP().toString(16));
        System.out.println("dq= "+pri.getPrimeExponentQ().toString(16));
        System.out.println("qmp= "+pri.getCrtCoefficient().toString(16));

        Map map = new HashMap<String,String>();
        map.put("n",pri.getModulus().toString(16));
        map.put("e",pri.getPublicExponent().toString(16));
        map.put("d",pri.getPrivateExponent().toString(16));
        map.put("p",pri.getPrimeP().toString(16));
        map.put("q",pri.getPrimeQ().toString(16));
        map.put("dp",pri.getPrimeExponentP().toString(16));
        map.put("dq",pri.getPrimeExponentQ().toString(16));
        map.put("qmp",pri.getCrtCoefficient().toString(16));
//        return map;
        try {
            rsaCombination(map);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

}
