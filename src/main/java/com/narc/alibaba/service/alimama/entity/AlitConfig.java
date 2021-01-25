package com.narc.alibaba.service.alimama.entity;

import java.util.Date;
import javax.validation.constraints.*;
import lombok.Data;

/**
 * ALIT_CONFIG
 * 消息表
 * 
 * @author Narc
 * @date 2021-01-25
 */
@Data
public class AlitConfig {
    /**
     * ID
     * 自增主键
     */
    @NotNull
    private Integer id;

    /**
     * KEY
     * 配置项
     */
    @Size(max=255)
    private String key;

    /**
     * VALUE
     * 配置值
     */
    @Size(max=255)
    private String value;

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