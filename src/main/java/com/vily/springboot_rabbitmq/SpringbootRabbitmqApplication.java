package com.vily.springboot_rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *   rabbitMQ 自动配置
 *   1：rabbitAutoCnofiguration
 *   2: 有自动配置了连接工厂 ConnectionFactory
 *   3: RabbitProperties 封装了 RabbitMQ 的配置
 *   4：RabbitTemplate : 给 RabbitMQ 发送和接受消息
 *   5：AmqpAdmin : RabbitMQ 系统管理功能组件
 *       AmqpAdmin:创建和删除 Queue,Exchange,Binding
 */
@EnableRabbit  // 开启基于注解的RabbitMQ 模式
@SpringBootApplication
public class SpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqApplication.class, args);
    }

}
