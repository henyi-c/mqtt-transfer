package com.henyi.mqtt.core.clients;


import com.henyi.mqtt.core.callbacks.SubscribeCallBack;
import com.henyi.mqtt.core.properties.MqttProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
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
public class MqttSubscribeClient {

    @Resource
    private MqttProperties mqttProperties;
    @Resource
    private MyMqttConnectOptions mqttConnectOptions;


    @PostConstruct
    public void init() throws MqttException {
        log.info("-----------连接订阅初始化----------");
        Integer subscribeBalanceCount = mqttProperties.getSubscribeBalanceCount();
        for (int i = 0; i < subscribeBalanceCount; i++) {
            // host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，MemoryPersistence设置clientId的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(mqttProperties.getUrl(), mqttProperties.getClientId() + "_subscribe_" + i, new MemoryPersistence());
            // 设置回调
            client.setCallback(new SubscribeCallBack(client));
            client.connect(mqttConnectOptions.getOptions());
        }
    }
}


