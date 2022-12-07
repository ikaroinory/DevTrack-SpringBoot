package cn.auroralab.devtrack.service;

public interface SecretService {
    /**
     * 获取Token加密密钥。
     *
     * @return 密钥。
     * @author Guanyu Hu
     * @since 2022-11-11
     */
    String getTokenSecret();
}
