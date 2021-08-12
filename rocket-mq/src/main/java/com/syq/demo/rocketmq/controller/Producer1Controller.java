package com.syq.demo.rocketmq.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 顺序消息
 */
@RestController
public class Producer1Controller {

    /**
     * producer只需要确保消息发送到特定的分区，也就是MessageQueue。通过MessageQueueSelector来实现分区的选择（自定义消息发送规则）
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @GetMapping("/p1")
    public void ss() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("shunxu");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("OrderTopic1" /* Topic */,
                    "TagH" /* Tag */,
                    ("Hello RocketMQ H " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    //此刻arg == orderId,可以保证是每个订单进入同一个队列
                    Integer id = (Integer) o;
                    int index = id % list.size();
                    System.out.println(index);
                    return list.get(index);
                }
            },1);

        }


        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("OrderTopic1" /* Topic */,
                    "TagJ" /* Tag */,
                    ("Hello RocketMQ J " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    //此刻arg == orderId,可以保证是每个订单进入同一个队列
                    Integer id = (Integer) o;
                    int index = id % list.size();
                    System.out.println(index);
                    return list.get(index);
                }
            },2);

        }


        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

}
