package com.example.entity.gm;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.security.*;

public class SM2 {
    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
    private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
    private static ECParameterSpec ecParameterSpec = new ECParameterSpec(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());

    public SM2() {
        if(Security.getProvider("BC")==null){
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public KeyPair sm2Generate() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC","BC");
        keyPairGenerator.initialize(ecParameterSpec,new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] sm2Encrypt(BCECPublicKey pub, byte[] in) throws InvalidCipherTextException {
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(pub.getQ(),ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters,new SecureRandom()));
        return sm2Engine.processBlock(in,0,in.length);
    }

    public byte[] sm2Decrypt(ECPrivateKeyParameters pri, byte[] in) throws InvalidCipherTextException {
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(pri.getD(),ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, new ParametersWithRandom(ecPrivateKeyParameters,new SecureRandom()));
        return sm2Engine.processBlock(in,0,in.length);
    }

    final String SIGNATURE_MODE = "SM3WithSM2";
    public byte[] sm2Sign(byte []msg, byte[] userId, PrivateKey pri) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        SM2ParameterSpec sm2ParameterSpec = new SM2ParameterSpec(userId);
        Signature signature = Signature.getInstance(SIGNATURE_MODE,"BC");
        signature.setParameter(sm2ParameterSpec);
        signature.initSign(pri,new SecureRandom());
        signature.update(msg,0,msg.length);
        return signature.sign();
    }

    public boolean sm2Verify(byte[] sign, byte []msg, byte[] userId, PublicKey pub) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        SM2ParameterSpec sm2ParameterSpec = new SM2ParameterSpec(userId);
        Signature signature = Signature.getInstance(SIGNATURE_MODE, "BC");
        signature.setParameter(sm2ParameterSpec);
        signature.initVerify(pub);
        signature.update(msg,0,msg.length);
        return signature.verify(sign);
    }
}
