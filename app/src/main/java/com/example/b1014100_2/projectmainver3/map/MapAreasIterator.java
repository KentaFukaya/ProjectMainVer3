package com.example.b1014100_2.projectmainver3.map;


import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class MapAreasIterator implements Iterator {
    private AggregateMapLocation aggregateMapLocation;
    private int index;

    public MapAreasIterator(AggregateMapLocation aggregateMapLocation){
        this.aggregateMapLocation = aggregateMapLocation;
        this.index  = 0;
    }
    public boolean hasNext(){
     if(index < aggregateMapLocation.getLength()){
         return true;
     }else{
         return false;
     }
    }
    public Object next(){
        MapLocation mapLocation = aggregateMapLocation.getMapLocationAt(index);
        index++;
        return mapLocation;
    }
}
