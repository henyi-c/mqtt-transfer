package com.henyi.mqtt.core.clients;

import com.henyi.mqtt.core.callbacks.PublishCallback;
import com.henyi.mqtt.core.domain.PublishRes;
import com.henyi.mqtt.core.properties.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 发布消息初始化
 *
 * @author henyi
 */
@Slf4j
@Component
public class MqttPublishClient {


    @Resource
    private MqttProperties mqttProperties;
    @Resource
    private MyMqttConnectOptions mqttConnectOptions;
    private MqttClient client;

    @PostConstruct
    public void init() throws MqttException {
        log.info("-----------连接发布初始化----------");
        // MemoryPersistence设置clientId的保存形式，默认为以内存保存
        client = new MqttClient(mqttProperties.getUrl(), mqttProperties.getClientId() + "_publish", new MemoryPersistence());
        client.setCallback(new PublishCallback());
        client.connect(mqttConnectOptions.getOptions());
    }

    //发送消息并获取回执
    public PublishRes publish(String topic, MqttMessage message) throws MqttException {
        log.info("-----------发布topic:" + topic);
        String context = new String(message.getPayload());
        log.info("-----------发布内容:" + context);
        MqttTopic mqttTopic = client.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(message);
        token.waitForCompletion();
        log.info("-----------消息id:" + token.getMessageId());
        token.getResponse();
        PublishRes publishRes = new PublishRes();
        publishRes.setSuccess(token.isComplete());
        publishRes.setTopic(topic);
        publishRes.setQos(message.getQos());
        publishRes.setContent(context);
        return publishRes;
    }
}