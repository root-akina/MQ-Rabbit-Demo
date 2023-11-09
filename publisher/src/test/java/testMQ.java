import com.itheima.publisher.PublisherApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
