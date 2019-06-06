package com.vily.springboot_rabbitmq;

import com.vily.springboot_rabbitmq.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testSen() {

        // Message 需要自己狗爪一个，定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routingKey,message);

        // object 默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitMq
//        rabbitTemplate.convertAndSend(exchange,routingKey,object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这个是第一个消息");
        map.put("data", Arrays.asList("hellou", 123, true));


        // vily.direct 为 我在网站上注册的交换机，http://localhost:15672/#/exchanges
        // hluffy  为注册的消息队列
        rabbitTemplate.convertAndSend("vily.direct", "hluffy", map);
    }

    @Test
    public void getMsg() {

        Object hluffy = rabbitTemplate.receiveAndConvert("hluffy");

        System.out.println("--------------------------------");
        System.out.println(hluffy);
//        System.out.println(hluffy.getClass());

    }
    @Test
    public void test01() {
        rabbitTemplate.convertAndSend("vily.direct", "hluffy", new Book(1, "qqq"));

    }


    /**
     *   通过amqpAdmin 创建交换机，创建队列，创建绑定关系等
     */
    @Test
    public void creatExchange(){

        // 创建交换机
        amqpAdmin.declareExchange(new DirectExchange("amqp.direct"));
        // 创建消息队列
        amqpAdmin.declareQueue(new Queue("amqp.queue",true));

        // 绑定交换机和消息队列
        amqpAdmin.declareBinding(new Binding("amqp.queue",
                Binding.DestinationType.QUEUE,
                "amqp.direct",
                "amqp.key",
                null));

    }
}
