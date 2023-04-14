package com.henyi.mqtt.core.clients;

import com.henyi.mqtt.core.properties.MqttProperties;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * mqtt连接配置
 *
 * @author henyi
 */
@Component
public class MyMqttConnectOptions {

    @Resource
    private MqttProperties mqttProperties;

    public MqttConnectOptions getOptions() {
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);
        // 设置最长重连的时间间隔，单位为秒
        opts.setMaxReconnectDelay(50);
        // 设置超时时间，单位为秒
        opts.setConnectionTimeout(20);
        opts.setHttpsHostnameVerificationEnabled(false);
        // 是否清空session，设置为false表示服务器会保留客户端的连接记录，客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
        // 设置为true表示每次连接到服务端都是以新的身份
        opts.setCleanSession(false);
        // 设置心跳时间 单位为秒，表示服务器每隔20秒的时间向客户端发送心跳判断客户端是否在线
        opts.setKeepAliveInterval(20);
        // 断开自动重连
        opts.setAutomaticReconnect(true);
        opts.setUserName(mqttProperties.getUsername());
        opts.setPassword(mqttProperties.getPassword().toCharArray());
        // 设置ssl
        // opts.setSocketFactory(SslUtil.getSocketFactory("/UserProfile/ca/ca.crt", "/UserProfile/ca/client.crt", "/UserProfile/ca/client.key", "123456"));
        return opts;
    }
}
