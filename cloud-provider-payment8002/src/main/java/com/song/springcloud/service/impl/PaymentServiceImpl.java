package com.song.springcloud.service.impl;

import com.song.springcloud.dao.PaymentDao;
import com.song.springcloud.entities.Payment;
import com.song.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    
    @Resource
    private PaymentDao paymentDao;
    
    public int create(Payment payment){
        return paymentDao.create(payment);
    }
    
    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}
