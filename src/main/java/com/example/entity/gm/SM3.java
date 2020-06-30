package com.example.entity.gm;

import org.bouncycastle.crypto.digests.SM3Digest;

public class SM3 {

//    @Test
//    public void sm3Digest(){
//        ToolService tool = new ToolServiceImpl();
//        byte[] msg = "zhuheng".getBytes();
//        SM3Digest sm3Digest = new SM3Digest();
//        sm3Digest.update(msg,0,msg.length);
//        byte[] digest = new byte[sm3Digest.getDigestSize()];
//        sm3Digest.doFinal(digest,0);
//        System.out.println(tool.byte2hex(digest,digest.length));
//    }

    //SM3 hash  zhuheng20200605
    public byte[] sm3Digest(byte msg[]){
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(msg,0,msg.length);
        byte[] digest = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(digest,0);
        return  digest;
    }
}
