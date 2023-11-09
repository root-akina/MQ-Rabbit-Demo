package com.itheima.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleListener {
    // 利用RabbitListener来声明要监听的队列信息
    // 将来一旦监听的队列中有了消息，就会推送给当前服务，调用当前方法，处理消息。
    // 可以看到方法体中接收的就是消息体的内容
    @RabbitListener(queues = "simple.queue")
    public void simpleQueue(String msg){
        System.out.println("Simple.queue: {"+msg+"}");
    }

    //work。queue 共享消息池
    @RabbitListener(queues = "work.queue")
    public void workQueue1(String msg) throws InterruptedException {
        System.out.println("work.queue 1 : {"+msg+"}");
        Thread.sleep(20);
    }
    @RabbitListener(queues = "work.queue")
    public void workQueue2(String msg) throws InterruptedException {
        System.out.println("work.queue 2 : {"+msg+"}");
        Thread.sleep(200);
    }
}
