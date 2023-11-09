import com.itheima.publisher.PublisherApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
