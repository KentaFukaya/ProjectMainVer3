package com.example.b1014100_2.projectmainver3.map;


import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

/**
 * Created by b1014100_2 on 2016/09/30.
 */

public class MapAreasIterator implements Iterator {
    private AggregateMapArea aggregateMapArea;
    private int index;

    public MapAreasIterator(AggregateMapArea aggregateMapArea) {
        this.aggregateMapArea = aggregateMapArea;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < aggregateMapArea.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public Object next() {
        MapArea mapArea = aggregateMapArea.getMapAreaAt(index);
        index++;
        return mapArea;
    }
}
