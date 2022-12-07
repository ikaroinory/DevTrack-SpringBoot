package cn.auroralab.devtrack.util;

import cn.auroralab.devtrack.service.SecretService;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * Jwt工具类。
 *
 * @author Guanyu Hu
 * @since 2022-11-11
 */
public class JwtUtils {
    /**
     * Jwt有效期。单位：分钟。
     */
    private static final int expire = 24 * 60;
    /**
     * Jwt签名密钥。
     */
    private static final String secret = SpringContextUtils.getBean(SecretService.class).getTokenSecret();

    /**
     * 生成Jwt。
     *
     * @param userUUID 用户uuid。
     * @param username 用户名。
     * @return Jwt token。
     * @author Guanyu Hu
     * @since 2022-11-12
     */
    public static String generate(String userUUID, String username) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire * 60 * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("DevTrack Token")           // sub：主题
                .setIssuer("AuroraLab")                 // iss：发行人
                .setAudience(username)                  // aud：用户
                .setIssuedAt(now)                       // iat：发行时间
                .setExpiration(expireDate)              // exp：到期时间
                .claim("uuid", userUUID)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取Jwt声明部分。
     *
     * @param jwt Jwt字符串。
     * @return Jwt声明对象。
     * @throws ExpiredJwtException      过期的Jwt。
     * @throws UnsupportedJwtException  被篡改的Jwt。
     * @throws MalformedJwtException    Jwt格式异常。
     * @throws SignatureException       Jwt签名异常。
     * @throws IllegalArgumentException 非法参数异常。
     * @author Guanyu Hu
     * @since 2022-11-12
     */
    private static Claims getClaim(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
    }

    /**
     * 判断Jwt是否过期。
     *
     * @param jwt Jwt字符串。
     * @throws ExpiredJwtException      过期的Jwt。
     * @throws UnsupportedJwtException  被篡改的Jwt。
     * @throws MalformedJwtException    Jwt格式异常。
     * @throws SignatureException       Jwt签名异常。
     * @throws IllegalArgumentException 非法参数异常。
     * @author Guanyu Hu
     * @since 2022-11-11
     */
    public static boolean isExpired(String jwt) {
        return getClaim(jwt).getExpiration().before(new Date());
    }

    /**
     * 获取Jwt主题。
     *
     * @param jwt Jwt字符串。
     * @throws ExpiredJwtException      过期的Jwt。
     * @throws UnsupportedJwtException  被篡改的Jwt。
     * @throws MalformedJwtException    Jwt格式异常。
     * @throws SignatureException       Jwt签名异常。
     * @throws IllegalArgumentException 非法参数异常。
     * @author Guanyu Hu
     * @since 2022-11-11
     */
    public static String getSubject(String jwt) {
        return getClaim(jwt).getSubject();
    }

    /**
     * 获取Jwt用户。
     *
     * @param jwt Jwt字符串。
     * @throws ExpiredJwtException      过期的Jwt。
     * @throws UnsupportedJwtException  被篡改的Jwt。
     * @throws MalformedJwtException    Jwt格式异常。
     * @throws SignatureException       Jwt签名异常。
     * @throws IllegalArgumentException 非法参数异常。
     * @author Guanyu Hu
     * @since 2022-11-11
     */
    public static String getAudience(String jwt) {
        return getClaim(jwt).getAudience();
    }

    /**
     * 获取Jwt发行时间。
     *
     * @param jwt Jwt字符串。
     * @throws ExpiredJwtException      过期的Jwt。
     * @throws UnsupportedJwtException  被篡改的Jwt。
     * @throws MalformedJwtException    Jwt格式异常。
     * @throws SignatureException       Jwt签名异常。
     * @throws IllegalArgumentException 非法参数异常。
     * @author Guanyu Hu
     * @since 2022-11-11
     */
    public static Date getIssuedAtDate(String jwt) {
        return getClaim(jwt).getIssuedAt();
    }

    /**
     * 获取Jwt用户uuid时间。
     *
     * @param jwt Jwt字符串。
     * @return 用户UUID。
     * @throws ExpiredJwtException      过期的Jwt。
     * @throws UnsupportedJwtException  被篡改的Jwt。
     * @throws MalformedJwtException    Jwt格式异常。
     * @throws SignatureException       Jwt签名异常。
     * @throws IllegalArgumentException 非法参数异常。
     * @author Guanyu Hu
     * @since 2022-11-20
     */
    public static String getUserUUID(String jwt) {
        return JwtUtils.getClaim(jwt).get("uuid", String.class);
    }
}
