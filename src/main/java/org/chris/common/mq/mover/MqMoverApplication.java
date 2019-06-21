package org.chris.common.mq.mover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

/**
 * @author caizq
 * @date 2018/4/8
 * @since v1.0.0
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={RabbitAutoConfiguration.class})
public class MqMoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqMoverApplication.class, args);
    }
}
