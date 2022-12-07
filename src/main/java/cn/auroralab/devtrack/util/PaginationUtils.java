package cn.auroralab.devtrack.util;

import com.github.pagehelper.PageInfo;

/**
 * 分页工具。
 *
 * @author Xiaotong Wu, Guanyu Hu
 * @since 2022-11-19
 */
public class PaginationUtils {
    /**
     * 生成分页信息。
     *
     * @author Xiaotong Wu, Guanyu Hu
     * @since 2022-11-19
     */
    public static <DataType> PageInformation<DataType> parsePageInformation(PageInfo<DataType> pageInfo) {
        PageInformation<DataType> pageInformation = new PageInformation<>();
        pageInformation.setCurrent(pageInfo.getPageNum());
        pageInformation.setSize(pageInformation.getSize());
        pageInformation.setRecordCount(pageInfo.getTotal());
        pageInformation.setPageCount(pageInfo.getPages());
        pageInformation.setList(pageInfo.getList());
        return pageInformation;
    }
}
