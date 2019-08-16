
package org.chris.common.mq.mover.domain.bo;

import lombok.Data;

/**
 * @author caizq
 * @date 2019/6/17
 * @since v1.0.0
 */
@Data
public class QueueWarnInfo {
    /**
     * 队列名称
     */
    private String queueName;
    /**
     * 当前消息条数
     */
    private Integer msgCount;
    /**
     * VH名称
     */
    private String virtualHost;

    /**
     * 告警消息模板
     */
    private String warnMsgFormat;
    /**
     * 告警消息
     */
    private String warnMsg;
    /**
     * 告警谁(如 UserId等)
     */
    private String warnWho;
    /**
     * 告警方式
     */
    private String warnWay;
    /**
     * 告警阈值
     */
    private Integer warnThreshold;
}
