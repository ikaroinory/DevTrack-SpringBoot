package cn.auroralab.devtrack.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

/**
 * Spring上下文工具。
 *
 * @author Guanyu Hu
 * @since 2022-11-11
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
