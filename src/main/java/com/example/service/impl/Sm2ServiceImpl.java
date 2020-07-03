package com.example.service.impl;

import com.example.entity.gm.SM2;
import com.example.service.Sm2Service;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.springframework.stereotype.Service;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@Service("sm2Service")
public class Sm2ServiceImpl implements Sm2Service{
    SM2 sm2;
    {
        try {
            sm2 = new SM2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSm2PrivateKeySz(byte[] pri){
        return sm2.getSm2PrivateKeySz(pri);
    }

    public PrivateKey setSm2PrivateKeySz(String privateKey, String publicKey) throws InvalidKeySpecException {
        return sm2.setSm2PrivateKeySz(privateKey,publicKey);
    }

    public String getSm2PublicKeySz(byte[] pub){
        return sm2.getSm2PublicKeySz(pub);
    }

    public PublicKey setSm2PublicKeySz(String publicKey) throws InvalidKeySpecException {
        return sm2.setSm2PublicKeySz(publicKey);
    }

    public Map<String,String> sm2Gen() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        return sm2.sm2Gen();
    }

    public byte[] sm2Encrypt(PublicKey pub, byte[] in) throws InvalidCipherTextException {
        return sm2.sm2Encrypt( pub,  in);
    }

    public byte[] sm2Decrypt(PrivateKey pri, byte[] in) throws InvalidCipherTextException {
        return sm2.sm2Decrypt(pri, in);
    }

    public byte[] sm2Sign(byte []msg, byte[] userId, PrivateKey pri) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        return sm2.sm2Sign(msg, userId, pri);
    }

    public boolean sm2Verify(byte[] sign, byte []msg, byte[] userId, PublicKey pub) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        return sm2.sm2Verify(sign, msg, userId, pub);
    }
}
