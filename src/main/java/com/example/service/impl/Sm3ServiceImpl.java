package com.example.service.impl;

import com.example.entity.gm.SM3;
import com.example.service.Sm3Service;
import org.springframework.stereotype.Service;

@Service("sm3Service")
public class Sm3ServiceImpl implements Sm3Service {
    private SM3 sm3 = new SM3();

    public byte[] sm3Hash(byte[] in) throws Exception {
        return sm3.sm3Digest(in);
    }
}
