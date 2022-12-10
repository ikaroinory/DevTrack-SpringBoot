package cn.auroralab.devtrack.enumeration;

import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 任务类型。
 *
 * @author Guanyu Hu
 * @since 2022-11-10
 */
@AllArgsConstructor
public enum TaskType implements Parseable {
    /**
     * 未知。
     */
    UNKNOWN(0),
    /**
     * 新功能。
     */
    NEW_FEATURE(1),
    /**
     * 修复缺陷。
     */
    BUGFIX(2),
    /**
     * 设计。
     */
    DESIGN(3),
    /**
     * 文档。
     */
    DOCS(4),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
