package com.funny.study.springboot.controller;

import com.funny.study.springboot.event.TestEvent;
import com.funny.study.springboot.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@Slf4j
@RestController
public class SpringEventController {

    /**
     * spring的event 默认为同步执行，如果想异步执行需要在@EventListener 上增加@Async注解，
     * 如果项目上没有指定异步线程池，也会同步执行，@Order可以改变Listener的执行顺序
     * @param id
     * @return
     */
    @GetMapping("/eventTest")
    public String eventTest(Long id) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        SpringContextUtils.publishEvent(new TestEvent(id));
        stopWatch.stop();
        System.out.println(stopWatch.shortSummary());
        return stopWatch.prettyPrint();
    }
}
