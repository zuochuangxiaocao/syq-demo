package com.example.hengqi.demos.messagequeue.controller;

import com.example.hengqi.demos.messagequeue.util.MqTmc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MqTmc mqTmc;

    @GetMapping("/t1")
    public String T1(){
        System.out.println("推送自动消费");
        MqTmc.getInstance().pushTmc("hahahhahahahha");
        return "result";
    }

    @GetMapping("/t2")
    public String T3(){
        System.out.println("推送不消费");
        MqTmc.getInstance().pushTmcTwo("hahahhahahahha");
        return "result";
    }

    @Value("${test.value.t1}")
    private String test1;

    @GetMapping("/t6")
    public String T6(){
        System.out.println(test1);
        return "result";
    }




}
