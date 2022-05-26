package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Losca
 * @date 2022/5/26 11:37
 */
@Component
public class SpringRabbitListener {
    @RabbitListener(queues = "simple.queue")
    public void listen1(String msg) throws InterruptedException {
        System.out.println("=======================");
        System.out.println("消费者1消费了消息："+msg);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listen2(String msg) throws InterruptedException {
        System.out.println("=======================");
        System.err.println("消费者2消费了消息："+msg);
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void fanoutQueue1(String msg) throws InterruptedException {
        System.err.println("fanout:queue1："+msg);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void fanoutQueue12(String msg) throws InterruptedException {
        System.out.println("=======================");
        System.err.println("fanout:queue2："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者接收到direct.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "green"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者接收到direct.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
        System.out.println("消费者接收到topic.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg){
        System.out.println("消费者接收到topic.queue2的消息：【" + msg + "】");
    }

    @RabbitListener(queues = "object.queue")
    public void objectMessage(Map<String,String> message){
        System.out.println("object的消息"+message);
    }
}
