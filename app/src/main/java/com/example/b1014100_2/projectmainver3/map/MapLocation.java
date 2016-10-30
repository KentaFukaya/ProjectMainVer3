package com.example.b1014100_2.projectmainver3.map;

/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class MapLocation {
    private int id;
    private  int area_id;
    private String name;
    private double xcor,ycor;
    private int check360;

    public MapLocation(int id,int area_id,String name,double xcor,double ycor,int check360){
        this.id = id;
        this.area_id  =area_id;
        this.name = name;
        this.xcor = xcor;
        this.ycor = ycor;
        this.check360 = check360;
    }

    /*getter*/
    public int getId() {
        return id;
    }
    public int getArea_id(){return area_id;}
    public String getName() {
        return name;
    }
    public double getXcor() {
        return xcor;
    }
    public double getYcor(){
        return ycor;
    }
    public int getCheck360() {return check360;}

    /*setter*/
    public void setId(int id) {this.id = id;}
    public void setArea_id(int area_id){this.area_id = area_id;}
    public void setName(String name) {
        this.name = name;
    }
    public void setXcor(double xcor) {
        this.xcor = xcor;
    }
    public void setYcor(double ycor) {
        this.ycor = ycor;
    }
    public void setCheck360(int check360) {this.check360 = check360;}

}
