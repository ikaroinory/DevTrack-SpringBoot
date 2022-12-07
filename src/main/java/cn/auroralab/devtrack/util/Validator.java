package cn.auroralab.devtrack.util;

import cn.auroralab.devtrack.exception.system.InvalidParametersException;
import cn.auroralab.devtrack.exception.system.RequiredParametersIsEmptyException;

import java.util.List;

/**
 * 验证器。
 *
 * @author Guanyu Hu
 * @since 2022-12-04
 */
public class Validator {
    /**
     * 非空断言。
     *
     * @param args 参数列表。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    public static void notEmpty(String... args) throws RequiredParametersIsEmptyException {
        for (String arg : args)
            if (arg == null || arg.equals(""))
                throw new RequiredParametersIsEmptyException();
    }

    /**
     * 非空断言。
     *
     * @param args 参数列表。
     * @author Guanyu Hu
     * @since 2022-12-07
     */
    public static void notEmpty(List<?>... args) throws RequiredParametersIsEmptyException {
        for (List<?> arg : args)
            if (arg == null || arg.isEmpty())
                throw new RequiredParametersIsEmptyException();
    }

    /**
     * 断言参数列表不等于指定值。
     *
     * @param num  指定值。
     * @param args 参数列表。
     * @author Guanyu Hu
     * @since 2022-12-05
     */
    public static void notEqual(int num, int... args) throws InvalidParametersException {
        for (int arg : args)
            if (arg == num)
                throw new InvalidParametersException();
    }
}
