package com.example.entity.gm;

import com.example.service.ToolService;
import com.example.service.impl.ToolServiceImpl;
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
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;

public class SM2 {
    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
    private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
    private static ECGenParameterSpec sm2spec = new ECGenParameterSpec("sm2p256v1");
    private static KeyFactory keyFactory;

    public SM2() throws Exception {
        if(Security.getProvider("BC")==null){
            Security.addProvider(new BouncyCastleProvider());
        }
        keyFactory = KeyFactory.getInstance("EC", "BC");
    }


    ToolService tool = new ToolServiceImpl();
    //sm2 der private key = first + pri_key + middle + pub_key
    final String SZ_PRI_FIRST = "308193020100301306072A8648CE3D020106082A811CCF5501822D047930770201010420";
    final String SZ_PRI_MIDDLE = "A00A06082A811CCF5501822DA14403420004";
    //sm2 der public key = first + pub_key
    final String SZ_PUB_FIRST = "3059301306072A8648CE3D020106082A811CCF5501822D03420004";

    final byte[] PRI_FIRST = tool.hex2byte(SZ_PRI_FIRST);
    final byte[] PRI_MIDDLE = tool.hex2byte(SZ_PRI_MIDDLE);
    final byte[] PUB_FIRST = tool.hex2byte(SZ_PUB_FIRST);

    final int PRI_FIRST_LEN = PRI_FIRST.length;
    final int PRI_MIDDLE_LEN = PRI_MIDDLE.length;
    final int PUB_FIRST_LEN = PUB_FIRST.length;

    final int PRI_KEY_LEN = 64/2;
    final int PUB_KEY_LEN = 128/2;

    final int DER_PRI_KEY_LEN = PRI_FIRST_LEN + PRI_KEY_LEN + PRI_MIDDLE_LEN + PUB_KEY_LEN;
    final int DER_PUB_KEY_LEN = PUB_FIRST_LEN + PUB_KEY_LEN;

    final String PRI_KEY = "PRI_KEY";
    final String PUB_KEY = "PUB_KEY";

//    public byte[] getSm2PrivateKey(byte[] pri){
//        byte[] privateKey = new byte[PRI_KEY_LEN];
//        System.arraycopy(pri,PRI_FIRST_LEN,privateKey,0,PRI_KEY_LEN);
//        return privateKey;
//    }

    public String getSm2PrivateKeySz(byte[] pri){
        byte[] privateKey = new byte[PRI_KEY_LEN];
        System.arraycopy(pri,PRI_FIRST_LEN,privateKey,0,PRI_KEY_LEN);
        return tool.byte2hex(privateKey);
    }

//    public byte[] setSm2PrivateKey(byte[] privateKey, byte[] publicKey){
//        byte[] pri = new byte[DER_PRI_KEY_LEN];
//        System.arraycopy(PRI_FIRST,0,pri,0,PRI_FIRST_LEN);
//        System.arraycopy(privateKey,0,pri,0+PRI_FIRST_LEN,PRI_KEY_LEN);
//        System.arraycopy(PRI_MIDDLE,0,pri,0+PRI_FIRST_LEN + PRI_KEY_LEN,PRI_MIDDLE_LEN);
//        System.arraycopy(publicKey,0,pri,0+PRI_FIRST_LEN + PRI_KEY_LEN + PRI_MIDDLE_LEN,PUB_KEY_LEN);
//        return pri;
//    }

    public PrivateKey setSm2PrivateKeySz(String szPrivateKey, String szPublicKey) throws InvalidKeySpecException {
        byte[] pri = new byte[DER_PRI_KEY_LEN];
        byte[] privateKey = tool.hex2byte(szPrivateKey);
        byte[] publicKey = tool.hex2byte(szPublicKey);

        System.arraycopy(PRI_FIRST,0,pri,0,PRI_FIRST_LEN);
        System.arraycopy(privateKey,0,pri,PRI_FIRST_LEN,PRI_KEY_LEN);
        System.arraycopy(PRI_MIDDLE,0,pri,PRI_FIRST_LEN + PRI_KEY_LEN,PRI_MIDDLE_LEN);
        System.arraycopy(publicKey,0,pri,PRI_FIRST_LEN + PRI_KEY_LEN + PRI_MIDDLE_LEN,PUB_KEY_LEN);
        EncodedKeySpec spec = new PKCS8EncodedKeySpec(pri);
        return keyFactory.generatePrivate(spec);
    }

//    public byte[] getSm2PublicKey(byte[] pub){
//        byte[] publicKey = new byte[PUB_KEY_LEN];
//        System.arraycopy(pub,pub.length-PUB_KEY_LEN, publicKey,0, PUB_KEY_LEN);
//        return publicKey;
//    }

    public String getSm2PublicKeySz(byte[] pub){
        byte[] publicKey = new byte[PUB_KEY_LEN];
        System.arraycopy(pub,pub.length-PUB_KEY_LEN, publicKey,0, PUB_KEY_LEN);
        return tool.byte2hex(publicKey);
    }

//    public byte[] setSm2PublicKey(byte[] publicKey){
//        byte[] pub = new byte[DER_PUB_KEY_LEN];
//        System.arraycopy(PUB_FIRST,0,pub,0, PUB_FIRST_LEN);
//        System.arraycopy(publicKey,0,pub, PUB_FIRST_LEN,PUB_KEY_LEN);
//        return pub;
//    }

    public PublicKey setSm2PublicKeySz(String szPublicKey) throws InvalidKeySpecException {
        byte[] pub = new byte[DER_PUB_KEY_LEN];
        byte[]publicKey = tool.hex2byte(szPublicKey);

        System.arraycopy(PUB_FIRST,0,pub,0, PUB_FIRST_LEN);
        System.arraycopy(publicKey,0,pub, PUB_FIRST_LEN,PUB_KEY_LEN);
        EncodedKeySpec spec = new X509EncodedKeySpec(pub);
        return keyFactory.generatePublic(spec);
    }

    public Map<String,String> sm2Gen() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        KeyPair keyPair = sm2Generate();
        PrivateKey pri = keyPair.getPrivate();
        PublicKey pub = keyPair.getPublic();
        Map map = new HashMap();
        map.put(PRI_KEY,getSm2PrivateKeySz(pri.getEncoded()));
        map.put(PUB_KEY,getSm2PublicKeySz(pub.getEncoded()));

//        System.out.println(tool.byte2hex(pri.getEncoded()));
//        System.out.println(tool.byte2hex(pub.getEncoded()));
//        System.out.println(map.get("PRI_KEY"));
//        System.out.println(map.get("PUB_KEY"));

        return map;
    }

    public KeyPair sm2Generate() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC","BC");
        keyPairGenerator.initialize(sm2spec);
        return keyPairGenerator.generateKeyPair();
    }



    public byte[] sm2Encrypt(PublicKey pub, byte[] in) throws InvalidCipherTextException {
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(((BCECPublicKey)pub).getQ(),ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters,new SecureRandom()));
        return sm2Engine.processBlock(in,0,in.length);
    }

    public byte[] sm2Decrypt(PrivateKey pri, byte[] in) throws InvalidCipherTextException {
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(((BCECPrivateKey)pri).getD(),ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, ecPrivateKeyParameters);
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
