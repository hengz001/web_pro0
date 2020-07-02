package com.example.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

public interface HashService {
    public byte[] hash(String algo, byte[] msg) throws NoSuchAlgorithmException;

    public Set<String> hashSet();

    public JSONArray hashList();
}

