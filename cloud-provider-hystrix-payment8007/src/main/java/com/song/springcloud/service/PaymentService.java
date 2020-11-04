package com.song.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName()
                + " paymentInfo_OK, id: " + id
                + "\tO(∩_∩)O哈哈~";
    }
    
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        // 这个方法是测试熔断的方法，这里会超时3秒，测试是否熔断
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //int age = 10 / 0;
        return "线程池: " + Thread.currentThread().getName()
                + " paymentInfo_Timeout, id: " + id
                + "\tO(∩_∩)O哈哈~"
                + "\t耗时(秒): " + timeNumber;
    }
    
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName()
                + " 系统繁忙或者运行报错，请稍后再试。id: " + id
                + "\to(╥﹏╥)o";
    }
    
    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 失败率达到多少后跳闸
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("***** id不能为负数 *****");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }
    
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "***** id不能为负数，请稍后再试。o(╥﹏╥)o \t id: " + id + " *****";
    }
}
