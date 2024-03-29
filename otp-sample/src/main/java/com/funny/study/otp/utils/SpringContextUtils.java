package com.funny.study.otp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {


    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setApplicationContextStatic(applicationContext);
    }

    //改动2、静态set方法
    public static void setApplicationContextStatic(final ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    /**
     * 通过name获取 Bean.
     *
     * @param name name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz clazz
     * @param <T>   obj
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz clazz
     * @param <T>   obj
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 发布事件
     *
     * @param event event
     * @return void
     * @author li yao
     * @date 2022/3/15 14:20
     */
    public static void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
}
