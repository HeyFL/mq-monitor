package org.chris.common.mq.mover.domain.dos;

import java.io.Serializable;
import lombok.Data;

@Data
public class ConnectionFactorySetting implements Serializable {
    /**
     * 本连接的名称
     */
    private String factoryName;

    /**
     * MQ IP地址
     */
    private String mqAddress;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * MQ的VH
     */
    private String virtualHost;

}