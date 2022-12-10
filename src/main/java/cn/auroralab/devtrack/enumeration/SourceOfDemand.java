package cn.auroralab.devtrack.enumeration;

import cn.auroralab.devtrack.util.Parseable;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 需求来源。
 *
 * @author Guanyu Hu
 * @since 2022-11-22
 */
@AllArgsConstructor
public enum SourceOfDemand implements Parseable {
    /**
     * 未知。
     */
    UNKNOWN(0),
    /**
     * 研发岗。
     */
    RD_POST(1),
    /**
     * 测试岗。
     */
    TEST_POST(2),
    /**
     * 设计岗。
     */
    DESIGN_POST(3),
    ;

    @EnumValue
    @JsonValue
    public final int code;

    public int getIdentificationCode() {
        return code;
    }
}
