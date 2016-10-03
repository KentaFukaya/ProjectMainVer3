package com.example.b1014100_2.projectmainver3.map;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Aggregate;
import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

import java.util.ArrayList;


/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class AggregateMapLocation implements Aggregate {
    private ArrayList<MapLocation> MapLocations;
    private int last = 0;

    public AggregateMapLocation(){
        MapLocations = new ArrayList<MapLocation>();
    }
    public int getLength() {
        return last;
    }
    /*get maplocation by id*/
    public MapLocation getMapLocationAt(int id){
        return MapLocations.get(id);
    }
    /*add maplocation*/
    public void appendMapLocation(MapLocation mapLocation){
        this.MapLocations.add(mapLocation);
        last++;
    }

    public int getIdbyName(String name){
        Iterator it = this.Iterator();
        while (it.hasNext()){
            MapLocation mp = (MapLocation)it.next();
            if(mp.getName().equals(name)) return mp.getId();
        }
    return -1;
    }

    public Iterator Iterator(){
        return new MapLocationsIterator(this);
    }
}
