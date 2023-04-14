package com.henyi.mqtt.core.domain;

import lombok.Data;

/**
 * 返回发布消息状态
 *
 * @author henyi
 */
@Data
public class PublishRes {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * topic
     */
    private String topic;

    /**
     * qos
     */
    private Integer qos;

    /**
     * 消息内容
     */
    private String content;

}
