package cn.bngel.springcloud.controller;

import cn.bngel.springcloud.springcloud.entities.CommonResult;
import cn.bngel.springcloud.springcloud.entities.Payment;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircleBreakerController {

    private String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("非法参数异常");
        }
        else if (result.getData() == null) {
            throw new NullPointerException("ID無对应记录");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(404, payment, "無对应内容");
    }

}
