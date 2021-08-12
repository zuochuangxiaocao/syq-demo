package com.syq.demo.rocketmq.conusmer;/*
package com.sxx.mybatisplus.conusmer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class conusmer2 {

    private DefaultMQPushConsumer consumer = null;

    */
/**
     * 消费顺序消息
     *//*

    @PostConstruct
    public void initMQConsumer() {
        consumer = new DefaultMQPushConsumer("shunxu");
        consumer.setNamesrvAddr("localhost:9876");
        try {
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe("OrderTopic1", "*");
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    try {
                        System.out.println("orderInfo: " + new String(msgs.get(0).getBody(), "utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
            System.out.println("mq-----------------------------");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void shutDownConsumer() {
        if (consumer != null) {
            consumer.shutdown();
        }
    }
}
*/
