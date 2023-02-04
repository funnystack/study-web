package com.funny.study.springboot.config;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Date 2019/4/15
 * @auther lilun
 */

public class CustomExceptionResolver implements HandlerExceptionResolver, Ordered {

    private static Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);

    private String errorView= "/error/500";
    private String errorAttritube= "error";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception exception) {
        ModelAndView view = new ModelAndView();
        if (isReturnView(handler)) {
            view.setViewName(errorView);
            request.setAttribute(errorAttritube, exception);
        } else {
            writeJsonResponse(response, exception);
        }
        return view;
    }

    /**
     *
     * @param response
     * @param exception
     * @author lilun 2019年4月15日 16:32:58
     */
    private void writeJsonResponse(HttpServletResponse response, Exception exception) {
        Object retObj = dealExceptionToJson(exception);
        logger.error(exception.getClass().getName(),exception);
        String json = JSON.toJSONString(retObj);
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().print(json);
        } catch (IOException | IllegalStateException e) {
            logger.error("err while output json to client:" + json, e);
        }
    }
    /**
     * 处理异常成所需要的格式
     * @param exception 异常
     * @return 需要输出到客户端的数据，返回的数据会被序列化成json
     * @author lilun 2019年4月15日 下午8:37:44
     */
    protected Object dealExceptionToJson(Exception exception){
        logger.error("未catch的异常", exception);
        return "请求失败,请稍后再试";
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected boolean isReturnView(Object handler){
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class returnType = handlerMethod.getMethod().getReturnType();
            if (ModelAndView.class.isAssignableFrom(returnType) || View.class.isAssignableFrom(returnType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
