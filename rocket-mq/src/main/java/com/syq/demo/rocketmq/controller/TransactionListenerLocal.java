package com.syq.demo.rocketmq.controller;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class TransactionListenerLocal implements TransactionListener {

    //执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println(":执行本地事务："+o.toString());
        String orderId=o.toString();
        return LocalTransactionState.COMMIT_MESSAGE;
        // 这个返回状态表示告诉broker这个事务消息是否被确认，允许给到consumer进行消费
        // LocalTransactionState.ROLLBACK_MESSAGE 回滚
        //LocalTransactionState.UNKNOW 未知
    }

    //提供事务执行状态的回查方法，提供给broker回调
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String orderId=messageExt.getKeys();
        System.out.println("执行事务执行状态的回查，orderId:"+orderId);
        boolean rs= true;
        System.out.println("回调："+rs);
        return rs?LocalTransactionState.COMMIT_MESSAGE:LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
