package com.funny.study.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funny.study.shiro.entity.UserRole;
import com.funny.study.shiro.mapper.UserRoleMapper;
import com.funny.study.shiro.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
