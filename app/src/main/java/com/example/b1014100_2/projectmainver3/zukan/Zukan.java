package com.example.b1014100_2.projectmainver3.zukan;

import java.util.ArrayList;

/**
 * Created by Allen on 2016/10/11.
 */

public class Zukan {
    private int id;
    private String name;
    private String content;
    private String group;
    private int length;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Zukan(int id, String name, String content, String group, int length, String imageName) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.group = group;
        this.length = length;
        this.imageName = imageName;
    }

    public static ArrayList<Zukan> zukanCrate(){

        ArrayList<Zukan> zukans = new ArrayList<Zukan>();

        zukans.add(new Zukan(1, "マアジ", "海水魚", "アジ科", 100, "zukan1"));
        zukans.add(new Zukan(2, "ヤリイカ", "海水魚", "ヤリイカ科", 100, "zukan2"));
        zukans.add(new Zukan(3, "クロマグロ", "海水魚", "サバ科", 100, "zukan3"));
        zukans.add(new Zukan(4, "マダイ", "海水魚", "タイ科", 100, "zukan4"));
        zukans.add(new Zukan(5, "アイナメ", "海水魚", "アイナメ科", 100, "zukan5"));
        zukans.add(new Zukan(6, "アカガレイ", "海水魚", "カレイ科", 100, "zukan6"));

        return zukans;
    }
}
