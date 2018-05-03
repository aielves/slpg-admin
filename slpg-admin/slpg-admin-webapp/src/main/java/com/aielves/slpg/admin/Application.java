package com.aielves.slpg.admin;

import com.soho.spring.cache.CacheManager;
import com.soho.spring.extend.DefaultServletInitializer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shadow
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.soho", "com.aielves.slpg"})
public class Application extends DefaultServletInitializer {

    @Bean
    public PropertyPlaceholderConfigurer initPropertyPlaceholderConfigurer() {
        return super.initPropertyPlaceholderConfigurer(); // 本地启动方式
    }

    @Bean
    public CacheManager initCacheManager() {
        return super.initCacheManager(); // 本地缓存模式(EhCache)
    }

}