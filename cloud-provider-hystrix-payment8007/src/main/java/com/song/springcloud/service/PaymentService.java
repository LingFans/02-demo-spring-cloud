package com.song.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName()
                + " paymentInfo_OK, id: " + id
                + "\tO(∩_∩)O哈哈~";
    }
    
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {
        // 这个方法是测试熔断的方法，这里会超时3秒，测试是否熔断
        /*int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }*/
        int age = 10 / 0;
        return "线程池: " + Thread.currentThread().getName()
                + " paymentInfo_Timeout, id: " + id
                + "\tO(∩_∩)O哈哈~"
                /*+ "\t耗时(秒): " + timeNumber*/;
    }
    
    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName()
                + " 系统繁忙或者运行报错，请稍后再试。id: " + id
                + "\to(╥﹏╥)o";
    }
}
