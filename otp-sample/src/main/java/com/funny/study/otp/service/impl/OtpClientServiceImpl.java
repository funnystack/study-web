package com.funny.study.otp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.funny.study.otp.dao.OtpClientMapper;
import com.funny.study.otp.entity.OtpClientEntity;
import com.funny.study.otp.service.IOtpClientService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 动态口令表 服务实现类
 * </p>
 *
 * @author fangli
 * @since 2023-04-13 09:48:25
 */
@Service
public class OtpClientServiceImpl extends ServiceImpl<OtpClientMapper, OtpClientEntity> implements IOtpClientService {

}
