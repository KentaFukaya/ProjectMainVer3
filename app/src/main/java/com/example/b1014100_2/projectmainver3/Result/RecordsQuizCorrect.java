package com.example.b1014100_2.projectmainver3.Result;

/**
 * Created by  on 2016/12/01.
 */

public class RecordsQuizCorrect {

    private int id;
    private int correct;
    private int flag;

    public RecordsQuizCorrect(int id, int correct, int flag) {
        this.id = id;
        this.correct = correct;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public int getCorrect() {
        return correct;
    }

    public int getFlag() {
        return flag;
    }
}
