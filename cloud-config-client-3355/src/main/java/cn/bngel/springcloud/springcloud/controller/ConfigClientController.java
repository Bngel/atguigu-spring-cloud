package cn.bngel.springcloud.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/config_info")
    public String getConfigInfo() {
        return "port:" + serverPort + " info: " + configInfo;
    }
}
