package com.example.b1014100_2.projectmainver3.zukan;

import java.util.ArrayList;

/**
 * Created by 1014159 on 2016/10/11.
 */

public class Zukan {
    private int id;
    private String name;
    private String content;
    private int length;
    private String syllabary;
    private String type;
    private int season_spring;
    private int season_summer;
    private int season_fall;
    private int season_winter;
    private String imageName;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSeason_fall() {
        return season_fall;
    }
    public void setSeason_fall(int season_fall) {
        this.season_fall = season_fall;
    }
    public int getSeason_spring() {
        return season_spring;
    }
    public void setSeason_spring(int season_spring) {
        this.season_spring = season_spring;
    }
    public int getSeason_summer() {
        return season_summer;
    }
    public void setSeason_summer(int season_summer) {
        this.season_summer = season_summer;
    }
    public int getSeason_winter() {
        return season_winter;
    }
    public void setSeason_winter(int season_winter) {
        this.season_winter = season_winter;
    }
    public String getSyllabary() {
        return syllabary;
    }
    public void setSyllabary(String syllabary) {
        this.syllabary = syllabary;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Zukan() {
    }

    public Zukan(String content, int id, String imageName, int length, String name, int season_fall, int season_spring, int season_summer, int season_winter, String syllabary, String type) {
        this.content = content;
        this.id = id;
        this.imageName = imageName;
        this.length = length;
        this.name = name;
        this.season_fall = season_fall;
        this.season_spring = season_spring;
        this.season_summer = season_summer;
        this.season_winter = season_winter;
        this.syllabary = syllabary;
        this.type = type;
    }

    /* no use
    public static ArrayList<Zukan> zukanCrate(){

        ArrayList<Zukan> zukans = new ArrayList<Zukan>();

        zukans.add(new Zukan(1, "マアジ", "海水魚", "アジ科", 100, "春", "zukan1"));
        zukans.add(new Zukan(2, "ヤリイカ", "海水魚", "ヤリイカ科", 100, "夏", "zukan2"));
        zukans.add(new Zukan(3, "クロマグロ", "海水魚", "サバ科", 100, "秋", "zukan3"));
        zukans.add(new Zukan(4, "マダイ", "海水魚", "タイ科", 100, "冬", "zukan4"));
        zukans.add(new Zukan(5, "アイナメ", "海水魚", "アイナメ科", 100, "春", "zukan5"));
        zukans.add(new Zukan(6, "アカガレイ", "海水魚", "カレイ科", 100, "夏", "zukan6"));

        return zukans;
    }
    */

    public String printall(){
        String allcontents;
        allcontents = getId() + " " + getName() + " " + getContent() + " " + getType() + " " + getLength() + " " + getImageName();
        return allcontents;
    }
}
