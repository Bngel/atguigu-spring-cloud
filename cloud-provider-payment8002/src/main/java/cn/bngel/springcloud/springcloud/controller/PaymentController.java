package cn.bngel.springcloud.springcloud.controller;

import cn.bngel.springcloud.springcloud.entities.CommonResult;
import cn.bngel.springcloud.springcloud.entities.Payment;
import cn.bngel.springcloud.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("----插入结果: " + result + "----");
        if (result > 0) return new CommonResult(200, result, "插入数据库成功 port: " + serverPort);
        else return new CommonResult(400, "插入数据库失败");
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("----查询结果: " + result.toString() + "----");
        if (result != null) return new CommonResult(200, result, "查询成功 port: " + serverPort);
        else return new CommonResult(400, "没有对应记录, 查询ID:" + id);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
