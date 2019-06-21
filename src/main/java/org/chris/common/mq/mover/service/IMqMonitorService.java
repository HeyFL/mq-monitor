package org.chris.common.mq.mover.service;

import org.chris.common.mq.mover.domain.bo.QueueInfo;
import org.chris.common.mq.mover.domain.bo.QueueWarnInfo;

import java.util.List;

/**
 * @author caizq
 * @date 2018/4/8
 * @since v1.0.0
 */
public interface IMqMonitorService {
    /**
     * 队列获取消息数量
     *
     * @param queueInfo
     * @return
     */
    int getMsgCount(QueueInfo queueInfo);

    /**
     * @return
     */
    List<QueueWarnInfo> getAllQueueCurrentInfo();
}
