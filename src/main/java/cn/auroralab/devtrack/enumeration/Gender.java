package cn.auroralab.devtrack.enumeration;

import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 用户性别。
 *
 * @author Guanyu Hu
 * @since 2022-11-11
 */
@AllArgsConstructor
public enum Gender implements Parseable {
    /**
     * 其他。
     */
    OTHERS(0),
    /**
     * 男性。
     */
    MALE(1),
    /**
     * 女性。
     */
    FEMALE(2),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
