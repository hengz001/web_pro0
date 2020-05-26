package com.example.service;

public interface ToolService {
    String byte2hex(byte[] in, int inl);

    byte[] hex2byte(String in, int inl);
}
