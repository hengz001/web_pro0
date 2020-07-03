package com.example.service;

import org.bouncycastle.crypto.InvalidCipherTextException;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface Sm2Service {
    String getSm2PrivateKeySz(byte[] pri);

    PrivateKey setSm2PrivateKeySz(String privateKey, String publicKey) throws InvalidKeySpecException ;

    String getSm2PublicKeySz(byte[] pub);

    PublicKey setSm2PublicKeySz(String publicKey) throws InvalidKeySpecException ;

    Map<String,String> sm2Gen() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException ;

    byte[] sm2Encrypt(PublicKey pub, byte[] in) throws InvalidCipherTextException ;

    byte[] sm2Decrypt(PrivateKey pri, byte[] in) throws InvalidCipherTextException ;

    byte[] sm2Sign(byte []msg, byte[] userId, PrivateKey pri) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException ;

    boolean sm2Verify(byte[] sign, byte []msg, byte[] userId, PublicKey pub) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException ;

}
