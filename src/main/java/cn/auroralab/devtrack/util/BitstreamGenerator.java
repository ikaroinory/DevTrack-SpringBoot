package cn.auroralab.devtrack.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 比特流生成器。
 *
 * @author Guanyu Hu
 * @since 2022-12-04
 */
public class BitstreamGenerator {
    /**
     * 根据时间戳获取基于版本4的UUID（第13位和第14位随机赋值）。
     *
     * @author Guanyu Hu
     * @since 2022-10-15
     */
    public static String parseUUID() {
        char c = "0123456789abcdef".charAt(new SecureRandom().nextInt(16));
        return new StringBuilder(UUID.randomUUID().toString().trim().replaceAll("-", ""))
                .replace(12, 13, String.valueOf(c))
                .toString();
    }

    /**
     * 获取字符串的MD5消息摘要。
     *
     * @author Guanyu Hu
     * @since 2022-10-15
     */
    public static String parseMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return Converter.bytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException ignored) { }
        return "";
    }
}
