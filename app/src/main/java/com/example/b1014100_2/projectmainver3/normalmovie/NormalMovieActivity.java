package com.example.b1014100_2.projectmainver3.normalmovie;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.movie.AggregateMovieData;
import com.example.b1014100_2.projectmainver3.movie.MovieData;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;

public class NormalMovieActivity extends AppCompatActivity {
    AggregateMovieData movieDatas = new AggregateMovieData();
    int id;
    String moviename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//INVISIVLE titilebar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//full screan
        setContentView(R.layout.activity_normal_movie);

        //get id from intent
        Intent intent = getIntent();
        intent.getIntExtra("id", 0);
        id = intent.getIntExtra("id", 0);

        ReadMovieCsv();
        moviename = movieDatas.getMovieDataAt(id).getMovieName();
        Log.d("TEST", "onCreate: id = "+id+", MovieName is  "+ movieDatas.getMovieDataAt(id).getMovieName()+",random = "+Random(id));
        SaveMovieCsv();
    }

    public void ReadMovieCsv() {
        // AssetManagerの呼び出し
        AssetManager assetManager = getResources().getAssets();
        try {
            // movie.CSVファイルの読み込み
            InputStream is = assetManager.open("Movie.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            // watch.CSVファイルの読み込み
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                // movie.csv
                //id,max,動画名
                StringTokenizer st = new StringTokenizer(line, ",");
                int id = Integer.parseInt(st.nextToken());
                int max = Integer.parseInt(st.nextToken());
                String name = st.nextToken();
                name = name.substring(0,name.length()-4);//deleate .mp4
                movieDatas.appendMovieData(new MovieData(id,max,name));
                //Log.d("ReadMovieCsv", "id = "+id +", max = "+max+", name ="+name);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            String openFileName = "watch.csv";
            FileInputStream input = this.openFileInput(openFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";
            int i = 0;
            while ((line = bufferReader.readLine()) != null) {
                // 各行が","で区切られていて4つの項目があるとす
                StringTokenizer st = new StringTokenizer(line, ",");
                int  s = 0;
                i = Integer.parseInt(st.nextToken());
                while(st.hasMoreTokens() && s < movieDatas.getMovieDataAt(i).getMax()){
                    if(Integer.parseInt(st.nextToken()) != 0)
                        movieDatas.getMovieDataAt(i).setWatchbynumber(s);
                    // Log.d("TEST", "ReadMovieCsv: id = "+i+",s ="+s+",watch = "+movieDatas.getMovieDataAt(i).getWatch(s));
                    s++;
                }
                // Log.d("TEST", "ReadMovieCsv: id = "+i+",watch = "+movieDatas.getMovieDataAt(i).getWatchtoString());
            }
            bufferReader.close();
            // ストリームを閉じる
            input.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void SaveMovieCsv(){
        String FileName = "watch.csv";
        try {
            // 書き込み先のストリームを開く
            FileOutputStream output = this.openFileOutput(FileName, MODE_PRIVATE);

            Iterator it = movieDatas.Iterator();
            while(it.hasNext()){
                MovieData md = (MovieData) it.next();
                Log.d("test", "SaveMovieCsv: id = "+md.getId()+", watch ="+md.getWatchtoString() );
                output.write(md.getWatchtoString().getBytes());
                output.write("\n".getBytes());
            }
            // ストリームを閉じる
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int Random(int id){
        //現在の秒数を取得
        Date rowdate = new Date();
        String date[] = rowdate.toString().split(" ");
        int min = Integer.parseInt(date[3].substring(6, 8));
        //watchフラグが立っていないものを選ぶ
        MovieData md = movieDatas.getMovieDataAt(id);
        while(md.checkWatch(min % md.getMax()))
            min++;
        md.setWatchbynumber(min % md.getMax());
        //Log.d("TEST", "Random: id="+id+",return ="+min%md.getMax());
        return min%md.getMax();
    }
}
