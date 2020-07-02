package com.example.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.nonGm.Hash;
import com.example.service.HashService;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

@Service("hashService")
public class HashServiceImpl implements HashService {
    Hash hash = new Hash();

    public byte[] hash(String algo, byte[] msg) throws NoSuchAlgorithmException {
        return hash.hash(algo, msg);
    }

    public Set<String> hashSet(){
        return hash.hashSet();
    }

    public JSONArray hashList(){
        return hash.hashList();
    }

}
