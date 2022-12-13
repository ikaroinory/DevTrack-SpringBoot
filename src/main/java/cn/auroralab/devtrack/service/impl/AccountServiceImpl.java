package cn.auroralab.devtrack.service.impl;

import cn.auroralab.devtrack.dao.AccountDAO;
import cn.auroralab.devtrack.dao.VCodeDAO;
import cn.auroralab.devtrack.dto.UserInformation;
import cn.auroralab.devtrack.dto.UserSearchItem;
import cn.auroralab.devtrack.enumeration.Gender;
import cn.auroralab.devtrack.enumeration.VCodeType;
import cn.auroralab.devtrack.exception.system.FileTypeException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;
import cn.auroralab.devtrack.exception.system.UnknownException;
import cn.auroralab.devtrack.exception.user.UserExistedException;
import cn.auroralab.devtrack.exception.user.UserNotFoundException;
import cn.auroralab.devtrack.exception.user.WrongPasswordException;
import cn.auroralab.devtrack.exception.vcode.VCodeErrorException;
import cn.auroralab.devtrack.exception.vcode.VCodeExpiredException;
import cn.auroralab.devtrack.exception.vcode.VCodeRecordNotFoundException;
import cn.auroralab.devtrack.po.Account;
import cn.auroralab.devtrack.po.VCodeRecord;
import cn.auroralab.devtrack.service.AccountService;
import cn.auroralab.devtrack.util.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDAO accountDAO;
    private final VCodeDAO vCodeDAO;

    public AccountServiceImpl(AccountDAO accountDAO, VCodeDAO vCodeDAO) {
        this.accountDAO = accountDAO;
        this.vCodeDAO = vCodeDAO;
    }

    public String signIn(String username, String password)
            throws RequiredParametersIsEmptyException, UserNotFoundException, WrongPasswordException {
        Validator.notEmpty(username, password);

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Account.USERNAME, username);
        Account account = accountDAO.selectOne(queryWrapper);

        if (account == null)
            throw new UserNotFoundException();
        if (!account.getPasswordDigest().equals(BitstreamGenerator.parseMD5(password)))
            throw new WrongPasswordException();

        accountDAO.updateLastSignInTime(account.getUuid());

        return JwtUtils.generate(account.getUuid(), account.getUsername());
    }

    public byte[] autoSignIn(String userUUID)
            throws RequiredParametersIsEmptyException, UserNotFoundException {
        Validator.notEmpty(userUUID);

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Account.UUID, userUUID);
        Account account = accountDAO.selectOne(queryWrapper);

        if (account == null)
            throw new UserNotFoundException();

        accountDAO.updateLastSignInTime(userUUID);

        return account.getAvatar();
    }

    public void signUp(String username, String password, String email, String vCode)
            throws RequiredParametersIsEmptyException, VCodeRecordNotFoundException, VCodeErrorException, UserExistedException, UnknownException {
        Validator.notEmpty(username, password, email, vCode);

        VCodeRecord latestRecord = vCodeDAO.getLatestRecord(VCodeType.SIGN_UP, email);

        if (latestRecord == null)
            throw new VCodeRecordNotFoundException();
        if (!latestRecord.getVCode().equals(vCode))
            throw new VCodeErrorException();
        if (!VCodeGenerator.isValid(latestRecord.getTime(), latestRecord.getValidTime()))
            throw new VCodeExpiredException();

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Account.USERNAME, username);
        if (accountDAO.selectOne(queryWrapper) != null)
            throw new UserExistedException();

        Account account = new Account();
        account.setUuid(BitstreamGenerator.parseUUID());
        account.setUsername(username);
        account.setPasswordDigest(BitstreamGenerator.parseMD5(password));
        account.setAvatar(ResourceFileLoader.imageToBytes("img/default_avatar.png"));
        account.setNickname(username);
        account.setEmail(email);

        int insert = accountDAO.insert(account);

        if (insert == 0)
            throw new UnknownException();
    }

    public void retrievePassword(String username, String password, String email, String vCode)
            throws RequiredParametersIsEmptyException, VCodeRecordNotFoundException, VCodeErrorException, UserNotFoundException, UnknownException {
        Validator.notEmpty(username, password, email, vCode);

        VCodeRecord latestRecord = vCodeDAO.getLatestRecord(VCodeType.RETRIEVE_PASSWORD, email);

        if (latestRecord == null)
            throw new VCodeRecordNotFoundException();
        if (!latestRecord.getVCode().equals(vCode))
            throw new VCodeErrorException();
        if (!VCodeGenerator.isValid(latestRecord.getTime(), latestRecord.getValidTime()))
            throw new VCodeExpiredException();

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Account.USERNAME, username);
        if (accountDAO.selectOne(queryWrapper) == null)
            throw new UserNotFoundException();

        Account account = new Account();
        account.setPasswordDigest(BitstreamGenerator.parseMD5(password));

        int update = accountDAO.update(account, queryWrapper);

        if (update == 0)
            throw new UnknownException();
    }

    public void updateProfile(String username, String nickname, Gender gender, String phone, String location, String website, String introduction)
            throws RequiredParametersIsEmptyException, UserNotFoundException {
        Validator.notEmpty(username);

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Account.USERNAME, username);

        if (accountDAO.selectOne(queryWrapper) == null)
            throw new UserNotFoundException();

        Account account = new Account();
        account.setNickname(nickname);
        account.setGender(gender);
        account.setPhone(phone);
        account.setLocation(location);
        account.setWebsite(website);
        account.setIntroduction(introduction);

        accountDAO.update(account, queryWrapper);
    }

    public void updateAvatar(String username, MultipartFile avatar)
            throws RequiredParametersIsEmptyException, FileTypeException, UserNotFoundException, UnknownException {
        Validator.notEmpty(username);

        String filename = avatar.getOriginalFilename();
        Validator.notEmpty(filename);

        assert filename != null;
        String type = filename.split("\\.")[1];

        String[] typeList = {"png", "jpg", "jpeg"};

        if (!Arrays.asList(typeList).contains(type))
            throw new FileTypeException();

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Account.USERNAME, username);
        if (accountDAO.selectOne(queryWrapper) == null)
            throw new UserNotFoundException();

        Account account = new Account();
        try {
            account.setAvatar(avatar.getBytes());
        } catch (IOException e) {
            throw new UnknownException();
        }

        accountDAO.update(account, queryWrapper);
    }

    public UserInformation getUserInformation(String username)
            throws RequiredParametersIsEmptyException, UserNotFoundException {
        Validator.notEmpty(username);

        UserInformation userInformation = accountDAO.getUserInformation(username);

        if (userInformation == null)
            throw new UserNotFoundException();

        return userInformation;
    }

    public List<UserSearchItem> searchUsers(String prefix) throws RequiredParametersIsEmptyException {
        Validator.notEmpty(prefix);

        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight(Account.USERNAME, prefix)
                .last("limit 10");

        List<Account> list = accountDAO.selectList(queryWrapper);

        return list.stream().map(UserSearchItem::new).collect(Collectors.toList());
    }
}
