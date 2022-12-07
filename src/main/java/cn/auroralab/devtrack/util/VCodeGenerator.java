package cn.auroralab.devtrack.util;

import lombok.Getter;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * 验证码生成器。
 *
 * @author Guanyu Hu
 * @since 2022-10-17
 */
public class VCodeGenerator {
    /**
     * 验证码的默认长度。
     */
    private static final int DEFAULT_VCODE_LENGTH = 6;
    /**
     * 验证码的默认有效时间。单位：分钟。
     */
    public static final int DEFAULT_VALID_TIME = 15;

    /**
     * 验证码生成时间。
     */
    @Getter
    private final LocalDateTime startTime;
    /**
     * 验证码。
     */
    @Getter
    private final String vCode;
    /**
     * 验证码有效时间。单位：分钟。
     */
    @Getter
    private final int validTime;

    public VCodeGenerator() { this(DEFAULT_VALID_TIME); }

    public VCodeGenerator(int validTime) {
        this.startTime = LocalDateTime.now();

        Random random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < DEFAULT_VCODE_LENGTH; i++)
            stringBuilder.append(random.nextInt(10));

        this.vCode = stringBuilder.toString();
        this.validTime = validTime;
    }

    /**
     * 判断验证码是否过期。
     *
     * @param startTime 验证码生成时间。
     * @param validTime 验证码有效期。
     * @return 验证码是否过期。
     * @author Guanyu Hu
     * @since 2022-10-17
     */
    public static boolean isValid(LocalDateTime startTime, int validTime) {
        return Duration.between(startTime, LocalDateTime.now()).toMinutes() < validTime;
    }
}
