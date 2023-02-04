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

package com.funny.study.vertx.service.impl;

import com.funny.study.vertx.service.BookAsyncService;
import com.funny.study.vertx.service.BookService;
import com.funny.study.vertx.service.RedisAsyncService;
import com.funny.study.vertx.service.RedisService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implements the {@link BookAsyncService}, delegating calls to the transactional {@link BookService}.
 *
 * @author Thomas Segismont
 */
@Component
public class RedisAsyncServiceImpl implements RedisAsyncService {

  @Autowired
  RedisService redisService;


  @Override
  public void get(String key, Handler<AsyncResult<String>> resultHandler) {
      String value = redisService.getValue(key);
      Future.succeededFuture(value).setHandler(resultHandler);
  }
}
