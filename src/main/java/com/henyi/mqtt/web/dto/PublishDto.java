package com.henyi.mqtt.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发布消息对象
 *
 * @author henyi
 */
@Data
public class PublishDto {
    /**
     * topic
     */
    @NotBlank(message = "${topic}")
    private String topic;

    /**
     * qos
     * QoS 0（最多一次）：消息发布完全依赖底层 TCP/IP 网络。会发生消息丢失或重复。这个级别可用于如下情况，环境传感器数据，丢失一次数据无所谓，因为不久后还会有第二次发送
     * QoS 1（至少一次）：确保消息到达，但消息重复可能会发生
     * QoS 2（只有一次）：确保消息到达一次。这个级别可用于如下情况，在计费系统中，消息重复或丢失会导致不正确的结果
     */
    @NotNull(message = "${qos}")
    private Integer qos;

    /**
     * 消息内容
     */
    @NotBlank(message = "${消息内容}")
    private String content;
}
