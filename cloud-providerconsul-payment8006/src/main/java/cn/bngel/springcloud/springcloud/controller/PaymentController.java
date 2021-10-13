package cn.bngel.springcloud.springcloud.controller;

import cn.bngel.springcloud.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/consul")
    public CommonResult paymentZk() {
        return new CommonResult(200, UUID.randomUUID(), "Consul 访问成功, port: " + serverPort);
    }

}
