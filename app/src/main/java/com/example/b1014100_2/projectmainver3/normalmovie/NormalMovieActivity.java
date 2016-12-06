package com.example.b1014100_2.projectmainver3.normalmovie;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.Result.ResultManager;
import com.example.b1014100_2.projectmainver3.map.MapsActivity;
import com.example.b1014100_2.projectmainver3.movie.AggregateMovieData;
import com.example.b1014100_2.projectmainver3.movie.MovieActivity;
import com.example.b1014100_2.projectmainver3.movie.MovieData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;

import static android.os.Environment.getExternalStorageState;

public class NormalMovieActivity extends Activity {
    AggregateMovieData movieDatas = new AggregateMovieData();
    int id;
    String moviename;
    int random;
    VideoView Vv;
    File video;
    ImageButton nMovieback, nMoviereplay;
    ImageView nMoviebg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_normal_movie);

        Vv = (VideoView) findViewById(R.id.videoView);
        nMoviebg = (ImageView) findViewById(R.id.n_movie_bg);
        nMovieback = (ImageButton) findViewById(R.id.n_movie_backbutton);
        nMoviereplay = (ImageButton) findViewById(R.id.n_movie_replaybutton);


        setReplayView(false);

        //get id from intent
        Intent intent = getIntent();
        intent.getIntExtra("id", 0);
        id = intent.getIntExtra("id", 0);

        //select movie name at random
        ReadMovieCsv();
        random = Random(id);
        moviename = movieDatas.getMovieDataAt(id).getMovieName();
        Log.d("NormalMovieActivity", "onCreate: id = " + id + ", MovieName is  " + moviename + ",random = " + random);

        SaveMovieCsv();

        //make path
        //case video in R.raw
        /*int movie_R_Id = getResources().getIdentifier(moviename, "raw", getPackageName());//get R.raw."moviename"
        path = "android.resource://" + getPackageName() + "/" + movie_R_Id;
        Vv.setVideoURI(Uri.parse(path));*/

        //case video in Video
        String path = Environment.getExternalStorageDirectory().getPath();
        File dir = new File(path+"/Movies/"+moviename);
        moviename = moviename+random+".mp4";
        video = new File(dir.getAbsolutePath()+"/"+ moviename);

        ResultManager.setRecordFlagTrue(this,ResultManager.MOVIE_ACTIVITY,moviename,ResultManager.MOVIE_NORMAL);

        Log.d("NormalMovieActivity", "onCreate: dir ="+dir.toString()+", exisits = "+dir.exists() + ", video = "+video.toString()+", exisits = "+video.exists()+", mount ="+ getExternalStorageState());

        while(!video.exists())
            random = Random(id);
        Vv.setVideoPath(video.toString());
        Vv.start();

        //movie finish listener
        Vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                Log.d("MoviePlayer:test", "moive FInfished");
                setReplayView(true);
            }
        });

        //button click listenre
        nMovieback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//backto mapActivity
            }
        });
        nMoviereplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormalMovieActivity.this, NormalMovieActivity.class); //ダイビングアクティビティに飛ぶ処理
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
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
                name = name.substring(0, name.length() - 4);//deleate .mp4
                movieDatas.appendMovieData(new MovieData(id, max, name));
                //Log.d("ReadMovieCsv", "id = "+id +", max = "+max+", name ="+name);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String openFileName = "watch.csv";
            FileInputStream input = this.openFileInput(openFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String line = "";
            int i = 0;
            while ((line = bufferReader.readLine()) != null) {
                // 各行が","で区切られていて4つの項目があるとす
                StringTokenizer st = new StringTokenizer(line, ",");
                int s = 0;
                i = Integer.parseInt(st.nextToken());
                while (st.hasMoreTokens() && s < movieDatas.getMovieDataAt(i).getMax()) {
                    if (Integer.parseInt(st.nextToken()) != 0)
                        movieDatas.getMovieDataAt(i).setWatchbynumber(s);
                    // Log.d("TEST", "ReadMovieCsv: id = "+i+",s ="+s+",watch = "+movieDatas.getMovieDataAt(i).getWatch(s));
                    s++;
                }
                 Log.d("TEST", "ReadMovieCsv: id = "+i+",watch = "+movieDatas.getMovieDataAt(i).getWatchtoString());
            }
            bufferReader.close();
            // ストリームを閉じる
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveMovieCsv() {
        String FileName = "watch.csv";
        try {
            // 書き込み先のストリームを開く
            FileOutputStream output = this.openFileOutput(FileName, MODE_PRIVATE);

            Iterator it = movieDatas.Iterator();
            while (it.hasNext()) {
                MovieData md = (MovieData) it.next();
                //Log.d("test", "SaveMovieCsv: id = " + md.getId() + ", watch =" + md.getWatchtoString());
                output.write(md.getWatchtoString().getBytes());
                output.write("\n".getBytes());
            }
            // ストリームを閉じる
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int Random(int id) {
        //現在の秒数を取得
        Date rowdate = new Date();
        String date[] = rowdate.toString().split(" ");
        int min = Integer.parseInt(date[3].substring(6, 8));
        //watchフラグが立っていないものを選ぶ
        MovieData md = movieDatas.getMovieDataAt(id);
        while (md.checkWatch(min % md.getMax()))
            min++;
        md.setWatchbynumber(min % md.getMax());
        return min % md.getMax() + 1;
    }

    public void setReplayView(boolean visible) {
        if (visible) {
            Vv.setVisibility(View.INVISIBLE);
            nMoviereplay.setVisibility(View.VISIBLE);
            nMovieback.setVisibility(View.VISIBLE);
            nMoviebg.setVisibility(View.VISIBLE);
            Vv.setVisibility(View.VISIBLE);
            Vv.seekTo(40000);
            Vv.start();
            Vv.pause();
        } else {
            nMoviereplay.setVisibility(View.INVISIBLE);
            nMovieback.setVisibility(View.INVISIBLE);
            nMoviebg.setVisibility(View.INVISIBLE);
            Vv.setVisibility(View.VISIBLE);
        }
    }
}
