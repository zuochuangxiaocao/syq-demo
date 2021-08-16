package com.syq.demo.swgger.controller;

import com.syq.demo.swgger.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试api接口",tags = "测试api接口")
public class TController {

    @ApiOperation(value = "t1接口")
    @GetMapping("/t1")
    public void  test1(){
        System.out.println("t1-------------------------------------");
    }


    @ApiOperation(value = "t2接口")
    @GetMapping("/t2")
    public String test2(String name){
        System.out.println("t2------------------------");
        System.out.println("t2------------------------");
        return name;
    }

    @ApiOperation(value = "t3接口")
    @PostMapping("/t3")
    public String test3 (@RequestBody User name){
        return name.toString();
    }

    @ApiOperation(value = "t4接口")
    @PostMapping("/t4")
    public String test4 (User name){
        return name.toString();
    }





}
