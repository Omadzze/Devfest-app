package com.example.devfest.Model;

public class FaqItem {

    private int question, answer;
    private boolean expanded;

    public FaqItem(int question, int answer) {
        this.question = question;
        this.answer = answer;
        this.expanded = false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
