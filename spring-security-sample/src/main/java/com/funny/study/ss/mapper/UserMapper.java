package com.funny.study.ss.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.funny.study.ss.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author funnystack
 * @since 2018-03-20
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE name = #{name}")
    User findByName(@Param("name") String name);
}
