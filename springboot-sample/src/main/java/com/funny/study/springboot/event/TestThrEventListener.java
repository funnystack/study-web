package com.funny.study.springboot.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @date 2022/9/7 15:34
 */
@Component
@Slf4j

public class TestThrEventListener {

    @EventListener
    @Order(3)
    public void callBackListener(TestEvent event) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("TestThrEventListener event:{}", JSON.toJSONString(event));
    }
}
