package cn.bngel.springcloud.controller;

import cn.bngel.springcloud.handler.CustomerBlockHandler;
import cn.bngel.springcloud.springcloud.entities.CommonResult;
import cn.bngel.springcloud.springcloud.entities.Payment;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, new Payment(2020L, "serial001"), "FlowLimitByResource OK");
    }

    public CommonResult handleException(BlockException blockException) {
        return new CommonResult(444, "Service Down - ByResource");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, new Payment(2020L, "serial002"), "FlowLimitByUrl OK");
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerException02")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, new Payment(2020L, "serial003"), "FlowLimitByCustomerBlockHandler OK");
    }
}
