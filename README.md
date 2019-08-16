# mq-monitor
> 基于RabbitMQ的消息监控  
> 后续考虑新增一个定时转发消息等功能

## MQ队列监控

1.支持跨机器、进程、VH之间的队列监控  
2.支持动态自定义消息格式  
3.支持动态修改被监控队列阈值  
4.支持动态增改监控队列  


### 事前工作：  
#### 1.建立数据库: 
`https://github.com/HeyFL/mq-monitor/blob/MQ_Monitor/connection_factory_setting.sql`  
#### 2.配置连接信息(connection_factory_setting表):   

factory_name|mq_address|username|password|virtual_host
---|---|---|---|---
gds-wms|127.0.0.1:5672|mq_pds|pds_dev|gds-wms

#### 3.配置需要监控的队列信息(monitor_queue表)  

queue_name|factory_name|warn_msg_format|warn_msg|warn_who|warn_way|warn_threshold
---|---|---|---|---|---|---
GDS_WMS_DISPATCH_Q_CALLBACK_DEAD|gds-wms| | |1|1|0
GDS_WMS_WMS_Q_TASK_ASYNC_CONSUME_DEAD|gds-wms| | |1|1|0

### **通过定时调度工具,如xx-job等定时调用：** 
http://localhost:8080/getAllQueueCurrentInfoWhichBeyondThreshold
>
>可得到告警消息,以及进行告警(`告警需要根据自己需求自己扩展`)：  
> VH：gds-wms 队列：GDS_WMS_DISPATCH_Q_CALLBACK_DEAD 消息当前消息条数122  超过阈值：0 请及时排查问题  
> VH：gds-wms 队列：GDS_WMS_WMS_Q_TASK_ASYNC_CONSUME_DEAD 消息当前消息条数7  超过阈值：0 请及时排查问题  
> VH：gds-wos-uspdxa 队列：GDS_WMS_WOS_Q_JOB_FORECAST_DEAD 消息当前消息条数6  超过阈值：0 请及时排查问题  
> VH：gds-wms 队列：GDS_WOS_WMS_Q_CALLBACK_DEAD 消息当前消息条数49  超过阈值：0 请及时排查问题  

```