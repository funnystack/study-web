package com.funny.study.oauth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author funnystack
 * @since 2018/5/21 14:28
 */
@Controller
public class LoginController {
    @Value("${custom.cas.single-logout-url:}")
    public String casSingleLogoutUrl;

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        return "服务A测试通过";
    }
    /**
     * 首页，需要登录
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        //@formatter:off
        return "<h1>登录成功</h1><br><br>"
                + "<a href=\"/logout\">退出登录</a><br><br>"
                + "<a href=\"" + casSingleLogoutUrl + "\">全局退出登录</a>";
        //@formatter:on
    }

    /**
     * 登出提示页，免登录
     */
    @GetMapping("/logoutPage")
    public String logoutPage(HttpServletResponse response) {
        //@formatter:off
        return "<h1>您已退出登录成功。</h1><br><br>"
                + "<a href=\"/index\">去登录</a><br><br>"
                + "<a href=\"" + casSingleLogoutUrl + "\">全局退出登录</a>";
        //@formatter:on
    }

    /**
     * 退出登录，跳转登出提示页
     */
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 过期会话
            session.invalidate();
        }
        // 跳转登出提示页
        response.sendRedirect("/logoutPage");
    }
}
