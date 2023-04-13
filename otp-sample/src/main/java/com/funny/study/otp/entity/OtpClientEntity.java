package com.funny.study.otp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 动态口令表
 * </p>
 *
 * @author fangli
 * @since 2023-04-13 09:48:25
 */
@Getter
@Setter
@TableName("otp_client")
public class OtpClientEntity {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField("created_stime")
    private Date createdStime;

    /**
     * 更新时间
     */
    @TableField("modified_stime")
    private Date modifiedStime;

    /**
     * 是否删除
     */
    @TableField("is_del")
    @TableLogic
    private Integer isDel;

    /**
     * 1实体卡 2动态令牌 3 手机app
     */
    @TableField("client_type")
    private Integer clientType;

    /**
     * 客户端唯一标识
     */
    @TableField("client_sn")
    private String clientSn;

    /**
     * 加密secret，用来生成6位动态口令，需要加密传输
     */
    @TableField("secret")
    private String secret;

    /**
     * 绑定的用户id
     */
    @TableField("bind_user_id")
    private String bindUserId;

    /**
     * 绑定的用户id的时间
     */
    @TableField("bind_time")
    private Date bindTime;

    /**
     * 绑定的用户id
     */
    @TableField("ext_value")
    private String extValue;


}
