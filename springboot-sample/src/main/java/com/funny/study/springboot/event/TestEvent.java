package com.funny.study.springboot.event;

import org.springframework.context.ApplicationEvent;

/**
 * @className SecondHandCallBeginEvent
 * @date 2022/9/7 15:34
 */
public class TestEvent extends ApplicationEvent {

    private Long id;


    public TestEvent( Long id) {
        super(new Object());
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
