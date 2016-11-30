package com.example.b1014100_2.projectmainver3.Result;

/**
 * Created by b1014100_2 on 2016/11/29.
 */

public class ResultData {

    int id;
    int state;
    int mode;
    int level;
    String name;
    String desc;


    public ResultData( int id , int state, int mode, int level , String name, String desc){
        this.id = id;//通しバング
        this.state = state;//称号を取得しているか
        this.mode = mode;//0 動画,1 図鑑,2 kuizu
        this.level = level;//称号のレベル　１～５
        this.name = name;//称号の名前
        this.desc = desc;//未取得時の表示
    }

    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public int getMode() {
        return mode;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
