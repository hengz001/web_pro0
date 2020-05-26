package com.example.service;

public interface AesService {
    public byte[] ecb_enc_dec_aes(byte data[],byte key[], int enc) throws Exception ;

    public byte[] cbc_enc_dec_aes(byte data[],byte key[], byte iv[], int enc) throws Exception ;

    public byte[] cfb_enc_dec_aes(byte data[],byte key[], byte iv[], int enc) throws Exception ;

    public byte[] ofb_enc_dec_aes(byte data[],byte key[], byte iv[], int enc) throws Exception ;
}
