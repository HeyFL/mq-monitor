package org.chris.common.mq.mover.service;

import org.chris.common.mq.mover.domain.bo.QueueWarnInfo;

import java.util.List;

/**
 * @author caizq
 * @date 2019/6/18
 * @since v1.0.0
 */
public interface IWarningService {
    /**
     *
     * @param queueWarnInfoList
     */
    List<String> getWarnMsg(List<QueueWarnInfo> queueWarnInfoList);

    void doWarn(List<String> warnMsgs);
}
