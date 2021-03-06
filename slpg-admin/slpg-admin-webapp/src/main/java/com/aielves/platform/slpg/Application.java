package com.aielves.platform.slpg;

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
@ComponentScan(basePackages = {"com.soho", "com.aielves.platform"})
public class Application extends DefaultServletInitializer {

    @Bean
    public PropertyPlaceholderConfigurer initPropertyPlaceholderConfigurer() {
        String[] decodeKeys = new String[]{"spring.datasource.username", "spring.datasource.password", "oss.appId", "oss.appKey", "ggk.appId", "ggk.appKey",};
        return super.initPropertyPlaceholderConfigurer(null, decodeKeys); // 本地启动方式
    }

    @Bean
    public CacheManager initCacheManager() {
        return super.initCacheManager(); // 本地缓存模式(EhCache)
    }

}