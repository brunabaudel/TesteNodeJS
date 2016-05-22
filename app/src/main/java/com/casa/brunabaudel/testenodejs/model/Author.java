package com.casa.brunabaudel.testenodejs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by brunabaudel on 12/04/16.
 */
public class Author {

    @JsonProperty("author")
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return getAuthor();
    }
}
