package com.example.b1014100_2.projectmainver3.movie;

/**
 * Created by b1014100_2 on 2016/10/01.
 */

public class MovieData {
    int id;
    int max;
    String MovieName;
    int []watch;

    public MovieData(int id,int max,int [] watch){
        this.id = id;
        this.max = max;
        this.watch = new int [max];
        for(int i : watch)
            this.watch[i] = watch[i];
    }
    public MovieData(int id,int max,String movieName){
        this.id = id;
        this.max = max;
        this.MovieName = movieName;
        this.watch = new int [max];
     }
    public int getId() {
        return id;
    }

    public int getMax() {
        return max;
    }

    public int getWatch(int n) {
        return watch[n];
    }

    public String getMovieName() {
        return MovieName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setWatch(int[] watche) {
        int s = 0;
        for(int i : watche) {
            this.watch[s] = i; s++;
        }
    }
    public void setWatchbynumber(int num){
        this.watch[num] = 1;
        /*watchがすべて1かどうかチェック*/
        int s = 1;
        for(int i :this.watch)
            s -= 1 - i;
        //すべて1の場合配列を初期化
        if(s == 1)
            this.watch = new int[this.max];
    }

    public boolean checkWatch(int num){
        if(watch[num] != 0) return true;
        return false;
    }

    public String getWatchtoString(){
        String output = new String();
        output =this.id +",";

        for(int i : this.watch)
            output += i + ",";
        return output.substring(0,output.length()-1);
    }
}
