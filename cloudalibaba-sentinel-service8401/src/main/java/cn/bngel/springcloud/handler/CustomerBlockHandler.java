package cn.bngel.springcloud.handler;

import cn.bngel.springcloud.springcloud.entities.CommonResult;
import cn.bngel.springcloud.springcloud.entities.Payment;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    public static CommonResult handlerException01(BlockException blockException) {
        return new CommonResult(1301, new Payment(2021L, "serial0001"), "customr Exception");
    }

    public static CommonResult handlerException02(BlockException blockException) {
        return new CommonResult(1302, new Payment(2021L, "serial0002"), "customr Exception");
    }
}
