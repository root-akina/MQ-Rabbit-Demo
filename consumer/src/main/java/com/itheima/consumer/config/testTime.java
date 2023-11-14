package com.itheima.consumer.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class testTime {

    @Test
    public void timeT(){
        System.out.println(LocalDateTime.now());
    }
}
