package com.casa.brunabaudel.testenodejs.model;

import java.io.Serializable;

/**
 * Created by brunabaudel on 12/04/16.
 */
public class AuthorParams implements Serializable {

    public String author;
    public static final long  serialVersionUID = 100L;

    public AuthorParams(String author ) {
        this.author = author;
    }
}
