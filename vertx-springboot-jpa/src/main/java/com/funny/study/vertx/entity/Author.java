package com.funny.study.vertx.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import javax.persistence.*;

/**
 * Created by XHD on 2018/3/15.
 */
@Entity
// Book must annotated with @DataObject because it is used as a parameter type in BookAsyncService
@DataObject(generateConverter = true)
public class Author {
    @Id
    @GeneratedValue
    private long id;

    private String name;

//    @OneToOne(mappedBy = "author", cascade = {CascadeType.REFRESH, CascadeType.MERGE})
//    private Book book;

    protected Author() {
    }

    public Author(JsonObject jsonObject) {
        AuthorConverter.fromJson(jsonObject, this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        AuthorConverter.toJson(this, json);
        return json;
    }
}
