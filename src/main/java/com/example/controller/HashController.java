package com.example.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.service.HashService;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/hash")
public class HashController {
    @Autowired
    private HashService hashService;
    @Autowired
    private ToolService toolService;

    @RequestMapping("/digest")
    @ResponseBody
    public String hash(String algoHash, String inHash) {
//        System.out.println(algoHash);
        byte buf[] = null;
        String rec = "数据异常！ 请输入16进制数据";
        try {
            buf = toolService.hex2byte(inHash,inHash.length());
            if(buf==null) return rec;
            buf = hashService.hash(algoHash, buf);
            rec = toolService.byte2hex(buf,buf.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rec;
    }

    @RequestMapping("/hashSet")
    @ResponseBody
    public Set<String> hashSet(){
        return  hashService.hashSet();
    }

    @RequestMapping("/hashList")
    @ResponseBody
    public JSONArray hashList(){
        return hashService.hashList();
    }
}
