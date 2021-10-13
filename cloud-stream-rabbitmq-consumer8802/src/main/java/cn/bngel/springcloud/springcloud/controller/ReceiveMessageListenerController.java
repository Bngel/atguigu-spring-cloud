package cn.bngel.springcloud.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("{server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        String msg = message.getPayload();
        log.info("消费者 port: " + port + " 收到消息: " + msg);
    }
}