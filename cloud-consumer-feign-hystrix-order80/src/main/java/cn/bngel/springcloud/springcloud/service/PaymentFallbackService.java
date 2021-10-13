package cn.bngel.springcloud.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "---PaymentFallbackService fall paymentInfo_OK---";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "---PaymentFallbackService fall paymentInfo_Timeout---";
    }
}
