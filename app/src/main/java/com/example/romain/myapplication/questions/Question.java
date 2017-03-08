package com.example.romain.myapplication.questions;

/**
 * Created by romaindemongivert on 07/03/2017.
 */

public class Question {
    public String text;
    public String answer;
    public String difficulty;
    public String category;

    public Question (String text, String difficulty, String answer, String category){
        this.text = text;
        this.difficulty = difficulty;
        this.answer = answer;
        this.category = category;
    }

}
