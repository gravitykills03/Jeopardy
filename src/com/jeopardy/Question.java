package com.jeopardy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Question {

    private String question;
    private String answer;
    private String category;
    private int value;
    private boolean isAnswered;
    private boolean isDailyDouble;
    private boolean validateAnswer;

    public Question() {
    }

    public Question(String question) {
        this.question = question;
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer, String category) {
        this.question = question;
        this.answer = answer;
        this.category = category;
    }

    public Question(String question, String answer, String category, int value) {
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.value = value;
    }

    public boolean isValidateAnswer() {
        return validateAnswer;
    }

    public void setValidateAnswer(boolean validateAnswer) {
        this.validateAnswer = validateAnswer;
    }

    public int getValue() {
        return value;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public boolean isDailyDouble() {
        return isDailyDouble;
    }

    public void setDailyDouble(boolean dailyDouble) {
        isDailyDouble = dailyDouble;
    }
}
