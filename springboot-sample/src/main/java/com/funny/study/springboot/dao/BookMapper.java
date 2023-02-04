package com.funny.study.springboot.dao;

import com.funny.study.springboot.entity.BookEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BookMapper {

    List<BookEntity> queryStaffExpressCompany();

}