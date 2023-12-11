package com.example.hengqi.demos.factorysubmit.controller;

import com.example.hengqi.demos.factorysubmit.util.SubmitChannelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sc")
@RestController
public class SCController {


    @GetMapping("/t1")
    public String t1(){

        String t1 = SubmitChannelUtils.map.get("t1").t1("111");

        return t1;
    }

    @GetMapping("/t2")
    public String t2(){

        String t1 = SubmitChannelUtils.map.get("t2").t1("111");

        return t1;
    }

    @GetMapping("/t3")
    public String t2(String ss){

        String t = SubmitChannelUtils.map.get(ss).t1("111");

        return t;
    }

}
