package cn.bngel.springcloud.springcloud.controller;

import cn.bngel.springcloud.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import cn.bngel.springcloud.springcloud.service.PaymentService;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public CommonResult paymentZk() {
        return new CommonResult(200, UUID.randomUUID(), "ZooKeeper 访问成功, port: " + serverPort);
    }
}
