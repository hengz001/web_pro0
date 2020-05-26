package com.example.service;

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

public interface RsaService {

    //20200525zhuheng key generate
    KeyPair rsaGenerate(int keyLen) throws NoSuchAlgorithmException ;

    //20200525zhuheng key split
    Map<String,String> rsaSplit(KeyPair keyPair);

    //20200525zhuheng key combination
    Map<String, RSAKey> rsaCombination(Map<String,String> map) throws NoSuchAlgorithmException, InvalidKeySpecException ;
    Map<String, RSAKey> rsaCombination(String n0, String e0,String d0,String p0,String q0,String dp0,String dq0,String qmp0) throws NoSuchAlgorithmException, InvalidKeySpecException;

    //20200525zhuheng rsa encrypt decrypt 1 encrypt 0 decrypt
    byte[] rsaEncryptDecrypt(RSAKey key, int mode,byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException ;

}
