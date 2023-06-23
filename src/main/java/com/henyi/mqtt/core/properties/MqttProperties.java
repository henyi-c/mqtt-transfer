package com.henyi.mqtt.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * mqtt配置
 *
 * @author henyi
 */
@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String url;

    private String username;

    private String password;

    private String clientId;

    private List<SubscribeProperties> subscribes;
}
