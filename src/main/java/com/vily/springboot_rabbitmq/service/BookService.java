package com.vily.springboot_rabbitmq.service;

import com.vily.springboot_rabbitmq.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-06-05
 *  
 **/

@Service
public class BookService {


    @RabbitListener(queues = "hluffy")
    public void receive(Book book){


        System.out.println("消息："+book);
    }
}
