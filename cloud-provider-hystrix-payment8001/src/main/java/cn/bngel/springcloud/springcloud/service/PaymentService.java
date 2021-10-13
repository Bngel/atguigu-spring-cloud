package cn.bngel.springcloud.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_OK, id:\t" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            // 设置超时时间峰值
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) throws Exception{
        Thread.sleep(5000);
        return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_Timeout, id:\t" + id + "\t 耗时:\t5s";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池:\t" + Thread.currentThread().getName() + "\tpaymentInfo_TimeoutHandler, id:\t" + id;
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期, 半开状态
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到60%后发生断路
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("------- id 不能为负数 --------");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功, 流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能为负数, 请稍后再试. id: " + id;
    }
}
