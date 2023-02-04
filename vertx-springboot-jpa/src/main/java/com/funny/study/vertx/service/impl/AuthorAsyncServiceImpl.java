package com.funny.study.vertx.service.impl;

import com.funny.study.vertx.entity.Author;
import com.funny.study.vertx.service.AuthorAsyncService;
import com.funny.study.vertx.service.AuthorService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by XHD on 2018/3/15.
 */
@Component
public class AuthorAsyncServiceImpl implements AuthorAsyncService {

    @Autowired
    private AuthorService authorService;

    @Override
    public void add(Author author, Handler<AsyncResult<Author>> resultHandler) {
        Author save = authorService.save(author);
        Future.succeededFuture(save).setHandler(resultHandler);
    }

    @Override
    public void getAll(Handler<AsyncResult<List<Author>>> resultHandler) {
        Future.succeededFuture(authorService.getAll()).setHandler(resultHandler);
    }
}
