package cn.auroralab.devtrack.service;

import cn.auroralab.devtrack.dto.UserInformation;
import cn.auroralab.devtrack.dto.UserSearchItem;
import cn.auroralab.devtrack.enumeration.Gender;
import cn.auroralab.devtrack.exception.system.FileTypeException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;
import cn.auroralab.devtrack.exception.user.UserExistedException;
import cn.auroralab.devtrack.exception.user.UserNotFoundException;
import cn.auroralab.devtrack.exception.user.WrongPasswordException;
import cn.auroralab.devtrack.exception.vcode.VCodeErrorException;
import cn.auroralab.devtrack.exception.vcode.VCodeRecordNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    /**
     * 登录。
     *
     * @param username 用户名。
     * @param password 密码。
     * @return Jwt字符串。
     * @throws RequiredParametersIsEmptyException 必填参数为空。
     * @throws UserNotFoundException              用户不存在。
     * @throws WrongPasswordException             密码错误。
     * @author Guanyu Hu, Xiaotong Wu
     * @since 2022-10-17
     */
    String signIn(String username, String password)
            throws RequiredParametersIsEmptyException, UserNotFoundException, WrongPasswordException;

    /**
     * 自动登录。
     *
     * @param userUUID 用户UUID。
     * @return 用户头像。
     * @throws RequiredParametersIsEmptyException 必填参数为空。
     * @throws UserNotFoundException              用户不存在。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    byte[] autoSignIn(String userUUID)
            throws RequiredParametersIsEmptyException, UserNotFoundException;

    /**
     * 注册。
     *
     * @param username 用户名。
     * @param password 密码。
     * @param email    邮箱。
     * @param vCode    验证码。
     * @author Guanyu Hu
     * @since 2022-10-15
     */
    void signUp(String username, String password, String email, String vCode)
            throws RequiredParametersIsEmptyException, VCodeRecordNotFoundException, VCodeErrorException, UserExistedException, UnknownException;

    /**
     * 修改用户个人信息。
     *
     * @param username     用户名。
     * @param nickname     昵称。
     * @param gender       性别。
     * @param phone        手机号。
     * @param location     位置。
     * @param website      个人网站。
     * @param introduction 介绍。
     * @author Xiaotong Wu, Guanyu Hu
     * @since 2022-11-01
     */
    void updateProfile(String username, String nickname, Gender gender, String phone, String location, String website, String introduction)
            throws RequiredParametersIsEmptyException, UserNotFoundException;

    /**
     * 修改用户头像。
     *
     * @param username 用户名。
     * @param avatar   头像文件。
     * @author Xiaotong Wu
     * @since 2022-11-01
     */
    void updateAvatar(String username, MultipartFile avatar)
            throws RequiredParametersIsEmptyException, FileTypeException, UserNotFoundException, UnknownException;

    /**
     * 获取用户信息。
     *
     * @param username 用户名。
     * @author Guanyu Hu
     * @since 2022-11-03
     */
    UserInformation getUserInformation(String username)
            throws RequiredParametersIsEmptyException, UserNotFoundException;

    /**
     * 根据用户名前缀搜索用户。
     *
     * @param prefix 用户名前缀。
     * @return 用户列表。
     */
    List<UserSearchItem> searchUsers(String prefix)
            throws RequiredParametersIsEmptyException;
}
