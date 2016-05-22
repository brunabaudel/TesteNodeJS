package com.casa.brunabaudel.testenodejs.model;

/**
 * Created by brunabaudel on 09/05/16.
 */
public class LetterHeader {

    private String letter;
    private int position;

    public LetterHeader(String letter, int position) {
        this.letter = letter;
        this.position = position;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
