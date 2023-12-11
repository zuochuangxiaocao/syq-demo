package com.example.hengqi.demos.messagequeue.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqTmc extends Thread {
    private static StringRedisTemplate redisTemplate;
    private static volatile MqTmc mqTmc = null;

    private static final byte[] lock = new byte[0];
    public String TMCMESSAGE="TmcMessage"; //消息队列key


    public static MqTmc getInstance(){
        if (mqTmc == null) {
            synchronized (lock) {
                if (mqTmc == null) {
                    mqTmc = new MqTmc();
                    mqTmc.start();
                }
            }
        }
        return mqTmc;
    }


    @Autowired(required = true)
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run() {
        System.out.println("启动了-----------------------");
        while (true) {
            try {
                String s = popTMCMessage();
                if (s == null) {
                    synchronized (lock) {
                        lock.wait();
                    }
                } else {
                    System.out.println("消费=================" + s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 拉取消息
     * @return
     */
    public String popTMCMessage() {
        String json=redisTemplate.boundListOps(TMCMESSAGE).rightPop();
        return json;
    }


    /**
     * 推送消息
     * @param message
     */
    public void pushTmc(String message) {
        synchronized (lock) {
            redisTemplate.boundListOps(TMCMESSAGE).leftPush(message);
            lock.notifyAll();
        }
    }

    public void pushTmcTwo(String message) {
        synchronized (lock) {
            redisTemplate.boundListOps(TMCMESSAGE).leftPush(message);
        }
    }

}
