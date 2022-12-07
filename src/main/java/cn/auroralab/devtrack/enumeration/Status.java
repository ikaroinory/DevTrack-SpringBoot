package cn.auroralab.devtrack.enumeration;

import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 通用事务状态。
 *
 * @author Guanyu Hu
 * @since 2022-12-05
 */
@AllArgsConstructor
public enum Status implements Parseable {
    /**
     * 未知状态。
     */
    UNKNOWN(0),
    /**
     * 未开始。
     */
    NOT_STAR(1),
    /**
     * 进行中。
     */
    IN_PROGRESS(2),
    /**
     * 已完成。
     */
    FINISHED(3),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
