package com.narc.alibaba.service.alimama.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * ALIT_MESSAGE_LOG
 * 消息表
 * 
 * @author Narc
 * @date 2021-01-27
 */
@Data
public class AlitMessageLog {
    /**
     * ID
     * 自增主键
     */
    @NotNull
    private Integer id;

    /**
     * SENDER_ID
     * 消息发送人ID
     */
    @Size(max=64)
    private String senderId;

    /**
     * SENDER_NAME
     * 消息发送人姓名
     */
    @Size(max=255)
    private String senderName;

    /**
     * MSG_CONTENT
     * 收到消息内容
     */
    @Size(max=1024)
    private String msgContent;

    /**
     * RET_MSG
     * 返回消息内容
     */
    @Size(max=1024)
    private String retMsg;

    /**
     * ITEM_ID
     * 商品ID
     */
    @Size(max=64)
    private String itemId;

    /**
     * DISCOUNT_RATE
     * 返现比例
     */
    private BigDecimal discountRate;

    /**
     * CREATED_DATETIME
     * 创建时间
     */
    private Date createdDatetime;

    /**
     * CREATED_ID
     * 创建ID
     */
    @Size(max=64)
    private String createdId;

    /**
     * MODIFIED_DATETIME
     * 修改时间
     */
    private Date modifiedDatetime;

    /**
     * MODIFIED_ID
     * 修改ID
     */
    @Size(max=64)
    private String modifiedId;
}