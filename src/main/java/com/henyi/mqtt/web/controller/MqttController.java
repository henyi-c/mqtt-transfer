package com.henyi.mqtt.web.controller;

import com.henyi.mqtt.core.domain.R;
import com.henyi.mqtt.web.dto.PublishDto;
import com.henyi.mqtt.web.service.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * mqtt业务
 *
 * @author henyi
 */
@Slf4j
@RestController
@RequestMapping("/mqtt")
public class MqttController {

    @Resource
    MqttService mqttService;

    @PostMapping("/publish")
    public R<?> publish(@Validated @RequestBody PublishDto publishDto) throws MqttException {
        return R.ok(mqttService.publish(publishDto));
    }

}
