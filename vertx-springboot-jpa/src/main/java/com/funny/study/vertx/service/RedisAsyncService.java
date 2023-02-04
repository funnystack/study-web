package com.funny.study.vertx.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

/**
 * Created by XHD on 2018/3/15.
 */
@ProxyGen
public interface RedisAsyncService {

    String ADDRESS = RedisAsyncService.class.getName();

    void get(String key, Handler<AsyncResult<String>> resultHandler);

}
