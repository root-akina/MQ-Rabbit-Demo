package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {


    //创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("bean.fanout");
    }

    //创建队列

    @Bean
    public Queue fanoutQueue3(){
        return new Queue("fanout.queue3");
    }
    @Bean
    public Queue fanoutQueue4(){
        return new Queue("fanout.queue4");
    }

    //绑定交换机

    @Bean
    public Binding bindingFanout3(Queue fanoutQueue3,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }
    @Bean
    public Binding bindingFanout4(Queue fanoutQueue4,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue4).to(fanoutExchange);
    }


}
