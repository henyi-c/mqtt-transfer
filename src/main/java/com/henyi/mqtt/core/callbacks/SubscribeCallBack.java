package com.henyi.mqtt.core.callbacks;

import cn.hutool.extra.spring.SpringUtil;
import com.henyi.mqtt.core.properties.MqttProperties;
import com.henyi.mqtt.core.properties.SubscribeProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.List;

/**
 * 订阅消息回调
 *
 * @author henyi
 */
@Slf4j
public class SubscribeCallBack implements MqttCallbackExtended {


    MqttClient client;

    public SubscribeCallBack(MqttClient client) {
        this.client = client;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.warn("-----------订阅失败，进行重连-----------");
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) {

        try {
            // subscribe后得到的消息会执行到这里面
            log.info("-----------接收消息topic: " + s);
            log.info("-----------接收消息Qos: " + mqttMessage.getQos());
            log.info("-----------接收消息内容: " + new String(mqttMessage.getPayload()));
        } catch (Exception e) {
            log.error("-----------订阅消息返回报错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("-----------订阅交付完成-----------" + iMqttDeliveryToken.isComplete());
    }

    @Override
    public void connectComplete(boolean b, String s) {
        //订阅消息
        MqttProperties mqttProperties = SpringUtil.getBean(MqttProperties.class);
        List<SubscribeProperties> subscribes = mqttProperties.getSubscribes();
        String[] topics = subscribes.stream().map(SubscribeProperties::getTopic).toArray(String[]::new);
        int[] qos = subscribes.stream().map(SubscribeProperties::getQos).mapToInt(Integer::valueOf).toArray();
        try {
            if (client.isConnected()) {
                client.subscribe(topics, qos);
            }
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        log.info("-----------初始化订阅连接完成-----------");
    }

}
