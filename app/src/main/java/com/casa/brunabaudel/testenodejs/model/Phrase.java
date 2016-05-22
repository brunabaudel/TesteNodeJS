package com.casa.brunabaudel.testenodejs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrMatcher;

/**
 * Created by brunabaudel on 10/04/16.
 */
public class Phrase {
    @JsonProperty("p_id")
    private int id;
    @JsonProperty("phrase")
    private String phrase;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhrase() {
        this.formatPhrase();
        return "\"" + this.phrase + "\"";
    }

    private String formatPhrase() {
        this.phrase = this.phrase.replaceAll("^\"|\"$", "");
        this.phrase = this.phrase.replaceAll("^“|“$", "");
        return this.phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return getId() + " - \"" + getPhrase() + "\"";
    }
}
