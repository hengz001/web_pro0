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
import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SM2Test {
    ToolService tool = new ToolServiceImpl();

    @Test
    public void sm2Test() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, SignatureException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
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
        System.out.println(tool.byte2hex(privateKey,privateKey.length));
        System.out.println(tool.byte2hex(publicKey,publicKey.length));

//        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), new BouncyCastleProvider());
//        Signature signature = Signature.getInstance("NONEWITHECDSA", new BouncyCastleProvider());
//
//        byte[] plaintext = "hello world".getBytes();
//        signature.initSign(pri);
//        signature.update(plaintext);
//        byte[] signatureValue = signature.sign();
//        System.out.println(tool.byte2hex(signatureValue,signatureValue.length));
//
//        signature.initVerify(pub);
//        signature.update(plaintext);
//        boolean bn = signature.verify(signatureValue);
//        System.out.println(bn);
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
