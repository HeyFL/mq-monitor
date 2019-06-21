package org.chris.common.mq.mover.config;

import lombok.extern.slf4j.Slf4j;
import org.chris.common.mq.mover.domain.dos.ConnectionFactorySetting;
import org.chris.common.mq.mover.mapper.IConnectionFactorySettingMapper;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author caizq
 * @date 2019/3/8
 * @since v1.0.0
 */
@Slf4j
@Configuration
//@ConditionalOnExpression("${my.configuration.enabled}")
public class MonitorFactoryRegister {
    @Autowired
    private IConnectionFactorySettingMapper connectionFactorySettingMapper;
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 把所有的db中所有的factory配置作为connectionFactory注册到Spring中
     * 这个方法返回Runnable只是一个幌子，最重要的是执行方法里面的代码
     */
    @Bean
    public Runnable dynamicConfiguration() {
        List<ConnectionFactorySetting> connectionFactorySettings = connectionFactorySettingMapper.findByAll(null);
        if (!CollectionUtils.isEmpty(connectionFactorySettings)) {
            for (ConnectionFactorySetting connectionFactorySetting : connectionFactorySettings) {
                ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
                DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

                CachingConnectionFactory connectionFactory = getConnectionFactory(connectionFactorySetting);
                beanFactory.registerSingleton(connectionFactorySetting.getFactoryName(),connectionFactory);
            }
        }
        return null;
    }

    private CachingConnectionFactory getConnectionFactory(ConnectionFactorySetting connectionFactorySetting) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setAddresses(connectionFactorySetting.getMqAddress());
        connectionFactory.setUsername(connectionFactorySetting.getUsername());
        connectionFactory.setPassword(connectionFactorySetting.getPassword());
        connectionFactory.setVirtualHost(connectionFactorySetting.getVirtualHost());
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

}
