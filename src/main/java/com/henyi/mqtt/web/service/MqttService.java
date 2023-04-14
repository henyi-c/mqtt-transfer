package com.henyi.mqtt.web.service;

import com.henyi.mqtt.web.dto.PublishDto;
import com.henyi.mqtt.web.vo.PublishVo;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * mqtt
 *
 * @author henyi
 */
public interface MqttService {

    /**
     * 发布消息
     *
     * @param publishDto dto对象
     * @return PublishVo
     */
    PublishVo publish(PublishDto publishDto) throws MqttException;

}
