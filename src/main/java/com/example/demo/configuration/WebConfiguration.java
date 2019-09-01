package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import java.util.Properties;

/**
 * Intellij Idea
 * Created by ivosahlik on 2019-09-01
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("true")
    private String VELOCITY_ENABLE_AUTORELOAD;

    @Bean
    public ViewResolver getVelocityViewResolver() {
        VelocityLayoutViewResolver velocityViewResolver = new VelocityLayoutViewResolver();
        velocityViewResolver.setPrefix("");
        velocityViewResolver.setSuffix(".vm");
        velocityViewResolver.setCache(false);
        velocityViewResolver.setLayoutUrl("/velocity/views/layouts/default.vm");
        velocityViewResolver.setExposeSpringMacroHelpers(true);
        velocityViewResolver.setContentType("text/html; charset=UTF-8");
        return velocityViewResolver;
    }


    /**
     * Configures velocity resources path.
     *
     * @return velocity configurer instance
     */
    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath("classpath:/velocity/views/");
        velocityConfigurer.setPreferFileSystemAccess(false);
        Properties properties = new Properties();
        properties.setProperty("parser.pool.size", "30");
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        properties.setProperty("directive.if.tostring.nullcheck", "false");
        properties.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
        properties.setProperty("file.resource.loader.cache", "false".equals(VELOCITY_ENABLE_AUTORELOAD) ? "true" : "false");
        properties.setProperty("spring.resource.loader.cache", "false".equals(VELOCITY_ENABLE_AUTORELOAD) ? "true" : "false");
        properties.setProperty("velocimacro.permissions.allow.inline", "true");
        properties.setProperty("velocimacro.permissions.allow.inline.to.replace.global", "false");
        properties.setProperty("velocimacro.permissions.allow.inline.local.scope", "true");
        properties.setProperty("velocimacro.library.autoreload", VELOCITY_ENABLE_AUTORELOAD);
        velocityConfigurer.setVelocityProperties(properties);
        return velocityConfigurer;
    }

}
