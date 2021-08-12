package com.syq.demo.rocketmq.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * 事务消息
 */
@RestController
public class ProducerTransactionController {


    @GetMapping("/t1")
    public void ss() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer transactionProducer = new TransactionMQProducer("tx_producer_group");

        // 设置NameServer的地址
        transactionProducer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        transactionProducer.setTransactionListener(new TransactionListenerLocal());
        transactionProducer.start();

        Message msg = new Message("pay_tx_topic"  ,
                "TagX"  ,
                ("Hello RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        transactionProducer.sendMessageInTransaction(msg,"111");

      /*  TransactionMQProducer transactionProducer=new
                TransactionMQProducer("tx_producer_group");
        transactionProducer.setNamesrvAddr("localhost:9876");
        ExecutorService executorService = newFixedThreadPool(10);
        //自定义线程池，用于异步执行事务操作
       // transactionProducer.setExecutorService(executorService);
        transactionProducer.setTransactionListener(new TransactionListenerLocal());
        transactionProducer.start();
        for(int i=0;i<5;i++) {
            String orderId= UUID.randomUUID().toString();
            String body="{'operation':'doOrder','orderId':'"+orderId+"'}";
            Message message = new Message("pay_tx_topic", "TagA",orderId,body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            transactionProducer.sendMessageInTransaction(message,"111");
           // Thread.sleep(1000);
        }*/
    }

}
