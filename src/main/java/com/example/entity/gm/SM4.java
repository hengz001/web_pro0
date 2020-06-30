package com.example.entity.gm;

import com.example.service.ToolService;
import com.example.service.impl.ToolServiceImpl;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class SM4 {

    public SM4() {
        if(Security.getProvider("BC")==null){
            Security.addProvider(new BouncyCastleProvider());
        }
    }
    final String SM4_ECB_NOPADDING = "SM4/ECB/NOPADDING";
    final String SM4_CBC_NOPADDING = "SM4/CBC/NOPADDING";

    public byte[] sm4Enc(String mode, int enc, byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key sm4Key = new SecretKeySpec(key,"SM4");
        Cipher cipher = Cipher.getInstance(mode,"BC");
        cipher.init(enc==1?Cipher.ENCRYPT_MODE:Cipher.DECRYPT_MODE,sm4Key);
        return cipher.doFinal(in);
    }

    public byte[] sm4EcbEnc(int enc, byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return sm4Enc(SM4_ECB_NOPADDING,enc,key,in);
    }

    public byte[] sm4EcbEncrypt(byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return sm4EcbEnc(Cipher.ENCRYPT_MODE,key,in);
    }
    public byte[] sm4EcbDecrypt(byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return sm4EcbEnc(Cipher.DECRYPT_MODE,key,in);
    }

    public byte[] sm4CbcEnc(int enc, byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return sm4Enc(SM4_CBC_NOPADDING,enc,key,in);
    }
    public byte[] sm4CbcEncrypt(byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return sm4CbcEnc(Cipher.ENCRYPT_MODE,key,in);
    }

    public byte[] sm4CbcDecrypt(byte[] key, byte in[]) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        return sm4CbcEnc(Cipher.DECRYPT_MODE,key,in);
    }

//    @Test
//    public void sm4Test() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
//        ToolService tool = new ToolServiceImpl();
//        byte key[] = "zhuheng0zhuheng0".getBytes();
//        byte in[] = "zhuheng0zhuheng0zhuheng0zhuheng0".getBytes();
//        Key sm4Key = new SecretKeySpec(key,"SM4");
//        String mode = "SM4/ECB/NOPADDING";
//        Cipher cipher = Cipher.getInstance(mode,"BC");
//        cipher.init(Cipher.ENCRYPT_MODE,sm4Key);
//        byte out[] = cipher.doFinal(in);
//        System.out.println(tool.byte2hex(out,out.length));
//
//        cipher.init(Cipher.DECRYPT_MODE,sm4Key);
//        out = cipher.doFinal(out);
//        System.out.println(tool.byte2hex(out,out.length));
//
//        byte iv[] = "zhuheng0zhuheng0".getBytes();
//        byte iv0[] = "zhuheng0zhuheng0".getBytes();
//        Cipher cbc = Cipher.getInstance("SM4/CBC/NOPADDING","BC");
//        cbc.init(Cipher.ENCRYPT_MODE, sm4Key,new IvParameterSpec(iv));
//        out = cbc.doFinal(in);
//        System.out.println(tool.byte2hex(out,out.length));
//
//        cbc.init(Cipher.DECRYPT_MODE, sm4Key,new IvParameterSpec(iv0));
//        out = cbc.doFinal(out);
//        System.out.println(tool.byte2hex(out,out.length));
//    }
}
