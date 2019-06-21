package org.chris.common.mq.mover.service.impl;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.chris.common.mq.mover.domain.bo.QueueInfo;
import org.chris.common.mq.mover.domain.bo.QueueWarnInfo;
import org.chris.common.mq.mover.domain.dos.MonitorQueue;
import org.chris.common.mq.mover.mapper.IMonitorQueueMapper;
import org.chris.common.mq.mover.service.IMqMonitorService;
import org.chris.common.mq.mover.service.IWarningService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caizq
 * @date 2019/6/17
 * @since v1.0.0
 */
@Slf4j
@Service
public class MqMonitorServiceImpl implements IMqMonitorService, ApplicationContextAware {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private IMonitorQueueMapper monitorQueueMapper;
    @Value("${enable.warning}")
    private Boolean needWarn;

    @Autowired
    private IWarningService warningService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getMsgCount(QueueInfo queueInfo) {

        RabbitAdmin rabbitAdmin = new RabbitAdmin((ConnectionFactory) applicationContext.getBean(queueInfo.getFactoryName()));
        AMQP.Queue.DeclareOk declareOk = rabbitAdmin.getRabbitTemplate().execute(channel ->
                channel.queueDeclarePassive(queueInfo.getQueueName())
        );
        return declareOk.getMessageCount();
    }

    @Override
    public List<QueueWarnInfo> getAllQueueCurrentInfo() {
        List<MonitorQueue> monitorQueueList = monitorQueueMapper.findByAll(null);
        if (CollectionUtils.isEmpty(monitorQueueList)) {
            return new ArrayList<>();
        }

        //根据数据库配置  检查所有被监控的队列当前存在的消息数
        List<QueueWarnInfo> result = getQueueWarnInfos(monitorQueueList);

        //根据需要  发送提醒给对应的人
        if (Boolean.TRUE.equals(needWarn)) {
            doWarning(result);
        }
        return result;
    }

    private void doWarning(List<QueueWarnInfo> result) {
        if (CollectionUtils.isEmpty(result)) {
            return;
        }

        for (QueueWarnInfo queue : result) {
            warningService.warn(queue);
        }
    }

    private List<QueueWarnInfo> getQueueWarnInfos(List<MonitorQueue> monitorQueueList) {
        List<QueueWarnInfo> result = new LinkedList<>();
        for (MonitorQueue monitorQueue : monitorQueueList) {
            ConnectionFactory connectionFactory = getConnectionFactory(monitorQueue);
            if (connectionFactory == null) {
                //没有配置队列所属的connectionFactory 已打错误日志   这里先跳过  不作处理
                break;
            }

            QueueWarnInfo queueWarnInfo = getQueueMonitorInfo(monitorQueue, connectionFactory);
            result.add(queueWarnInfo);
        }
        return result;
    }

    /**
     * 获取被监控的队列当前信息  以及预警配置  阈值Threshold
     *
     * @param monitorQueue
     * @param connectionFactory
     * @return
     */
    private QueueWarnInfo getQueueMonitorInfo(MonitorQueue monitorQueue, ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        QueueWarnInfo queueWarnInfo = new QueueWarnInfo();
        AMQP.Queue.DeclareOk declareOk = rabbitAdmin.getRabbitTemplate().execute(channel ->
                channel.queueDeclarePassive(monitorQueue.getQueueName())
        );

        queueWarnInfo.setMsgCount(declareOk.getMessageCount());
        queueWarnInfo.setVirtualHost(connectionFactory.getVirtualHost());
        queueWarnInfo.setQueueName(monitorQueue.getQueueName());
        queueWarnInfo.setWarnMsg(monitorQueue.getWarnMsg());
        queueWarnInfo.setWarnMsgFormat(monitorQueue.getWarnMsgFormat());
        queueWarnInfo.setWarnWay(monitorQueue.getWarnWay());
        queueWarnInfo.setWarnWho(monitorQueue.getWarnWho());
        queueWarnInfo.setWarnThreshold(monitorQueue.getWarnThreshold());
        return queueWarnInfo;
    }

    private ConnectionFactory getConnectionFactory(MonitorQueue monitorQueue) {
        ConnectionFactory connectionFactory = null;
        try {
            connectionFactory = (ConnectionFactory) applicationContext.getBean(monitorQueue.getFactoryName());
        } catch (NoSuchBeanDefinitionException e) {
            log.error("找不到Bean{}", monitorQueue.getFactoryName(), e);
        }
        return connectionFactory;
    }

}
