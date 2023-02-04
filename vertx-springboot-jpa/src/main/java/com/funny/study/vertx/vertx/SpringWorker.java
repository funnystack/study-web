/*
 * Copyright 2017 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.funny.study.vertx.vertx;

import com.funny.study.vertx.service.AuthorAsyncService;
import com.funny.study.vertx.service.BookAsyncService;
import com.funny.study.vertx.service.RedisAsyncService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * A worker verticle, exposing the {@link BookAsyncService} over the event bus.
 * <p>
 * Since it is a worker verticle, it is perfectly fine for the registered service to delegate calls to backend Spring beans.
 *
 * @author Thomas Segismont
 */
@Component
// Prototype scope is needed as multiple instances of this verticle will be deployed
@Scope(SCOPE_PROTOTYPE)
public class SpringWorker extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(SpringWorker.class);

    @Autowired
    private BookAsyncService bookAsyncService;

    @Autowired
    private AuthorAsyncService authorAsyncService;


    @Autowired
    private RedisAsyncService redisAsyncService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        ServiceBinder binder = new ServiceBinder(vertx);
        Future<Void> bookFuture = Future.future();



        Future<Void> redisFuture = Future.future();
        binder.setAddress(RedisAsyncService.ADDRESS).register(RedisAsyncService.class, redisAsyncService).completionHandler(redisFuture);


        //注册服务代理
        binder.setAddress(BookAsyncService.ADDRESS).register(BookAsyncService.class, bookAsyncService)
                .completionHandler(bookFuture);
        bookFuture.compose(x -> {
            Future<Void> authorFuture = Future.future();
            binder.setAddress(AuthorAsyncService.ADDRESS).register(AuthorAsyncService.class, authorAsyncService).completionHandler(authorFuture);
            return authorFuture;
        }).setHandler(ar -> {
            if (ar.succeeded()) {
                logger.info("Async services registered");
                startFuture.complete();
            } else {
                logger.error(ar.cause().getMessage());
                startFuture.fail(ar.cause());
            }
        });
    }

}
