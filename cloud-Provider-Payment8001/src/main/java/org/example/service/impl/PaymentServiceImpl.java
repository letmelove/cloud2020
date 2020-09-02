package org.example.service.impl;

import org.example.dao.PaymentDao;
import org.example.service.PaymentService;
import org.example.pojo.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {

        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {

        return paymentDao.getPaymentById(id);
    }

    @Override
    public List<Payment> getPaymentAll() {
        return paymentDao.getPaymentAll();
    }
}
