package cn.auroralab.devtrack.vo;

import cn.auroralab.devtrack.enumeration.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 响应值对象。
 *
 * @param <TResponseData> 响应数据对象的类型
 * @author Guanyu Hu
 * @since 2022-10-17
 */
@Data
@AllArgsConstructor
public class ResponseVO<TResponseData> {
    private final StatusCode statusCode;
    private final TResponseData responseData;

    public ResponseVO() {
        this(StatusCode.UNKNOWN, null);
    }
}
