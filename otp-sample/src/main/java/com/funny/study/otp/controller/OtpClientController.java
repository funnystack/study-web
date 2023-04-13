package com.funny.study.otp.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 动态口令表 前端控制器
 * </p>
 *
 * @author fangli
 * @since 2023-04-13 09:48:25
 */
@RestController
@RequestMapping("/otp/")
public class OtpClientController {


    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public String bindUser() {

        return "ok";
    }

    /**
     * 不应该有这个接口，理论上应该是客户端自己生成，此接口只是为了模拟生成的逻辑
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getNumber", method = RequestMethod.GET)
    public String getNumber(String userId) {

        return "ok";
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String getNumber(String userId,String password) {

        return "ok";
    }
}
