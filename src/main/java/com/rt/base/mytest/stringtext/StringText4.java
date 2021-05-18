package com.rt.base.mytest.stringtext;

import com.rt.base.mytest.util.DevFileUtil;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

/**
 * @author wqy
 */
public class StringText4 {
    public static void main(String[] args) {
        String s = DigestUtils.md5DigestAsHex("不给钱，诅咒你生孩子没屁眼".getBytes());
        s += s.substring(2,8);
        s = s.replace(s.substring(2,8),"");
        String s1 = DigestUtils.md5DigestAsHex(s.getBytes());
        // 5fad1d72051d18832c716fcf2c3a7928
        System.out.println(s1);


        // 解封
        String forgive = DigestUtils.md5DigestAsHex("饶你一命".getBytes());
        forgive += forgive.substring(2,8);
        forgive = forgive.replace(s.substring(2,8),"");
        // bed8627a62320f87f9419c779250f038
        System.out.println(DigestUtils.md5DigestAsHex(forgive.getBytes()));
    }
}
