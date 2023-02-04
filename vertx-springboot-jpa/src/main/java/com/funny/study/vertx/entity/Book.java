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

package com.funny.study.vertx.entity;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import javax.persistence.*;

/**
 * A book JPA entity.
 *
 * @author Thomas Segismont
 */
@Entity
// Book must annotated with @DataObject because it is used as a parameter type in BookAsyncService
@DataObject(generateConverter = true)
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private Author author;


    // Mandatory for JPA entities
    protected Book() {
    }

    // Mandatory for data objects
    public Book(JsonObject jsonObject) {
        BookConverter.fromJson(jsonObject, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        BookConverter.toJson(this, json);
        return json;
    }

}
