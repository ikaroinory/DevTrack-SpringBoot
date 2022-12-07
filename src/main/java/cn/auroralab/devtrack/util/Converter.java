package cn.auroralab.devtrack.util;

import java.math.BigInteger;

/**
 * 转换器。
 *
 * @author Guanyu Hu
 * @since 2022-10-16
 */
public class Converter {
    private static byte charToByte(char c) { return (byte) "0123456789abcdef".indexOf(c); }

    /**
     * 将十六进制字符串转换为二进制数组，每一个元素代表0-0xff。
     *
     * @param str 十六进制字符串。
     * @return 二进制数组。
     * @author Guanyu Hu
     * @since 2022-10-16
     */
    public static byte[] hexStringToBytes(String str) {
        String s = str.length() % 2 == 0 ? str : '0' + str;

        int len = s.length() / 2;
        char[] chars = s.toLowerCase().toCharArray();
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (charToByte(chars[pos]) << 4 | charToByte(chars[pos + 1]));
        }
        return bytes;
    }

    /**
     * 将二进制数组转换为十六进制字符串。
     *
     * @param bytes 二进制数组。
     * @return 十六进制字符串。
     * @author Guanyu Hu
     * @since 2022-10-16
     */
    public static String bytesToHexString(byte[] bytes) {
        String str = new BigInteger(1, bytes).toString(16);
        return "0".repeat(bytes.length * 2 - str.length()) + str;
    }
}
