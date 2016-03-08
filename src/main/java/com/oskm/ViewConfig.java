package com.oskm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

/**
 * Created by oskmkr on 2016-03-05.
 */
@Configuration
public class ViewConfig {

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        //configurer.setTemplateLoaderPath("classpath:/views/ftl/");
        configurer.setTemplateLoaderPath("/WEB-INF/views/ftl/");
        Properties freemarkerSettings = new Properties();
        freemarkerSettings.setProperty("default_encoding", "UTF-8");
        configurer.setFreemarkerSettings(freemarkerSettings);

        return configurer;
    }

    @Bean(name = "freeMarkerViewResolver")
    public ViewResolver getFreeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        //resolver.setPrefix("views/");
        resolver.setSuffix(".ftl");
        resolver.setOrder(1);
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setCache(false);
        return resolver;
    }

        /*
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        resolver.setContentType("text/html");
        return resolver;
    }
    */
}
