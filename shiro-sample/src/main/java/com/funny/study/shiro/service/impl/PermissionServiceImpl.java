package com.funny.study.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funny.study.shiro.entity.Permission;
import com.funny.study.shiro.mapper.PermissionMapper;
import com.funny.study.shiro.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author funnystack
 * @since 2018-03-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
