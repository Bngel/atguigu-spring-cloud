package cn.bngel.springcloud.controller;

import cn.bngel.springcloud.springcloud.entities.CommonResult;
import cn.bngel.springcloud.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<Long, Payment>();
    static {
        hashMap.put(1L, new Payment(1L, "123123"));
        hashMap.put(2L, new Payment(2L, "123234"));
        hashMap.put(3L, new Payment(3L, "123345"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult<>(200, payment, "from mysql, serverPort: " + serverPort);
    }
}
