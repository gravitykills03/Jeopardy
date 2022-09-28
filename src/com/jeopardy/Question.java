package com.jeopardy;

class Question {

    private String question;
    private String answer;
    private int value;
    private boolean isAnswered;
    private boolean isDailyDouble;

    /**
     * Constructor for Question
     */
    public Question() {
    }

    /**
     * Accessor method: retrieves the value of the question
     *
     * @return - int value of question
     */
    public int getValue() {
        return value;
    }

    /**
     * Accessor method: retrieves the question string
     *
     * @return - String question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Accessor method: sets the question string
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Accessor method: retrieves the answer string
     *
     * @return - String answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Accessor method: sets the answer string
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Accessor method: sets the value of the question
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Accessor method: retrieves whether the question has been answered previously
     *
     * @return - boolean - has the question been answered
     */
    public boolean isAnswered() {
        return isAnswered;
    }

    /**
     * Accessor method: sets whether the question has been answered previously
     */
    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    /**
     * Accessor method: retrieves whether the question is a Daily Double
     *
     * @return - boolean - is the question a Daily Double
     */
    public boolean isDailyDouble() {
        return isDailyDouble;
    }

    /**
     * Accessor method: sets whether the question is a Daily Double
     */
    public void setDailyDouble(boolean dailyDouble) {
        isDailyDouble = dailyDouble;
    }
}
