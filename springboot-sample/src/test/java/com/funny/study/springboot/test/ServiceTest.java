package com.funny.study.springboot.test;

import com.funny.study.springboot.ExpressApplication;
import com.funny.study.springboot.dao.BookMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: zhangzhongdi
 * @date: 2019-12-18 14:11
 * @version: V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExpressApplication.class)
public class ServiceTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void getById() throws Exception {
//        BookEntity staffExpressCompany = bookMapper.queryStaffExpressCompany(1L);
//        System.out.println(staffExpressCompany);
        System.out.println(123);
    }
}
