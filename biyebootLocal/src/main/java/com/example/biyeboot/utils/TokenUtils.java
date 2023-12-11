package com.example.biyeboot.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.biyeboot.entity.User;



import java.util.Date;

public class TokenUtils {

    public static String genToken(String userId,String userPassword){
        return JWT.create().withAudience(userId) // 将 userid 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //五分钟后token过期
                .sign(Algorithm.HMAC256(userPassword)); // 以 password 作为 token 的密钥


    }
}
