package com.example.service.impl;

import com.example.entity.gm.SM4;
import com.example.service.DesService;
import com.example.service.Sm4Service;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Service("sm4Service")
public class Sm4ServiceImpl implements Sm4Service{
    SM4 sm4 = new SM4();

    public byte[] ecb_enc_dec(byte[] data, byte[] key, int enc) throws Exception {
        return sm4.sm4EcbEnc(enc,key,data);
    }

    public byte[] cbc_enc_dec(byte[] data, byte[] key, byte[] iv, int enc) throws Exception {
        return sm4.sm4CbcEnc(enc,key,data,iv);
    }
}
