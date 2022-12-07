package cn.auroralab.devtrack.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 跳过Jwt拦截器。
 *
 * @author Guanyu Hu
 * @since 2022-11-11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipTokenVerification {
    boolean required() default true;
}
