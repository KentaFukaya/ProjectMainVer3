package com.example.b1014100_2.projectmainver3.movie;


import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;

/**
 * Created by b1014100_2 on 2016/10/02.
 */

public class MovieDataIterator implements Iterator {
    private AggregateMovieData aggregateMovieData;
    private int index;

    public MovieDataIterator(AggregateMovieData aggregateMovieData) {
        this.aggregateMovieData = aggregateMovieData;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < aggregateMovieData.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public Object next() {
        MovieData movieData = aggregateMovieData.getMovieDataAt(index);
        index++;
        return movieData;
    }
}
