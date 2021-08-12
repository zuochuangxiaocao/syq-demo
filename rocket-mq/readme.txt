
1. recoketMq 测试项目
2.  下载rocketMQ 项目,下载mq可视化插件
3. 启动mq项目，启动可视化插件
     start mqnamesrv.cmd
     start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true

     启动可视化插件
     用CMD进入‘\rocketmq-externals\rocketmq-console’文件夹，执行‘mvn clean package -Dmaven.test.skip=true’，
     编译生成。
     编译成功之后，Cmd进入‘target’文件夹，执行‘java -jar rocketmq-console-ng-1.0.0.jar’，
     启动‘rocketmq-console-ng-1.0.0.jar

