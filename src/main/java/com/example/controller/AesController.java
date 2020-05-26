package com.example.controller;

import com.example.service.AesService;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aes")
public class AesController {
    @Autowired
    private AesService aesService;
    @Autowired
    private ToolService toolService;

    @RequestMapping("/encrypt")
    @ResponseBody
    public String encrypt(String keyAes, String ivAes, String plaintextAes, String ciphertextAes, String algoAes, String modeAes){
//        System.out.println("key= "+keyAes
//        +" iv= "+ivAes
//        +" plaintext= "+plaintextAes
//        +" ciphertext= "+ciphertextAes
//        +" algo= "+algoAes
//        +" mode= "+modeAes);
        byte[] out = null;
        try {
            if("ECB".equals(modeAes)){
                out = aesService.ecb_enc_dec_aes(
                        toolService.hex2byte(plaintextAes,plaintextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),1);
            }else if("CBC".equals(modeAes)){
                out = aesService.cbc_enc_dec_aes(
                        toolService.hex2byte(plaintextAes,plaintextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),
                        toolService.hex2byte(ivAes,ivAes.length()),1);
            }else if("CFB".equals(modeAes)){
                out = aesService.cfb_enc_dec_aes(
                        toolService.hex2byte(plaintextAes,plaintextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),
                        toolService.hex2byte(ivAes,ivAes.length()),1);
            }else if("OFB".equals(modeAes)){
                out = aesService.ofb_enc_dec_aes(
                        toolService.hex2byte(plaintextAes,plaintextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),
                        toolService.hex2byte(ivAes,ivAes.length()),1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toolService.byte2hex(out,out.length);
//        return "请检查AES数据";
    }

    @RequestMapping("/decrypt")
    @ResponseBody
    public String decrypt(String keyAes, String ivAes, String plaintextAes, String ciphertextAes, String algoAes, String modeAes){
//        System.out.println("key= "+keyAes
//        +" iv= "+ivAes
//        +" plaintext= "+plaintextAes
//        +" ciphertext= "+ciphertextAes
//        +" algo= "+algoAes
//        +" mode= "+modeAes);
        byte[] out = null;
        try {
            if("ECB".equals(modeAes)){
                out = aesService.ecb_enc_dec_aes(
                        toolService.hex2byte(ciphertextAes,ciphertextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),0);
            }else if("CBC".equals(modeAes)){
                out = aesService.cbc_enc_dec_aes(
                        toolService.hex2byte(ciphertextAes,ciphertextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),
                        toolService.hex2byte(ivAes,ivAes.length()),0);
            }else if("CFB".equals(modeAes)){
                out = aesService.cfb_enc_dec_aes(
                        toolService.hex2byte(ciphertextAes,ciphertextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),
                        toolService.hex2byte(ivAes,ivAes.length()),0);
            }else if("OFB".equals(modeAes)){
                out = aesService.ofb_enc_dec_aes(
                        toolService.hex2byte(ciphertextAes,ciphertextAes.length()),
                        toolService.hex2byte(keyAes,keyAes.length()),
                        toolService.hex2byte(ivAes,ivAes.length()),0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toolService.byte2hex(out,out.length);
//        return "请检查AES数据";
    }
}
