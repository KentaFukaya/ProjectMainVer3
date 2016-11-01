package com.example.b1014100_2.projectmainver3.quiz;

/**
 * Created by b1014169 on 2016/10/28.
 */

public class Quiz {
    private int id;
    private String name;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String answer;
    private String comment;
    private String imageName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getChoice1() {
        return choice1;
    }
    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }
    public String getChoice2() {
        return choice2;
    }
    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }
    public String getChoice3() {
        return choice3;
    }
    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Quiz() {
    }
}
