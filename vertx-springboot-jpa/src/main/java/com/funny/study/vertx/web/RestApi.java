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

package com.funny.study.vertx.web;

import com.funny.study.vertx.handler.AuthorHandler;
import com.funny.study.vertx.handler.BookHandler;
import com.funny.study.vertx.handler.RedisHandler;
import com.funny.study.vertx.service.AuthorAsyncService;
import com.funny.study.vertx.service.BookAsyncService;
import com.funny.study.vertx.service.RedisAsyncService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * A standard verticle, consuming the {@link BookAsyncService} over the event bus to expose a REST API.
 *
 * @author Thomas Segismont
 */
@Component
public class RestApi extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(RestApi.class);

    private BookAsyncService bookAsyncService;

    private AuthorAsyncService authorAsyncService;

    private RedisAsyncService redisAsyncService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        bookAsyncService = new ServiceProxyBuilder(vertx).setAddress(BookAsyncService.ADDRESS).build(BookAsyncService.class);

        authorAsyncService = new ServiceProxyBuilder(vertx).setAddress(AuthorAsyncService.ADDRESS).build(AuthorAsyncService.class);

        redisAsyncService = new ServiceProxyBuilder(vertx).setAddress(RedisAsyncService.ADDRESS).build(RedisAsyncService.class);


        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.post("/book").handler(routingContext -> BookHandler.addBook(routingContext, bookAsyncService));
        router.get("/books").handler(routingContext -> BookHandler.getAllBooks(routingContext, bookAsyncService));
        router.post("/author").handler(routingContext -> AuthorHandler.addAuthor(routingContext, authorAsyncService));
        router.get("/authors").handler(routingContext -> AuthorHandler.getAllAuthors(routingContext, authorAsyncService));
        router.get("/redis").handler(routingContext -> RedisHandler.getValue(routingContext, redisAsyncService));

        vertx.createHttpServer().requestHandler(router::accept).listen(8080, listen -> {
            if (listen.succeeded()) {
                logger.info("RestApi started");
                startFuture.complete();
            } else {
                startFuture.fail(listen.cause());
            }
        });
    }

}
