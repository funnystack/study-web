package com.funny.study.ss.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funny.study.ss.entity.User;
import com.funny.study.ss.mapper.UserMapper;
import com.funny.study.ss.service.IUserService;
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

