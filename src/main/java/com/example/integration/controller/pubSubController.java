package com.example.integration.controller;

import com.example.integration.service.publisher;
import com.example.integration.service.subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class pubSubController {
    @Autowired
    publisher publisher;

    @GetMapping("/test")
    public void test(){
        System.out.println("controller test");
    }

    @PostMapping("/pub")
    public void pub(@PathParam("message") String message) throws Exception{
        //System.out.println("pub");
        publisher.pub(message);
    }
    @GetMapping("/sub")
    public void sub(){
        //System.out.println("sub");
        subscriber.subscribeAsyncExample();
    }
}