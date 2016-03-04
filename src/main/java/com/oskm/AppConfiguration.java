package com.oskm;

import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.Filter;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * Created by oskm on 2015-12-31.
 */
@Configuration
public class AppConfiguration {

    @Bean(name = "freeMarkerViewResolver")
    public ViewResolver getFreeMarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".ftl");
        resolver.setOrder(1);
        resolver.setContentType("text/html");
        resolver.setCache(true);
        return resolver;
    }

    @Bean(name = "freemarkerConfig")
    public FreeMarkerConfigurer getFreemarkerConfig() throws IOException, TemplateException {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setTemplateLoaderPaths("/"); // prevents FreeMarkerConfigurer from using its default path allowing setPrefix to work as expected
        return result;
    }

    /**
     * responseBodyConverter는 결과를 출력시에 강제로 UTF-8로 설정하는 역할
     * @return
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    /**
     * characterEncodingFilter는 POST 요청시에 한글이 깨지는 문제를 보완
     * @return
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;
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
