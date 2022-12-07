package cn.auroralab.devtrack.enumeration;


import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 任务优先级。
 *
 * @author Guanyu Hu
 * @since 2022-11-22
 */
@AllArgsConstructor
public enum Priority implements Parseable {
    /**
     * 未知。
     */
    UNKNOWN(0),
    /**
     * 常规的。
     */
    GENERAL(1),
    /**
     * 一般的。
     */
    NORMAL(2),
    /**
     * 重要的。
     */
    IMPORTANT(3),
    /**
     * 紧急的。
     */
    URGENT(4),
    /**
     * 最紧急的。
     */
    MOST_URGENT(5),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
