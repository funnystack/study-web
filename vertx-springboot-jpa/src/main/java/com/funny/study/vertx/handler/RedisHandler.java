package com.funny.study.vertx.handler;

import com.funny.study.vertx.service.RedisAsyncService;
import io.vertx.ext.web.RoutingContext;

import static java.net.HttpURLConnection.HTTP_CREATED;

/**
 * Created by XHD on 2018/3/15.
 */
public class RedisHandler {

    public static void getValue(RoutingContext rc, RedisAsyncService redisAsyncService) {
       String key = "aa";

        redisAsyncService.get(key, ar -> {
            if (ar.succeeded()) {
                rc.response().setStatusCode(HTTP_CREATED).end();
            } else {
                rc.fail(ar.cause());
            }
        });
    }


}
