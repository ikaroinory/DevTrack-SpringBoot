package cn.auroralab.devtrack.configuration;

import cn.auroralab.devtrack.enumeration.Gender;
import cn.auroralab.devtrack.enumeration.Priority;
import cn.auroralab.devtrack.enumeration.SourceOfDemand;
import cn.auroralab.devtrack.enumeration.TaskType;
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
        registry.addConverter(String.class, TaskType.class, source -> Parseable.parse(Integer.parseInt(source), TaskType.class, TaskType.UNKNOWN));
        registry.addConverter(String.class, Priority.class, source -> Parseable.parse(Integer.parseInt(source), Priority.class, Priority.UNKNOWN));
        registry.addConverter(String.class, SourceOfDemand.class, source -> Parseable.parse(Integer.parseInt(source), SourceOfDemand.class, SourceOfDemand.UNKNOWN));
    }
}
