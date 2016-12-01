package com.example.b1014100_2.projectmainver3.Result;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.map.AggregateMapArea;
import com.example.b1014100_2.projectmainver3.map.MapArea;

/**
 * Created by b1014100_2 on 2016/11/29.
 */

public class ResultDataIterator implements Iterator {
    private AggregateResultData aggregateResultData;
    private int index;

    public ResultDataIterator(AggregateResultData aggregateResultData) {
        this.aggregateResultData = aggregateResultData;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < aggregateResultData.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public Object next() {
        ResultData resultData = aggregateResultData.getResultDataAt(index);
        index++;
        return resultData;
    }
}
