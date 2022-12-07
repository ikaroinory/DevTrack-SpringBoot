package cn.auroralab.devtrack.util;

import cn.auroralab.devtrack.exception.system.EnumMappingException;

import java.util.Arrays;
import java.util.Optional;

/**
 * 可映射接口，实现该接口可通过反射查找指定成员。
 *
 * @author Guanyu Hu
 * @since 2022-12-04
 */
public interface Parseable extends Mappable {
    private static <Enumeration extends Mappable> Optional<Enumeration> filterEnum(int identificationCode, Class<Enumeration> clazz) {
        return Arrays.stream(clazz.getEnumConstants()).filter(enumeration -> enumeration.getIdentificationCode() == identificationCode).findAny();
    }

    /**
     * 根据识别码获取枚举成员。
     *
     * @param identificationCode 识别码。
     * @param clazz              枚举类。
     * @return 枚举成员。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    static <Enumeration extends Mappable> Enumeration parse(int identificationCode, Class<Enumeration> clazz)
            throws EnumMappingException {
        Optional<Enumeration> any = filterEnum(identificationCode, clazz);
        return any.orElseThrow(EnumMappingException::new);
    }

    /**
     * 根据识别码获取枚举成员。
     *
     * @param identificationCode 识别码。
     * @param clazz              枚举类。
     * @param defaultEnum        无法映射时返回的默认枚举。
     * @return 枚举成员。
     * @author Guanyu Hu
     * @since 2022-12-04
     */
    static <Enumeration extends Mappable> Enumeration parse(int identificationCode, Class<Enumeration> clazz, Enumeration defaultEnum) {
        Optional<Enumeration> any = filterEnum(identificationCode, clazz);
        return any.orElse(defaultEnum);
    }
}
