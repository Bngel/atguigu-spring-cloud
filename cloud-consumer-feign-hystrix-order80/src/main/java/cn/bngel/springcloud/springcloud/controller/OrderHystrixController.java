package cn.bngel.springcloud.springcloud.controller;

import cn.bngel.springcloud.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }


    /*@HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            // 设置超时时间峰值
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })*/
    // @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_Timeout(id);
    }

    public String paymentInfo_TimeoutHandler(@PathVariable("id") Integer id) {
        return "加载超时";
    }

    public String payment_Global_FallbackMethod() {
        return "全局异常处理";
    }
}
