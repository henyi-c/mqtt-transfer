package com.henyi.mqtt.core.properties;

import lombok.Data;

/**
 * 获取topic/qos
 *
 * @author henyi
 */
@Data
public class SubscribeProperties {

    private String topic;
    private Integer qos;
}
