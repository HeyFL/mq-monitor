package org.chris.common.mq.mover.domain.dos;

import java.io.Serializable;
import lombok.Data;

@Data
public class ConnectionFactorySetting implements Serializable {
    private String factoryName;

    private String mqAddress;

    private String username;

    private String password;

    private String virtualHost;

}