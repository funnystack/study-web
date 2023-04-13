CREATE TABLE `otp_client` (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
`created_stime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`modified_stime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
`is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
`client_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1实体卡 2动态令牌 3 手机app',
`client_sn` varchar(32) DEFAULT NULL COMMENT '客户端唯一标识',
`secret` varchar(32) DEFAULT NULL COMMENT '加密secret，用来生成6位动态口令，需要加密传输',
`bind_user_id` varchar(32) DEFAULT NULL COMMENT '绑定的用户id',
`bind_time` datetime DEFAULT NULL COMMENT '绑定的用户id的时间',
`ext_value` varchar(32) DEFAULT NULL COMMENT '绑定的用户id',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动态口令表';