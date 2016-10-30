package com.example.b1014100_2.projectmainver3.map;

/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class MapArea {
    private int id;
    private String name;
    private double xcor,ycor;
    private float zoom;
    public MapArea(int id, String name, double xcor, double ycor, float zoom){
        this.id = id;
        this.name = name;
        this.xcor = xcor;
        this.ycor = ycor;
        this.zoom = zoom;
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
    public float getZoom() {return zoom;}

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
    public void setZoom(float zoom) {this.zoom = zoom;}

}
