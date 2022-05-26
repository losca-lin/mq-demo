package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Losca
 * @date 2022/5/26 11:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAmqpTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello,张三——————";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testFanoutExchange() {
        // 队列名称
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirectExchange() {
        // 队列名称
        String exchangeName = "itcast.direct";
        // 消息
        String message = "hello, red!";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "itcast.topic";
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }

    @Test
    public void testSendObject() {
        Map<String,String> map = new HashMap<>();
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        map.put("msg", message);
        map.put("statu", "err");
        // 发送消息
        rabbitTemplate.convertAndSend( "object.queue", map);
    }
}
