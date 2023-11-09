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

    //fanout 广播消息
    @RabbitListener(queues = "fanout.queue1")
    public void fanoutQueue1(String msg) {
        System.out.println("fanout.queue 1 ：{"+msg+"}");
    }
    @RabbitListener(queues = "fanout.queue2")
    public void fanoutQueue2(String msg) {
        System.out.println("fanout.queue 2 ：{"+msg+"}");
    }

    //direct 广播消息 有 routingKey
    @RabbitListener(queues = "direct.queue1")
    public void directQueue1(String msg){
        System.out.println("hello Direct.queue 1 : {"+msg+"}");
    }
    @RabbitListener(queues = "direct.queue2")
    public void directQueue2(String msg){
        System.out.println("hello Direct.queue 2: {"+msg+"}");
    }

    //topic 模式
    @RabbitListener(queues = "topic.queue1")
    public void topicQueue1(String msg){
        System.out.println("hello Topic.queue 1.: { "+msg+"}");
    }
    @RabbitListener(queues = "topic.queue2")
    public void topicQueue2(String msg){
        System.out.println("hello Topic.queue 2 .: { "+msg+"}");
    }
}
