package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
}
