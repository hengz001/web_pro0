package com.example.service.impl;

import com.example.service.ToolService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("toolService")
public class ToolServiceImpl implements ToolService {
    public String byte2hex(byte[] in, int inl){
        StringBuffer sb = new StringBuffer();
        byte h,l;
        for(int i=0; i<inl; i++){
            h = (byte) ((in[i]&0xf0)>>4);
            l = (byte) (in[i]&0x0f);
            sb.append(h>9?(char)(h-10+'A'):(char)(h+'0'));
            sb.append(l>9?(char)(l-10+'A'):(char)(l+'0'));
        }
        return sb.toString();
    }
    public byte[] hex2byte(String in, int inl){
        if(inl%2!=0) return null;
        in = in.toUpperCase();

        byte[] out = new byte[inl/2];
        List<Byte> list = new ArrayList<Byte>();
        byte h = 0,l=0;
        char c;
        for(int i=0; i<inl; i++){
            c = in.charAt(i);

            if(c>'F'||c<'0'){
                return null;
            }

            if(i%2==0){
                h = c>'9'?((byte) (c-'A'+10)):((byte) (c-'0'));
            }else{
                l = c>'9'?((byte) (c-'A'+10)):((byte) (c-'0'));
                out[i/2] = (byte) ((h<<4)|l);
//                list.add((byte) ((h<<4)|l));
            }
        }
//        return list.toArray(new Byte[inl/2]);
        return out;
    }
}
