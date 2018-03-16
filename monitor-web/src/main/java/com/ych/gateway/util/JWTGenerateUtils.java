package com.ych.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Date;

/**
 * Created by chenhao.ye on 2017/10/17.
 */
public class JWTGenerateUtils {

    /**
     * 生成JWT
     * @param secret    JWT密钥
     * @param uid       用户唯一标识
     * @param issuer    JWT签发者
     * @return
     */
    public static String generateJWT(String secret, Long uid, String issuer) {
        Date now = new Date();
        return Jwts.builder().setClaims(new DefaultClaims().setIssuer(issuer).setIssuedAt(now)
                .setSubject(uid.toString())).signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
