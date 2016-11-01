package com.example.b1014100_2.projectmainver3.map;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Aggregate;
import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class AggregateMapArea implements Aggregate {
    private ArrayList<MapArea> MapAreas;
    private int last = 0;

    public AggregateMapArea() {
        MapAreas = new ArrayList<MapArea>();
    }

    public int getLength() {
        return last;
    }

    /*get maplocation by id*/
    public MapArea getMapAreaAt(int id) {
        return MapAreas.get(id);
    }

    /*add maplocation*/
    public void appendMapArea(MapArea mapArea) {
        this.MapAreas.add(mapArea);
        last++;
    }

    public int getIdbyName(String name) {
        Iterator it = this.Iterator();
        while (it.hasNext()) {
            MapArea mapArea = (MapArea) it.next();
            if (mapArea.getName().equals(name)) return mapArea.getId();
        }
        return -1;
    }

    public Iterator Iterator() {
        return new MapAreasIterator(this);
    }
}
