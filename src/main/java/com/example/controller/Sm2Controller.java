package com.example.controller;

import com.example.service.Sm2Service;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sm2")
public class Sm2Controller {
    @Autowired
    private Sm2Service sm2Service;
    @Autowired
    private ToolService toolService;

    @RequestMapping("/gen")
    @ResponseBody
    public Map<String,String> sm2Gen() {
        Map map = new HashMap();
        try {
            map = sm2Service.sm2Gen();
//            System.out.println(map.get("PRI_KEY"));
//            System.out.println(map.get("PUB_KEY"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/enc")
    @ResponseBody
    public String sm2Enc(String sm2Pub, String sm2Pri, String sm2In) {
        String rec = "数据异常, 请检查数据.";
        try {
            PublicKey pub = sm2Service.setSm2PublicKeySz(sm2Pub);
            byte[] buf = sm2Service.sm2Encrypt(pub, toolService.hex2byte(sm2In));
            rec = toolService.byte2hex(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rec;
    }

    @RequestMapping("/dec")
    @ResponseBody
    public String sm2Dec(String sm2Pub, String sm2Pri, String sm2In) {
        String rec = "数据异常, 请检查数据.";
        try {
            PrivateKey pri = sm2Service.setSm2PrivateKeySz(sm2Pri,sm2Pub);
            byte[] buf = sm2Service.sm2Decrypt(pri,toolService.hex2byte(sm2In));
            rec = toolService.byte2hex(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rec;
    }

    @RequestMapping("/sign")
    @ResponseBody
    public String sm2Sign(String sm2Pub, String sm2Pri,String sm2UserId, String sm2In) {
        String rec = "数据异常, 请检查数据.";
        try {
            PrivateKey pri = sm2Service.setSm2PrivateKeySz(sm2Pri,sm2Pub);
            byte[] buf = sm2Service.sm2Sign(toolService.hex2byte(sm2In),toolService.hex2byte(sm2UserId),pri);
            rec = toolService.byte2hex(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rec;
    }

    @RequestMapping("/verify")
    @ResponseBody
    public boolean sm2Verify(String sm2Pub, String sm2Pri,String sm2UserId, String sm2In,String sm2Out) {
        try {
            PublicKey pub = sm2Service.setSm2PublicKeySz(sm2Pub);
            return sm2Service.sm2Verify(toolService.hex2byte(sm2Out),toolService.hex2byte(sm2In),toolService.hex2byte(sm2UserId),pub);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
