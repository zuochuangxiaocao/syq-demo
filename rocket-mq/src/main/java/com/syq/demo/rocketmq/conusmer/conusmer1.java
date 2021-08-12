package com.syq.demo.rocketmq.conusmer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class conusmer1 {

    private DefaultMQPushConsumer consumer = null;

    /**
     * 消费普通消息
     */
    @PostConstruct
    public void initMQConsumer() {
        consumer = new DefaultMQPushConsumer("defaultGroup");
        consumer.setNamesrvAddr("localhost:9876");
        try {
            consumer.subscribe("sz-sxx", "TagB");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    System.out.println("mq--sssssssssssssssssssssssssssssssssssssssssss");
                    for (MessageExt msg : msgs) {
                        System.out.println("Message Received: " + new String(msg.getBody()));

                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
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

