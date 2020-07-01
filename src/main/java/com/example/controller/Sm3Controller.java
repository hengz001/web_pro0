package com.example.controller;

import com.example.service.Sm3Service;
import com.example.service.Sm4Service;
import com.example.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sm3")
public class Sm3Controller {
    @Autowired
    private Sm3Service sm3Service;
    @Autowired
    private ToolService toolService;

    @RequestMapping("/hash")
    @ResponseBody
    public String sm3Hash(String inSM3) {
        byte buf[] = null;
        try {
            buf = toolService.hex2byte(inSM3,inSM3.length());
            if(buf==null){
                return "数据异常！ 请输入16进制数据";
            }
            buf = sm3Service.sm3Hash(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return "输入参数异常.";
        }
        return toolService.byte2hex(buf,buf.length);
    }


}
