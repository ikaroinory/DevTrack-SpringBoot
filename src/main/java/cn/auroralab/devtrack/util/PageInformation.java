package cn.auroralab.devtrack.util;

import lombok.Data;

import java.util.List;

/**
 * 分页信息类。
 *
 * @author Guanyu Hu
 * @since 2022-11-21
 */
@Data
public class PageInformation<DataType> {

    /**
     * 当前页码。
     */
    private int current;
    /**
     * 页面大小。
     */
    private int size;
    /**
     * 记录总数。
     */
    private long recordCount;
    /**
     * 页面总数。
     */
    private int pageCount;
    /**
     * 数据列表。
     */
    private List<DataType> list;
}
