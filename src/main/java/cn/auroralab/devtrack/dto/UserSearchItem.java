package cn.auroralab.devtrack.dto;

import cn.auroralab.devtrack.po.Account;
import lombok.Data;

@Data
public class UserSearchItem {
    private final String uuid;
    private final String username;
    private final String nickname;
    private final byte[] avatar;

    public UserSearchItem(Account account) {
        uuid = account.getUuid();
        username = account.getUsername();
        nickname = account.getNickname();
        avatar = account.getAvatar();
    }
}
