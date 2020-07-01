package com.example.controller;

import com.example.service.DesService;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/des")
public class DesController {
    @Autowired
    private DesService desService;

    @Autowired
    private ToolService toolService;

    @RequestMapping("/encrypt")
    @ResponseBody
    public String encrypt(String keyDes, String ivDes, String plaintextDes, String modeDes){
        String ret = "参数异常,请检查测试数据!";
        byte out[] = null;
        try {
            if("ECB".equals(modeDes)){
                out = desService.ecb_enc_dec(toolService.hex2byte(plaintextDes,plaintextDes.length()), toolService.hex2byte(keyDes,keyDes.length()),1);
            }else if("CBC".equals(modeDes)){
                out = desService.cbc_enc_dec(toolService.hex2byte(plaintextDes,plaintextDes.length()), toolService.hex2byte(keyDes,keyDes.length()), toolService.hex2byte(ivDes,ivDes.length()),1);
            }else if("CFB".equals(modeDes)){
                out = desService.cfb_enc_dec(toolService.hex2byte(plaintextDes,plaintextDes.length()), toolService.hex2byte(keyDes,keyDes.length()), toolService.hex2byte(ivDes,ivDes.length()),1);
            }else if("OFB".equals(modeDes)){
                out = desService.ofb_enc_dec(toolService.hex2byte(plaintextDes,plaintextDes.length()), toolService.hex2byte(keyDes,keyDes.length()), toolService.hex2byte(ivDes,ivDes.length()),1);
            }
            ret = toolService.byte2hex(out,plaintextDes.length()/2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "密文";
        return ret;
    }

    @RequestMapping("/decrypt")
    @ResponseBody
    public String decrypt(String keyDes, String ivDes, String ciphertextDes, String modeDes){
        String ret = "参数异常,请检查测试数据!";
        byte out[] = null;
        try {
            if("ECB".equals(modeDes)){
                out = desService.ecb_enc_dec(toolService.hex2byte(ciphertextDes,ciphertextDes.length()), toolService.hex2byte(keyDes,keyDes.length()),0);
            }else if("CBC".equals(modeDes)){
                out = desService.cbc_enc_dec(toolService.hex2byte(ciphertextDes,ciphertextDes.length()), toolService.hex2byte(keyDes,keyDes.length()), toolService.hex2byte(ivDes,ivDes.length()),0);
            }else if("CFB".equals(modeDes)){
                out = desService.cfb_enc_dec(toolService.hex2byte(ciphertextDes,ciphertextDes.length()), toolService.hex2byte(keyDes,keyDes.length()), toolService.hex2byte(ivDes,ivDes.length()),0);
            }else if("OFB".equals(modeDes)){
                out = desService.ofb_enc_dec(toolService.hex2byte(ciphertextDes,ciphertextDes.length()), toolService.hex2byte(keyDes,keyDes.length()), toolService.hex2byte(ivDes,ivDes.length()),0);
            }
            ret = toolService.byte2hex(out,ciphertextDes.length()/2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
//        return "明文";
    }


}
