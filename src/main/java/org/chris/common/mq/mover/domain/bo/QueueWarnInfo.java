
package org.chris.common.mq.mover.domain.bo;

import lombok.Data;

/**
 * @author caizq
 * @date 2019/6/17
 * @since v1.0.0
 */
@Data
public class QueueWarnInfo {
    private String queueName;
    private Integer msgCount;
    private String virtualHost;

    private String warnMsgFormat;
    private String warnMsg;
    private String warnWho;
    private String warnWay;
    private Integer warnThreshold;
}
