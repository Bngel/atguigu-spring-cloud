package cn.bngel.springcloud.springcloud.service.impl;

import cn.bngel.springcloud.springcloud.dao.PaymentDao;
import cn.bngel.springcloud.springcloud.entities.Payment;
import cn.bngel.springcloud.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
