package com.funny.study.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Slf4j
public class LoadLuaScriptService implements ApplicationContextAware {
    /**
     * 减库存脚本
     */
    public static String DECRBY_STOCK_SCRIPT = "";

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        InputStream certStream = null;
        BufferedReader br = null;
        try {
            certStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("lua/decrByStock.lua");
            br = new BufferedReader(new InputStreamReader(certStream, "UTF-8"));
            StringBuilder luaStr = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                luaStr.append(line).append(" ");
            }
            DECRBY_STOCK_SCRIPT = luaStr.toString();
            log.info("减库存脚本初始化加载完毕，内容为：" + DECRBY_STOCK_SCRIPT);

        } catch (Exception e) {
            log.error("初始化库存管理bean，加载操作库存脚本失败！" + e);
        } finally {
            if (certStream != null) {
                try {
                    certStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
