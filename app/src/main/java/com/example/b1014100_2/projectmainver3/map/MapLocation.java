package com.example.b1014100_2.projectmainver3.map;

/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class MapLocation {
    private int id;
    private String name;
    private double xcor,ycor;

    public MapLocation(int id,String name,double xcor,double ycor){
        this.id = id;
        this.name = name;
        this.xcor = xcor;
        this.ycor = ycor;
    }

    /*getter*/
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getXcor() {
        return xcor;
    }
    public double getYcor(){
        return ycor;
    }

    /*setter*/
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setXcor(double xcor) {
        this.xcor = xcor;
    }

    public void setYcor(double ycor) {
        this.ycor = ycor;
    }

}
