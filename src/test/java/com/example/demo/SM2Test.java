package com.example.demo;

import com.example.service.ToolService;
import com.example.service.impl.ToolServiceImpl;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCurve;
import org.junit.Test;

import javax.crypto.*;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SM2Test {
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

    public byte[] getSm2PrivateKey(byte[] pri){
        byte[] privateKey = new byte[PRI_KEY_LEN];
        System.arraycopy(pri,PRI_FIRST_LEN,privateKey,0,PRI_KEY_LEN);
        return privateKey;
    }

    public byte[] setSm2PrivateKey(byte[] privateKey, byte[] publickKey){
        byte[] pri = new byte[DER_PRI_KEY_LEN];
        System.arraycopy(PRI_FIRST,0,pri,0,PRI_FIRST_LEN);
        System.arraycopy(privateKey,0,pri,0+PRI_FIRST_LEN,PRI_KEY_LEN);
        System.arraycopy(PRI_MIDDLE,0,pri,0+PRI_FIRST_LEN + PRI_KEY_LEN,PRI_MIDDLE_LEN);
        System.arraycopy(publickKey,0,pri,0+PRI_FIRST_LEN + PRI_KEY_LEN + PRI_MIDDLE_LEN,PUB_KEY_LEN);
        return pri;
    }

    public byte[] getSm2PublicKey(byte[] pub){
        byte[] publicKey = new byte[PUB_KEY_LEN];
        System.arraycopy(pub,pub.length-PUB_KEY_LEN, publicKey,0, PUB_KEY_LEN);
        return publicKey;
    }

    public byte[] setSm2PublicKey(byte[] publicKey){
        byte[] pub = new byte[DER_PUB_KEY_LEN];
        System.arraycopy(PUB_FIRST,0,pub,0, PUB_FIRST_LEN);
        System.arraycopy(publicKey,0,pub, PUB_FIRST_LEN,PUB_KEY_LEN);
        return pub;
    }


    @Test
    public void sm2keyGen() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, SignatureException, InvalidAlgorithmParameterException, InvalidKeySpecException, InvalidCipherTextException {
//        for(int i=0; i<10; i++){
//            sm2Test();
//        }
        Security.addProvider(new BouncyCastleProvider());
        Set<String> set = Security.getAlgorithms("Signature");
        set.forEach(x->System.out.println(x));
    }

    @Test
    public void sm2Test() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, SignatureException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException, InvalidCipherTextException {
        //获取SM2椭圆曲线得参数
        ECGenParameterSpec sm2spec = new ECGenParameterSpec("sm2p256v1");
        //初始化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        keyPairGenerator.initialize(sm2spec);
//        keyPairGenerator.initialize(sm2spec, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey pri = keyPair.getPrivate();
        PublicKey pub = keyPair.getPublic();

        byte[] privateKey = pri.getEncoded();
        byte[] publicKey = pub.getEncoded();
        System.out.println("private key= "+tool.byte2hex(privateKey,privateKey.length));
        System.out.println("public key = "+tool.byte2hex(publicKey,publicKey.length));

//        System.out.println(PRI_FIRST.equals(tool.byte2hex(privateKey,PRI_FIRST.length)));
//        System.out.println(PRI_MIDDLE.equals(tool.byte2hex(privateKey,(PRI_FIRST.length) + 32,PRI_MIDDLE.length)));
////        System.out.println(tool.byte2hex(privateKey,(PRI_FIRST.length()/2) + 32,PRI_MIDDLE.length()/2));
//        System.out.println(PUB_FIRST.equals(tool.byte2hex(publicKey,PUB_FIRST.length)));

        ///////
        byte[] pri0 = getSm2PrivateKey(privateKey);
        byte[] pub0 = getSm2PublicKey(publicKey);
        byte[] pri1 = setSm2PrivateKey(pri0,pub0);
        byte[] pub1 = setSm2PublicKey(pub0);

        System.out.println(tool.byte2hex(pri1).equals(tool.byte2hex(privateKey)));
        System.out.println(tool.byte2hex(pub1).equals(tool.byte2hex(publicKey)));

        EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
        PublicKey pubk = keyFactory.generatePublic(spec);
        System.out.println(tool.byte2hex(pubk.getEncoded()).equals(tool.byte2hex(publicKey)));

        spec = new PKCS8EncodedKeySpec(privateKey);
        keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
        PrivateKey prik = keyFactory.generatePrivate(spec);
        System.out.println(tool.byte2hex(prik.getEncoded()).equals(tool.byte2hex(privateKey)));
//        System.out.println(tool.byte2hex(pri1));
//        System.out.println(tool.byte2hex(pub1));
//

        //encrypt
        byte[] in = {0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
        BCECPublicKey localECPublicKey = (BCECPublicKey) pubk;
        X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(),ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true,new ParametersWithRandom(ecPublicKeyParameters,new SecureRandom()));
        byte out[] = sm2Engine.processBlock(in,0,in.length);
        System.out.println(tool.byte2hex(out,out.length));

        //decrypt
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) prik;
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(localECPrivateKey.getD(),ecDomainParameters);
        sm2Engine.init(false,ecPrivateKeyParameters);
        byte[] out2 = sm2Engine.processBlock(out,0,out.length);
        System.out.println(tool.byte2hex(out2,out2.length));


        //        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), new BouncyCastleProvider());
        Signature signature = Signature.getInstance("NONEWITHECDSA", new BouncyCastleProvider());

        byte[] plaintext = "hello world".getBytes();
        signature.initSign(pri);
        signature.update(plaintext);
        byte[] signatureValue = signature.sign();
        System.out.println(tool.byte2hex(signatureValue,signatureValue.length));

        signature.initVerify(pub);
        signature.update(plaintext);
        boolean bn = signature.verify(signatureValue);
        System.out.println(bn);
//
//        //
//        Security.addProvider( new BouncyCastleProvider());
////        Set<String> set = Security.getAlgorithms("BouncyCastleProvider");
////        Set<String> set = Security.getAlgorithms("Signature");
//        Set<String> set = Security.getAlgorithms("Cipher");
////        Set<String> set = Security.getAlgorithms("KeyPairGenerator");
////        for(String s : set){
////            System.out.println(s);
//        }
    }

    public static final String PRIVATE_KEY= "privateKey";
    public static final String PUBLIC_KEY= "publicKey";
    public Map sm2Generate() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        //获取SM2椭圆曲线得参数
        ECGenParameterSpec sm2spec = new ECGenParameterSpec("sm2p256v1");
        //初始化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        keyPairGenerator.initialize(sm2spec);
//        keyPairGenerator.initialize(sm2spec, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey pri = keyPair.getPrivate();
        PublicKey pub = keyPair.getPublic();
        Map map = new HashMap();
        map.put(PRIVATE_KEY,pri);
        map.put(PUBLIC_KEY,pub);
//        System.out.println(((BCECPrivateKey)pri).getD());
//        System.out.println(((BCECPublicKey)pub).getQ());
        return map;
    }

    @Test
    public void sm2Test2() throws NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidCipherTextException {
        //add provider
        Security.addProvider(new BouncyCastleProvider());
//        Provider[] s = Security.getProviders();
//        for(int i=0; i<s.length; i++){
//            System.out.println(s[i].getName());
//        }
        Map map = sm2Generate();
        PrivateKey pri = (PrivateKey) map.get(PRIVATE_KEY);
        PublicKey pub = (PublicKey) map.get(PUBLIC_KEY);
//        //signature
//        byte[] userId = {0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
//        byte[] msg = {0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
//        SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
////        Signature signature = Signature.getInstance("SM3WITHSM2","BC");
//        Signature signature = Signature.getInstance("SM3WITHSM2","BC");
//        //
//        signature.setParameter(parameterSpec);
//        signature.initSign(pri);
//        signature.update(msg,0,msg.length);
//        byte sign[] = signature.sign();
//        System.out.println(tool.byte2hex(sign,sign.length));
//        //
//        signature.initVerify(pub);
//        signature.update(msg,0,msg.length);
//        boolean bn = signature.verify(sign);
//        System.out.println("verify: "+bn);

        //encrypt
        byte[] in = {0x01,0x01,0x01,0x01,0x01,0x01,0x01,0x01};
        BCECPublicKey localECPublicKey = (BCECPublicKey) pub;
        X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN());
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(),ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true,new ParametersWithRandom(ecPublicKeyParameters,new SecureRandom()));
        byte out[] = sm2Engine.processBlock(in,0,in.length);
        System.out.println(tool.byte2hex(out,out.length));

        //decrypt
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) pri;
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(localECPrivateKey.getD(),ecDomainParameters);
        sm2Engine.init(false,ecPrivateKeyParameters);
        byte[] out2 = sm2Engine.processBlock(out,0,out.length);
        System.out.println(tool.byte2hex(out2,out2.length));
    }
}
