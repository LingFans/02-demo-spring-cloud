package com.song.myrib;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mi
 */
@Configuration
public class MySelfRibbonRule {
    
    @Bean
    public IRule myRibbonRule(){
        // 随机规则
        return new RandomRule();
    }
}
