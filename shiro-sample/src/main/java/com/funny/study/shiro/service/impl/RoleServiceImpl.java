package com.funny.study.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funny.study.shiro.mapper.RoleMapper;
import com.funny.study.shiro.entity.Role;
import com.funny.study.shiro.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
