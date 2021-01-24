package org.example.service;

import com.tx.pojo.Payment;

import java.util.List;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Integer id);
    List<Payment> getPaymentAll();
}
