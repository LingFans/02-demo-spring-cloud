package com.song.springcloud.controller;

import com.song.springcloud.entities.CommonResult;
import com.song.springcloud.entities.Payment;
import com.song.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OpenFeignOrderController {
    
    @Autowired
    private PaymentFeignService paymentFeignService;
    
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
    
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        // openfeign-ribbon, 客户端一般默认等待一秒钟
        return paymentFeignService.paymentFeignTimeOut();
    }
}
