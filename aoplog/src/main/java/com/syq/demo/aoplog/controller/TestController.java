package com.syq.demo.aoplog.controller;

import com.syq.demo.aoplog.annotation.SysLog;
import com.syq.demo.aoplog.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@RestController
public class TestController {


    @SysLog
    @GetMapping("/t1")
    public void ss(@RequestBody User user) throws InterruptedException {

        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(simpleFormatter.format(new Date()));

        System.out.println("t1----------------------------");

        Thread.sleep(1000L);

        System.out.println("t2----------------------------");

        System.out.println(simpleFormatter.format(new Date()));

    }


}
