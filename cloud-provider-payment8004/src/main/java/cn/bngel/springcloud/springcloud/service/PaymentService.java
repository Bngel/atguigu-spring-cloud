package cn.bngel.springcloud.springcloud.service;

import cn.bngel.springcloud.springcloud.entities.Payment;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
