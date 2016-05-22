package com.casa.brunabaudel.testenodejs.rest;

import com.casa.brunabaudel.testenodejs.model.Author;
import com.casa.brunabaudel.testenodejs.model.AuthorParams;
import com.casa.brunabaudel.testenodejs.model.Phrase;
import com.casa.brunabaudel.testenodejs.rest.converter.MessageConverter;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.FormHttpMessageConverter;

import java.util.List;

@Rest(rootUrl = "http://192.168.0.14:8080", converters = {FormHttpMessageConverter.class, MessageConverter.class})

public interface PhraseRest extends RestClientErrorHandling {
    @Post("/getphrases")
    List<Phrase> getPhrases(AuthorParams author);

    @Post("/getauthors")
    List<Author> getAuthors();
}