package com.example.b1014100_2.projectmainver3.map;

/**
 * Created by b1014100_2 on 2016/10/30.
 */

public class MapData {
    private int id;
    private int area_id,location_id;
    private String name;
    private int check360;
    private double xcor,ycor;
    private float zoom;

    public MapData(int id, int location_id,MapLocation mapLocation){
        this.id = id;
        this.area_id = mapLocation.getArea_id();
        this.location_id = location_id;
        this.name = mapLocation.getName();
        this.area_id =mapLocation.getArea_id();
        this.check360 = mapLocation.getCheck360();
        this.xcor = mapLocation.getXcor();
        this.ycor = mapLocation.getYcor();
    }

    public MapData(int id, MapArea mapArea){
        this.id = id;
        this.area_id = id;
        this.location_id = -1;
        this.name = mapArea.getName();
        this.check360 = -1;
        this.xcor = mapArea.getXcor();
        this.ycor = mapArea.getYcor();
        this.zoom = mapArea.getZoom();
    }

    /*getter*/
    public int getId() {
        return id;
    }
    public int getArea_id(){return area_id;}
    public int getLocation_id() {return location_id;}
    public String getName() {
        return name;
    }
    public int getCheck360() {return check360;}
    public double getXcor() {
        return xcor;
    }
    public double getYcor(){
        return ycor;
    }
    public float getZoom() {return zoom;}

}
