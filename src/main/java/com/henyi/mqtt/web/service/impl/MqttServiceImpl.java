package com.henyi.mqtt.web.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.henyi.mqtt.core.clients.MqttClient;
import com.henyi.mqtt.core.domain.PublishRes;
import com.henyi.mqtt.web.dto.PublishDto;
import com.henyi.mqtt.web.service.MqttService;
import com.henyi.mqtt.web.vo.PublishVo;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * mqtt相关业务
 *
 * @author henyi
 */

@Service
public class MqttServiceImpl implements MqttService {

    @Resource
    private MqttClient mqttClient;

    @Override
    public PublishVo publish(PublishDto publishDto) throws MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload(publishDto.getContent().getBytes(StandardCharsets.UTF_8));
        message.setQos(publishDto.getQos());
        //设置是否在服务器中保存消息体,保留最新的消息到服务器上,以免订阅时丢失上一次最新的消息
        message.setRetained(true);
        PublishRes publishRes = mqttClient.publish(publishDto.getTopic(), message);
        return Convert.convert(new TypeReference<PublishVo>() {
        }, publishRes);
    }
}
