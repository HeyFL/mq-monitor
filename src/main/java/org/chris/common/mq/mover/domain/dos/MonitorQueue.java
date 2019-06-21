package org.chris.common.mq.mover.domain.dos;

import java.io.Serializable;
import lombok.Data;

@Data
public class MonitorQueue implements Serializable {
    private String queueName;

    private String factoryName;

    private String warnMsgFormat;

    private String warnMsg;

    private String warnWho;

    private String warnWay;

    private Integer warnThreshold;

    private static final long serialVersionUID = 1L;
}