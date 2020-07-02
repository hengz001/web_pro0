package com.example.entity.nonGm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.*;

public class Hash {

    public byte[] hash(String algo, byte[] msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algo);
        return md.digest(msg);
    }

//    public Map<String,String> hashMap(){
//        Map<String,String> map = new HashMap<>();
//        Set<String> set = hashSet();
//        for (String s: set) {
//            map.put(s,s);
//        }
//        return map;
//    }

    public Set<String> hashSet(){
        return Security.getAlgorithms("MessageDigest");
    }

    public JSONArray hashList(){
        JSONArray list = new JSONArray();
        Set<String> set = hashSet();
        set.forEach(x->{
            JSONObject obj = new JSONObject();
            obj.put("id",x);
            obj.put("text",x);
            list.add(obj);
        });
        return list;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
//        ToolService tool = new ToolServiceImpl();
//        String algorithm = "MD5";
//        String msg = "00000000";
//        MessageDigest md = MessageDigest.getInstance(algorithm);
//        byte[] buf = md.digest(tool.hex2byte(msg,msg.length()));
//        System.out.println(tool.byte2hex(buf,buf.length));

        Set<String> set = Security.getAlgorithms("MessageDigest");
        set.forEach(x->System.out.println(x));
    }
}
