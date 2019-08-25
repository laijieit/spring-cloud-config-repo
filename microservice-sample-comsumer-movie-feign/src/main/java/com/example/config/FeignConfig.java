package com.example.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanglaijie
 * @since 2019-08-24
 */
@Configuration
public class FeignConfig {

    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
}
