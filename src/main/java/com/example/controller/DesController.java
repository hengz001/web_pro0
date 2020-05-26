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
    public String encrypt(String key, String iv, String plaintext, String ciphertext, String algo, String mode){
//        System.out.println("encrypt key "+key);
//        System.out.println("iv "+iv);
//        System.out.println("plaintext "+plaintext);
//        System.out.println("ciphertext "+ciphertext);
//        System.out.println("algo "+algo);
//        System.out.println("mode "+mode);
        String ret = "";
        byte out[] = null;
        try {
            if("ECB".equals(mode)){
                out = desService.ecb_enc_dec(toolService.hex2byte(plaintext,plaintext.length()), toolService.hex2byte(key,key.length()),1);
            }else if("CBC".equals(mode)){
                out = desService.cbc_enc_dec(toolService.hex2byte(plaintext,plaintext.length()), toolService.hex2byte(key,key.length()), toolService.hex2byte(iv,iv.length()),1);
            }else if("CFB".equals(mode)){
                out = desService.cfb_enc_dec(toolService.hex2byte(plaintext,plaintext.length()), toolService.hex2byte(key,key.length()), toolService.hex2byte(iv,iv.length()),1);
            }else if("OFB".equals(mode)){
                out = desService.ofb_enc_dec(toolService.hex2byte(plaintext,plaintext.length()), toolService.hex2byte(key,key.length()), toolService.hex2byte(iv,iv.length()),1);
            }
            ret = toolService.byte2hex(out,plaintext.length()/2);
        } catch (Exception e) {
            e.printStackTrace();
            return "请检测数据";
        }
//        return "密文";
        return ret;
    }

    @RequestMapping("/decrypt")
    @ResponseBody
    public String decrypt(String key, String iv, String plaintext, String ciphertext, String algo, String mode){
//        System.out.println("decrypt key "+key);
//        System.out.println("iv "+iv);
//        System.out.println("plaintext "+plaintext);
//        System.out.println("ciphertext "+ciphertext);
//        System.out.println("algo "+algo);
//        System.out.println("mode "+mode);

        String ret = "";
        byte out[] = null;
        try {
            if("ECB".equals(mode)){
                out = desService.ecb_enc_dec(toolService.hex2byte(ciphertext,ciphertext.length()), toolService.hex2byte(key,key.length()),0);
            }else if("CBC".equals(mode)){
                out = desService.cbc_enc_dec(toolService.hex2byte(ciphertext,ciphertext.length()), toolService.hex2byte(key,key.length()), toolService.hex2byte(iv,iv.length()),0);
            }else if("CFB".equals(mode)){
                out = desService.cfb_enc_dec(toolService.hex2byte(ciphertext,ciphertext.length()), toolService.hex2byte(key,key.length()), toolService.hex2byte(iv,iv.length()),0);
            }else if("OFB".equals(mode)){
                out = desService.ofb_enc_dec(toolService.hex2byte(ciphertext,ciphertext.length()), toolService.hex2byte(key,key.length()), toolService.hex2byte(iv,iv.length()),0);
            }
            ret = toolService.byte2hex(out,ciphertext.length()/2);
        } catch (Exception e) {
            e.printStackTrace();
            return "请检测数据";
        }
        return ret;
//        return "明文";
    }


}
