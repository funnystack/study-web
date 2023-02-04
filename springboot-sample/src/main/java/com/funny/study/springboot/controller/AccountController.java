package com.funny.study.springboot.controller;

import com.funny.study.springboot.dao.BookMapper;
import com.funny.study.springboot.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 */
@RestController
@RequestMapping({"/api"})
public class AccountController{


    @Autowired
    private BookMapper bookMapper;


    @ResponseBody
    @RequestMapping(value = "/books")
    public List<BookEntity> books() {
        return bookMapper.queryStaffExpressCompany();
    }

}
