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
public class conusmerTransaction {

    private DefaultMQPushConsumer consumer = null;

    */
/**
     * 事务消息
     *//*

    @PostConstruct
    public void initMQConsumer() {
        consumer = new DefaultMQPushConsumer("tx_producer_group");
        consumer.setNamesrvAddr("localhost:9876");
        try {
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe("pay_tx_topic", "*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    System.out.println("mq--sssssssssssssssssssssssssssssssssssssssssss");
                    for (MessageExt msg : msgs) {
                        int count = msg.getReconsumeTimes();
                        System.out.println("重试次数="+count);
                        System.out.println("Message Received: " + new String(msg.getBody()));
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
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
