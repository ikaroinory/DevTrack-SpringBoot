package cn.auroralab.devtrack.service;

import org.springframework.core.io.Resource;

public interface EmailService {
    /**
     * 设置邮件正文。
     *
     * @param context 邮件正文
     * @param html    是否开启HTML模式。
     * @author Guanyu Hu
     * @since 2022-10-22
     */
    void setText(String context, boolean html);

    /**
     * 向邮件中添加图片。
     *
     * @param cid       资源cid号。
     * @param classPath 图片在resources资源文件夹下的相对路径。
     * @author Guanyu Hu
     * @since 2022-11-03
     */
    void addImage(String cid, String classPath);

    /**
     * 向邮件中添加图片。
     *
     * @param cid      资源cid号。
     * @param resource 资源对象。
     * @author Guanyu Hu
     * @since 2022-10-22
     */
    void addImage(String cid, Resource resource);

    /**
     * 发送邮件。
     *
     * @param receiveEmail 收件人邮箱。
     * @param subject      邮件主题
     * @author Guanyu Hu
     * @since 2022-10-22
     */
    void sendEmail(String receiveEmail, String subject);

    /**
     * 发送邮件。
     *
     * @param senderName   发件人名字。
     * @param receiveEmail 收件人邮箱。
     * @param subject      邮件主题
     * @author Guanyu Hu
     * @since 2022-10-16
     */
    void sendEmail(String senderName, String receiveEmail, String subject);
}
