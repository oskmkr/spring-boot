package com.oskm;

import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
