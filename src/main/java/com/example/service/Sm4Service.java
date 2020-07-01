package com.example.service;

public interface Sm4Service {
    public byte[] ecb_enc_dec(byte data[], byte key[], int enc) throws Exception ;

    public byte[] cbc_enc_dec(byte data[], byte key[], byte iv[], int enc) throws Exception ;
}
