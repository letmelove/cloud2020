package org.example.service;

import org.example.pojo.Payment;

import java.util.List;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Integer id);
    List<Payment> getPaymentAll();
}
