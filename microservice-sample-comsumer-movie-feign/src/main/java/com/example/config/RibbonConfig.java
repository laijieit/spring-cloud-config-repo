package com.example.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanglaijie
 * @since 2019-08-24
 */
//@Configuration
public class RibbonConfig {

    //@Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
