package cn.auroralab.devtrack.configuration;

import cn.auroralab.devtrack.enumeration.Gender;
import cn.auroralab.devtrack.util.Parseable;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class EnumConversionConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
        registry.addConverter(String.class, Gender.class, source -> Parseable.parse(Integer.parseInt(source), Gender.class, Gender.OTHERS));
    }
}
