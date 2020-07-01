package com.example.controller;

import com.example.service.RsaService;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

@Controller
@RequestMapping("/rsa")
public class RsaController {
    @Autowired
    private RsaService rsaService;

    @Autowired
    private ToolService toolService;

    @RequestMapping("/generate")
    @ResponseBody
    public Map generateRsa(int rsaKeyLen){
//        System.out.println(keyLen);
        Map map = null;
        try {
            KeyPair keyPair = rsaService.rsaGenerate(rsaKeyLen);
            map = rsaService.rsaSplit(keyPair);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
//        return "successfully.";
        return map;
    }

    @RequestMapping("/encrypt")
    @ResponseBody
    String rsaEncrypt(String n,String e,String d,String p,String q,
                      String dp,String dq,String qmp,String plaintextRsa){
//        System.out.println(n+"\n"+e+"\n"+d+"\n"+p+"\n"+q+"\n"+
//                dp+"\n"+dq+"\n"+qmp+"\n"+plaintextRsa);
        try {
            Map map = rsaService.rsaCombination(n,e,d,p,q,dp,dq,qmp);
            byte in[] = toolService.hex2byte(plaintextRsa,plaintextRsa.length());
            byte out[] = rsaService.rsaEncryptDecrypt((RSAKey) map.get("publicKey"),1,in);
            return toolService.byte2hex(out,out.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "encrypt failed! 请检测数据 数据不能大于RSA密钥";
    }

    @RequestMapping("/decrypt")
    @ResponseBody
    String decrtpt(String n,String e,String d,String p,String q,
                      String dp,String dq,String qmp,String ciphertextRsa){
        try {
            Map map = rsaService.rsaCombination(n,e,d,p,q,dp,dq,qmp);
            byte in[] = toolService.hex2byte(ciphertextRsa,ciphertextRsa.length());
            byte out[] = rsaService.rsaEncryptDecrypt((RSAKey) map.get("privateKey"),0,in);
            return toolService.byte2hex(out,out.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "decrypt failed! 请检测数据 数据不能大于RSA密钥";
    }

    @RequestMapping("/sign")
    @ResponseBody
    String rsaSign(String n,String e,String d,String p,String q,
                     String dp,String dq,String qmp,String plaintextRsa){
        try {
            Map map = rsaService.rsaCombination(n,e,d,p,q,dp,dq,qmp);
            byte in[] = toolService.hex2byte(plaintextRsa,plaintextRsa.length());
            byte out[] = rsaService.rsaSign((RSAKey) map.get("privateKey"),in);
            return toolService.byte2hex(out,out.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "encrypt failed! 请检测数据 数据不能大于RSA密钥";
    }

    @RequestMapping("/verify")
    @ResponseBody
    String rsaVerify(String n,String e,String d,String p,String q,
                      String dp,String dq,String qmp,String plaintextRsa,String ciphertextRsa){
        try {
            Map map = rsaService.rsaCombination(n,e,d,p,q,dp,dq,qmp);
            byte in[] = toolService.hex2byte(plaintextRsa,plaintextRsa.length());
            byte sign[] = toolService.hex2byte(ciphertextRsa,ciphertextRsa.length());
            boolean bn = rsaService.rsaVerify((RSAKey) map.get("publicKey"),in,sign);
            if(bn){
//                System.out.println("签名成功");
                return "验签成功";
            }else{
//                System.out.println("签名失败");
                return "验签失败";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "encrypt failed! 请检测数据 数据不能大于RSA密钥";
    }
}
