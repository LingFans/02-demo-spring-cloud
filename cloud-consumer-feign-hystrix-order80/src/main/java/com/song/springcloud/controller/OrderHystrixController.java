package com.song.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.song.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
    
    @Autowired
    private PaymentHystrixService paymentHystrixService;
    
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }
    
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(
            fallbackMethod = "paymentInfoTimeOutFallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
            }
    )*/
    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
//        int age = 10 / 0;
        return paymentHystrixService.paymentInfo_Timeout(id);
    }
    
    public String paymentInfoTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙 请10秒钟后再试。或者自己运行出错，请检查自己。o(╥﹏╥)o";
    }
    
    // 下面是全局fallback方法
    public String paymentGlobalFallbackMethod(){
        return "Global异常处理信息，请稍后再试。o(╥﹏╥)o";
    }
}
