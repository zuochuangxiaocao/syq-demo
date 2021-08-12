package com.syq.demo.rocketmq.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * 普通消息
 */
@RestController
public class ProducerController {

    /**
     * 1.同步发送：同步发送是指消息发送方发出一条消息后，会在收到服务端返回响应之后才发下一条消息的通讯方式。
     * 此种方式应用场景非常广泛，例如重要通知邮件、报名短信通知、营销短信系统等。
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @GetMapping("/s1")
    public void ss() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("defaultGroup");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();

        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("sz-sxx" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 这里设置需要延时的等级即可,RcoketMQ的延时等级为：
            // 1s，5s，10s，30s，1m，2m，3m，4m，5m，6m，7m，8m，9m，10m，20m，30m，1h，2h。
            // level=0，表示不延时。level=1，表示 1 级延时，对应延时 1s。level=2 表示 2 级延时，对应5s，以此类推。
            msg.setDelayTimeLevel(3);

            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    /**
     * 异步发送：异步发送是指发送方发出一条消息后，不等服务端返回响应，接着发送下一条消息的通讯方式。RocketMQ异步发送，需要实现异步发送回调接口（SendCallback）。消息发送方在发送了一条消息后，不需要等待服务端响应即可发送第二条消息。发送方通过回调接口接收服务端响应，并处理响应结果。
     * 异步发送一般用于链路耗时较长，对响应时间较为敏感的业务场景，例如，您视频上传后通知启动转码服务，转码完成后通知推送转码结果等。
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @GetMapping("/s2")
    public void s2() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("defaultGroup");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 5; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("sz-sxx" /* Topic */,
                    "TagB" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes() /* Message body */
            );
            producer.send(msg,new SendCallback(){
                @Override
                public void onSuccess(SendResult sendResult) {
                    // 消息发送成功。
                    System.out.println( "async send success" );
                }
                @Override
                public void onException(Throwable throwable) {
                    // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理。
                    System.out.println( "async send fail" );
                    System.out.println(throwable.getMessage());
                }
            });

        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();

    }

    /**
     * 单向发送OneWay
     * 适用于某些耗时非常短，但对可靠性要求并不高的场景，例如日志收集。
     * @throws MQClientException
     * @throws UnsupportedEncodingException
     * @throws RemotingException
     * @throws InterruptedException
     * @throws MQBrokerException
     */
    @GetMapping("/s3")
    public void s3() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("defaultGroup");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 5; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("sz-sxx" /* Topic */,
                    "TagB" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            producer.sendOneway(msg);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();

    }

}
