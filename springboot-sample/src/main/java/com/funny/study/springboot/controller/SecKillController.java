package com.funny.study.springboot.controller;

import com.funny.study.springboot.dao.BookMapper;
import com.funny.study.springboot.entity.BookEntity;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.funny.study.springboot.service.LoadLuaScriptService.DECRBY_STOCK_SCRIPT;

/**
 * @author
 */
@RestController
@RequestMapping({"/api"})
@Slf4j
public class SecKillController {


    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @ResponseBody
    @RequestMapping(value = "/seckill")
    public List<BookEntity> books() {
        return bookMapper.queryStaffExpressCompany();
    }


    @RequestMapping(value = "/decrByStock")
    public int decrByStock() {
        int res = 0;
        String stockId = "1001";
        Integer decrNum = 1;
        Integer userId = 8001;

        if (StringUtils.isBlank(DECRBY_STOCK_SCRIPT)) {
            log.error("减库存脚本为空！操作终止");
            return -1;
        }
        String stockCount = (String) redisTemplate.opsForValue().get(stockId);
        System.out.println("stockCount="+stockCount);
        try {
            if (null != stockId && null != decrNum) {
                // 加减库存lua脚本执行
                Long result = (Long) evalshaScript(stockId, decrNum, userId);

                log.info("脚本执行结果，result=" + result);

                res = result.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("decr sku stock failure.", e);
            res = -1;
        } finally {

        }
        return res;
    }

    /**
     * 加减库存lua脚本执行
     *
     * @param stockId 库存id
     * @param changeNum 加减库存的量
     * @param userId
     * @return 执行结果
     */
    private Object evalshaScript(String stockId, Integer changeNum, Integer userId) {

        Long aLong = (Long) redisTemplate.execute(RedisScript.of(DECRBY_STOCK_SCRIPT),
                Lists.newArrayList(stockId),changeNum+"",userId+"");

       return aLong;


    }
}
