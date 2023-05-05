package com.qi.common.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;


import java.util.Date;
/** 生成JSON Web令牌的工具类
 * @author admin
 */
public class JwtHelper {

    /**
     * token过期时间:实际开发中写1个小时，两个小时，30分钟等等
     */
    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;

    /**
     * 加密的秘钥：实际开发中生成一个唯一字符串或者随机的字符串
     */
    private static String tokenSignKey = "123456";

    /**
     * 根据用户id和用户名称成成token
     * @param userId
     * @param username
     * @return
     */
    public static String createToken(Long userId, String username) {
        String token = Jwts.builder()
                //分组
                .setSubject("AUTH-USER")
                //生成过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                //有效载荷：实际开发中放得更多
                .claim("userId", userId)
                .claim("username", username)
                //根据秘钥对字符串进行编码操作
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                //对字符串进行压缩，变为一行
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }


    /**
     * 获取token
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            if (token==null|| "".equals(token)) {
                return null;
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            if (token==null|| "".equals(token)) {
                return "";
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除token
     * @param token
     */
    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "admin"); //"eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY2sDQ3MTbQUSotTi3yTFGyMjKEsP0Sc1OBWp6unfB0f7NSLQDxzD8_QwAAAA.2eCJdsJXOYaWFmPTJc8gl1YHTRl9DAeEJprKZn4IgJP9Fzo5fLddOQn1Iv2C25qMpwHQkPIGukTQtskWsNrnhQ";//JwtHelper.createToken(7L, "admin");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUsername(token));
    }
}
