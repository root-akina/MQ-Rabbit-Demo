package com.itheima.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    //创建DirectExchange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("bean.direct");
    }


    //创建Queue
    @Bean
    public Queue directQueue3(){
        return new Queue("direct.queue3");
    }
    @Bean
    public Queue directQueue4(){
        return new Queue("direct.queue4");
    }
    //绑定交换机
    @Bean
    public Binding bindingDirect1red(Queue directQueue3, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue3).to(directExchange).with("red");
    } @Bean
    public Binding bindingDirect1Blue(Queue directQueue3, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue3).to(directExchange).with("blue");
    }

    @Bean
    public Binding bindingDirect2red(Queue directQueue4, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue4).to(directExchange).with("red");
    } @Bean
    public Binding bindingDirect2Yellow(Queue directQueue4, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue4).to(directExchange).with("yellow");
    }
}
