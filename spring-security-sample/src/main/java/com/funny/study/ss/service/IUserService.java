package com.funny.study.ss.service;

import com.baomidou.mybatisplus.service.IService;
import com.funny.study.ss.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author funnystack
 * @since 2018-03-20
 */
public interface IUserService extends IService<User> {
    User findByName(String name);
}
