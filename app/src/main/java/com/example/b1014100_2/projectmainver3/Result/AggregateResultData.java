package com.example.b1014100_2.projectmainver3.Result;

import android.util.Log;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Aggregate;
import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

import java.util.ArrayList;

/**
 * Created by b1014100_2 on 2016/11/29.
 */

public class AggregateResultData implements Aggregate {
    private ArrayList<ResultData> resultDatas;
    private int last = 0;

    public AggregateResultData() {
        resultDatas = new ArrayList<ResultData>();
    }

    public AggregateResultData(ArrayList<ResultData> list) {
        resultDatas = list;
        last = list.size();
    }


    public int getLength() {
        return last;
    }

    /*get maplocation by id*/
    public ResultData getResultDataAt(int id) {
        return resultDatas.get(id);
    }

    /*add maplocation*/
    public void appendResultData(ResultData resultData) {
        this.resultDatas.add(resultData);
        Log.d("TEST", "append: name = "+resultData.getName());
        last++;
    }

    public Iterator Iterator() {
        return new ResultDataIterator(this);
    }
}
