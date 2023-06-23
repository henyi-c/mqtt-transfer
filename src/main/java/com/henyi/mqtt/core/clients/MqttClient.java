package com.henyi.mqtt.core.clients;


import com.henyi.mqtt.core.callbacks.MqttCallBack;
import com.henyi.mqtt.core.domain.PublishRes;
import com.henyi.mqtt.core.properties.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 订阅消息初始化
 *
 * @author henyi
 */
@Slf4j
@Component
public class MqttClient {

    @Resource
    private MqttProperties mqttProperties;
    @Resource
    private MyMqttConnectOptions mqttConnectOptions;

    private org.eclipse.paho.client.mqttv3.MqttClient client;

    @PostConstruct
    public void init() throws MqttException {
        log.info("-----------连接订阅初始化----------");
        // host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientId的保存形式，默认为以内存保存
        client = new org.eclipse.paho.client.mqttv3.MqttClient(mqttProperties.getUrl(), mqttProperties.getClientId(), new MemoryPersistence());
        // 设置回调
        client.setCallback(new MqttCallBack(client));
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


