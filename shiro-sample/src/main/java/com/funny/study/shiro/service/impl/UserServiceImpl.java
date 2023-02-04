package com.funny.study.shiro.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funny.study.shiro.entity.User;
import com.funny.study.shiro.mapper.UserMapper;
import com.funny.study.shiro.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findByName(String name) {
        return baseMapper.findByName(name);
    }
}

