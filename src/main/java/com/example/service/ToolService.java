package com.example.service;

public interface ToolService {
    String byte2hex(byte[] in);

    byte[] hex2byte(String in);

    String byte2hex(byte[] in, int inl);

    byte[] hex2byte(String in, int inl);

    String byte2hex(byte[] in, int offset, int inl);

    byte[] hex2byte(String in, int offset, int inl);
}
