package com.henyi.mqtt.core.callbacks;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 发布回调
 *
 * @author henyi
 */
@Slf4j
public class PublishCallback implements MqttCallbackExtended {

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        log.info("-----------发布连接断开-----------");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("-----------发布交付完成-----------: " + token.isComplete());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
    }

    @Override
    public void connectComplete(boolean b, String s) {
        log.info("-----------初始化发布连接完成-----------");
    }


}