
package org.chris.common.mq.mover.domain.query;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caizq
 * @date 2019/6/17
 * @since v1.0.0
 */
@Slf4j
@Data
public class ConnectionFactorySettingQuery {
    private String factoryName;

    private String mqAddress;

    private String username;

    private String password;

    private String virtualHost;
}
