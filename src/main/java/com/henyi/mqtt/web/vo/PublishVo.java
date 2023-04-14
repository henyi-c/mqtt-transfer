package com.henyi.mqtt.web.vo;

import lombok.Data;

/**
 * 返回发布消息状态对象
 *
 * @author henyi
 */
@Data
public class PublishVo {

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
