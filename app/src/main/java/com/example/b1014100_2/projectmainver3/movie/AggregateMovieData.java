package com.example.b1014100_2.projectmainver3.movie;


import com.example.b1014100_2.projectmainver3.DesiginPattern.Aggregate;
import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

import java.util.ArrayList;

/**
 * Created by b1014100_2 on 2016/10/02.
 */

public class AggregateMovieData implements Aggregate {
    private ArrayList<MovieData> MovieDatas;
    private int last = 0;

    public AggregateMovieData(){
        MovieDatas = new ArrayList<MovieData>();
    }
    public int getLength() {
        return last;
    }
    /*get maplocation by id*/
    public MovieData getMovieDataAt(int id){
        return MovieDatas.get(id);
    }
    /*add maplocation*/
    public void appendMovieData(MovieData movieData){
        this.MovieDatas.add(movieData);
        last++;
    }

    public Iterator Iterator(){
        return new MovieDataIterator(this);
    }

}
