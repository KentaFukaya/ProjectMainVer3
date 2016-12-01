package com.example.b1014100_2.projectmainver3.Result;

/**
 * Created by on 2016/10/11.
 */

public class Result {
    private int id;
    private String name;
    private String title;
    private int star_level;
    private int flag;
    private int flag_of_new;
    private int type;
    private String elements_result;
    private String elements_movie;
    private String elements_zukan;
    private String	elements_quiz;
    private String elements_quiz_correct;
    private String elements_new;

    public Result(int id, String name, String title, int star_level, int flag, int flag_of_new, int type, String elements_result, String elements_movie, String elements_zukan, String elements_quiz, String elements_quiz_correct, String elements_new) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.star_level = star_level;
        this.flag = flag;
        this.flag_of_new = flag_of_new;
        this.type = type;
        this.elements_result = elements_result;
        this.elements_movie = elements_movie;
        this.elements_zukan = elements_zukan;
        this.elements_quiz = elements_quiz;
        this.elements_quiz_correct = elements_quiz_correct;
        this.elements_new = elements_new;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getStar_level() {
        return star_level;
    }

    public int getFlag() {
        return flag;
    }

    public int getFlag_of_new() {
        return flag_of_new;
    }

    public int getType() {
        return type;
    }

    public String getElements_result() {
        return elements_result;
    }

    public String getElements_movie() {
        return elements_movie;
    }

    public String getElements_zukan() {
        return elements_zukan;
    }

    public String getElements_quiz() {
        return elements_quiz;
    }

    public String getElements_quiz_correct() {
        return elements_quiz_correct;
    }

    public String getElements_new() {
        return elements_new;
    }

    public String printAll(){
        return getId() + " " + getName() + " " + getTitle() + " " + getStar_level() + " flag:" + getFlag() + " " + getFlag_of_new() + " "
                + getType() + " " + getElements_result() + " " + getElements_movie() + " " + getElements_zukan() + " " + getElements_quiz();
    }
}
