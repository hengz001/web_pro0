package com.example.controller;

import com.example.service.Sm4Service;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sm4")
public class Sm4Controller {
    @Autowired
    private Sm4Service sm4Service;

    @Autowired
    private ToolService toolService;

    @RequestMapping("/encrypt")
    @ResponseBody
    public String encrypt(String keySm4, String ivSm4, String plaintextSm4, String modeSm4){
        String ret = "参数异常,请检查测试数据!";
        byte out[] = null;
        try {
            if("ECB".equals(modeSm4)){
                out = sm4Service.ecb_enc_dec(toolService.hex2byte(plaintextSm4,plaintextSm4.length()), toolService.hex2byte(keySm4,keySm4.length()),1);
            }else if("CBC".equals(modeSm4)){
                out = sm4Service.cbc_enc_dec(toolService.hex2byte(plaintextSm4,plaintextSm4.length()), toolService.hex2byte(keySm4,keySm4.length()), toolService.hex2byte(ivSm4,ivSm4.length()),1);
            }
            ret = toolService.byte2hex(out,plaintextSm4.length()/2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return "密文";
        return ret;
    }

    @RequestMapping("/decrypt")
    @ResponseBody
    public String decrypt(String keySm4, String ivSm4, String ciphertextSm4, String modeSm4){
        String ret = "参数异常,请检查测试数据!";
        byte out[] = null;
        try {
            if("ECB".equals(modeSm4)){
                out = sm4Service.ecb_enc_dec(toolService.hex2byte(ciphertextSm4,ciphertextSm4.length()), toolService.hex2byte(keySm4,keySm4.length()),0);
            }else if("CBC".equals(modeSm4)){
                out = sm4Service.cbc_enc_dec(toolService.hex2byte(ciphertextSm4,ciphertextSm4.length()), toolService.hex2byte(keySm4,keySm4.length()), toolService.hex2byte(ivSm4,ivSm4.length()),0);
            }
            ret = toolService.byte2hex(out,ciphertextSm4.length()/2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
//        return "明文";
    }


}
