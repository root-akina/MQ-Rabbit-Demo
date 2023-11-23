import com.itheima.publisher.PublisherApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = PublisherApplication.class)
public class testMQ {

    @Autowired
    private RabbitTemplate rabbit;

    @Test
    public void simpleQueue(){

        String queue = "simple.queue";
        String msg = "hello  since: "+ LocalDateTime.now();
        rabbit.convertAndSend(queue,msg);
    }

    /**
     * 多线程发送
     */
    @Test
    public void workQueue(){

        String queue = "work.queue";
        String msg = "hello  since: "+ LocalDateTime.now();
        for (int i = 0; i < 50; i++) {
            rabbit.convertAndSend(queue,msg);
        }
    }


    /**
     * hmall.fanout 广播发送，所有绑定的queue都可以接收到消息
     */
    @Test
    public void fanoutExchange(){
        String exchangeName = "hmall.fanout";
        String msg = "hello fanout..."+ LocalDate.now();
        rabbit.convertAndSend(exchangeName,"",msg);
    }

    //direct 广播发送，但有 routingKey 关键字 包含关键字的消息才会接受
    @Test
    public void directExchange(){
        String exchangeName = "hmall.direct";
        String routingKey = "blue";
        String msg = "hello direct.."+LocalDate.now();
        rabbit.convertAndSend(exchangeName,routingKey,msg);
    }

    //Topic 广播模式  有routingKey  可以使用通配符   #：匹配一个或多个词   *只能一个
    @Test
    public void topicExchange(){
        String exchangeName = "hmall.topic";
        String routingKey ="red.news";
        String msg = "hello ..topic.."+LocalDate.now();
        rabbit.convertAndSend(exchangeName,routingKey,msg);
    }

    @Test
    public void objectQueue(){
        String queueName = "Object.queue";
        Map<String,Object> map = new HashMap<>();
        map.put("name","旃檀");
        map.put("age",21);
        rabbit.convertAndSend(queueName,map);
    }

    @Test
    public void timeNow(){
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now());
    }
}
