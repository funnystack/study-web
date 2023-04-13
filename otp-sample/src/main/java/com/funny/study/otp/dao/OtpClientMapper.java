package com.funny.study.otp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.funny.study.otp.entity.OtpClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 动态口令表 Mapper 接口
 * </p>
 *
 * @author fangli
 * @since 2023-04-13 09:48:25
 */
@Mapper
public interface OtpClientMapper extends BaseMapper<OtpClientEntity> {

}
