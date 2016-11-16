/*
 * SimplePlayer
 * Android example of Panframe library
 * The example plays back an panoramic movie from a resource.
 * 
 * (c) 2012-2013 Mindlight. All rights reserved.
 * Visit www.panframe.com for more information. 
 * 
 */

package com.example.b1014100_2.projectmainver3.movie;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.R;
import com.panframe.android.lib.PFAsset;
import com.panframe.android.lib.PFAssetObserver;
import com.panframe.android.lib.PFAssetStatus;
import com.panframe.android.lib.PFHotspot;
import com.panframe.android.lib.PFHotspotClickListener;
import com.panframe.android.lib.PFNavigationMode;
import com.panframe.android.lib.PFObjectFactory;
import com.panframe.android.lib.PFView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;

public class MovieActivity extends FragmentActivity implements PFAssetObserver, OnSeekBarChangeListener, PFHotspotClickListener {

    PFView _pfview;
    PFAsset _pfasset;
    PFNavigationMode _currentNavigationMode = PFNavigationMode.MOTION;

    boolean _updateThumb = true;
    ;
    Timer _scrubberMonitorTimer;

    ViewGroup _frameContainer;
    Button _stopButton;
    Button _playButton;
    Button _touchButton;
    SeekBar _scrubber;
    OrientationEventListener ol;
    Button testbutton;
    //add 2016 10 03 Kenta Fukaya
    String moviename = "skyrim360.mp4";
    String moviepath;
    int id,random;
    AggregateMovieData movieDatas = new AggregateMovieData();
    ImageButton backButton, replayButton;
    ImageView movieBg;

    /**
     * Creation and initalization of the Activitiy.
     * Initializes variables, listeners, and starts request of a movie list.
     *
     * @param savedInstanceState a saved instance of the Bundle
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movie);

        _frameContainer = (ViewGroup) findViewById(R.id.framecontainer);
        _frameContainer.setBackgroundColor(0xFF000000);

        _playButton = (Button) findViewById(R.id.playbutton);
        _stopButton = (Button) findViewById(R.id.stopbutton);
        _touchButton = (Button) findViewById(R.id.touchbutton);
        _scrubber = (SeekBar) findViewById(R.id.scrubber);

        backButton = (ImageButton) findViewById(R.id.movie_back_button);
        replayButton = (ImageButton) findViewById(R.id.movie_replay_button);
        movieBg = (ImageView) findViewById(R.id.movie_bg);

        _playButton.setOnClickListener(playListener);
        _stopButton.setOnClickListener(stopListener);
        _touchButton.setOnClickListener(touchListener);
        _scrubber.setOnSeekBarChangeListener(this);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//backto mapActivity
            }
        });
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieActivity.this, MovieActivity.class); //ダイビングアクティビティに飛ぶ処理
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });
        _scrubber.setEnabled(false);

        setReplayView(false);//set INVISIBLE
        showControls(false);//set moviemenu VISBE

        //add 2016 10 03 Kenta Fukaya
        Intent intent = getIntent();
        intent.getIntExtra("id", 0);
        id = intent.getIntExtra("id", 0);

        //select movie name at random
        ReadMovieCsv();
        moviename = movieDatas.getMovieDataAt(id).getMovieName();
        moviename = moviename.substring(0,moviename.length() -4);
        random  = Random(id);
        Log.d("TEST", "onCreate: MovieName is  " +moviename + ",random = " + Random(id));
        SaveMovieCsv();

        String path = Environment.getExternalStorageDirectory().getPath();
        File dir = new File(path+"/Movies/"+moviename);
        File video = new File(dir.getAbsolutePath()+"/"+moviename+random+".mp4");
        if(!video.exists())
            random = 1;

        Log.d("movieActivity", "onCreate: video = "+video.getPath()+", exsist =" + video.exists());
        moviepath = video.getPath();
        //auto start
            loadVideo(moviepath);
    }


    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (_pfview != null)
            _pfview.handleOrientationChange();
    }

    /**
     * Show/Hide the playback controls
     *
     * @param bShow Show or hide the controls. Pass either true or false.
     */
    public void showControls(boolean bShow) {
        int visibility = View.GONE;

        if (bShow)
            visibility = View.VISIBLE;

        _playButton.setVisibility(visibility);
        _stopButton.setVisibility(visibility);
        _touchButton.setVisibility(visibility);
        _scrubber.setVisibility(visibility);

        if (_pfview != null) {
            if (!_pfview.supportsNavigationMode(PFNavigationMode.MOTION))
                _touchButton.setVisibility(View.GONE);
        }
    }


    /**
     * Start the video with a local file path
     *
     * @param filename The file path on device storage
     */
    public void loadVideo(String filename) {

        _pfview = PFObjectFactory.view(this);
        _pfasset = PFObjectFactory.assetFromUri(this, Uri.parse(filename), this);

        _pfview.displayAsset(_pfasset);
        _pfview.setNavigationMode(_currentNavigationMode);
       /*
        _pfview.setBlindSpotImage(BitmapFactory.decodeResource(getResources(), R.raw.blackspot));
        _pfview.setBlindSpotPosition(1);
        _pfview.setBlindSpotScale(1.5f);
        
        
        PFHotspot hp1 = _pfview.createHotspot(BitmapFactory.decodeResource(getResources(), R.raw.hotspot));
        hp1.setTag(10);
        hp1.setCoordinates(60, 40, 0);
        hp1.setClickListener(this);
        
        PFHotspot hp2 = _pfview.createHotspot(BitmapFactory.decodeResource(getResources(), R.raw.hotspot));
        hp2.setTag(20);
        hp2.setCoordinates(0, 40, 0);
        hp2.setClickListener(this);
        */
        _frameContainer.addView(_pfview.getView(), 0);
        _pfasset.setPLaybackTime(0);
        _updateThumb = false;
        _pfasset.play();

    }

    /**
     * Status callback from the PFAsset instance.
     * Based on the status this function selects the appropriate action.
     *
     * @param asset  The asset who is calling the function
     * @param status The current status of the asset.
     */
    public void onStatusMessage(final PFAsset asset, PFAssetStatus status) {
        switch (status) {
            case LOADED:
                Log.d("SimplePlayer", "Loaded");
                break;
            case DOWNLOADING:
                Log.d("SimplePlayer", "Downloading 360� movie: " + _pfasset.getDownloadProgress() + " percent complete");
                break;
            case DOWNLOADED:
                Log.d("SimplePlayer", "Downloaded to " + asset.getUrl());
                break;
            case DOWNLOADCANCELLED:
                Log.d("SimplePlayer", "Download cancelled");
                break;
            case PLAYING:
                Log.d("SimplePlayer", "Playing");
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                _scrubber.setEnabled(true);
                _scrubber.setMax((int) asset.getDuration());
                _playButton.setText("pause");
                if (_scrubberMonitorTimer == null) {
                    _scrubberMonitorTimer = new Timer();
                    final TimerTask task = new TimerTask() {
                        public void run() {
                            if (_updateThumb) {
                                _scrubber.setMax((int) asset.getDuration());
                                _scrubber.setProgress((int) asset.getPlaybackTime());
                            }
                        }
                    };
                    _scrubberMonitorTimer.schedule(task, 0, 33);
                }
                break;
            case PAUSED:
                Log.d("SimplePlayer", "Paused");
                _playButton.setText("play");
                //_pfview.injectImageFromResource(R.raw.pausescreen);
                break;
            case STOPPED:
                Log.d("SimplePlayer", "Stopped");
                _playButton.setText("play");
                if (_scrubberMonitorTimer != null) {
                    _scrubberMonitorTimer.cancel();
                    _scrubberMonitorTimer.purge();
                    _scrubberMonitorTimer = null;
                }
                _scrubber.setProgress(0);
                _scrubber.setEnabled(false);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                break;
            case COMPLETE:
                Log.d("SimplePlayer", "Complete");
                _playButton.setText("play");
                if (_scrubberMonitorTimer != null) {
                    _scrubberMonitorTimer.cancel();
                    _scrubberMonitorTimer.purge();
                    _scrubberMonitorTimer = null;
                }
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                setReplayView(true);//set VISIBLE
                showControls(false);//movie menu INVISIBLE
                break;
            case ERROR:
                Log.d("SimplePlayer", "Error");
                break;
        }
    }

    /**
     * Click listener for the play/pause button
     */
    private OnClickListener playListener = new OnClickListener() {
        public void onClick(View v) {

            if (_pfasset == null)
                loadVideo(moviepath);

            if (_pfasset.getStatus() == PFAssetStatus.PLAYING) {
                _pfasset.pause();
            } else {
                if (_pfview != null) {
                    _pfview.injectImage(null);
                }
                _pfasset.play();
            }
        }
    };

    /**
     * Click listener for the stop/back button
     */
    private OnClickListener stopListener = new OnClickListener() {
        public void onClick(View v) {
            if (_pfasset == null)
                return;

            _pfasset.stop();

            // cleanup
            _pfview.release();
            _pfasset.release();

            _pfasset = null;

            _frameContainer.removeView(_pfview.getView());
            _pfview = null;

        }
    };

    /**
     * Click listener for the navigation mode (touch/motion (if available))
     */
    private OnClickListener touchListener = new OnClickListener() {
        public void onClick(View v) {
            if (_pfview != null) {
                Button touchButton = (Button) findViewById(R.id.touchbutton);
                if (_currentNavigationMode == PFNavigationMode.TOUCH) {
                    _currentNavigationMode = PFNavigationMode.MOTION;
                    touchButton.setText("motion");
                } else {
                    _currentNavigationMode = PFNavigationMode.TOUCH;
                    touchButton.setText("touch");
                }
                _pfview.setNavigationMode(_currentNavigationMode);
            }
        }
    };

    /**
     * Setup the options menu
     *
     * @param menu The options menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_movie, menu);
        return true;
    }

    /**
     * Called when pausing the app.
     * This function pauses the playback of the asset when it is playing.
     */
    public void onPause() {
        super.onPause();
        if (_pfasset != null) {
            if (_pfasset.getStatus() == PFAssetStatus.PLAYING)
                _pfasset.pause();
        }
    }

    /**
     * Called when a previously created loader is being reset, and thus making its data unavailable.
     *
     * @param seekbar  The SeekBar whose progress has changed
     * @param progress The current progress level.
     * @param fromUser True if the progress change was initiated by the user.
     */
    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
    }

    /**
     * Notification that the user has started a touch gesture.
     * In this function we signal the timer not to update the playback thumb while we are adjusting it.
     *
     * @param seekbar The SeekBar in which the touch gesture began
     */
    public void onStartTrackingTouch(SeekBar seekbar) {
        _updateThumb = false;
    }

    /**
     * Notification that the user has finished a touch gesture.
     * In this function we request the asset to seek until a specific time and signal the timer to resume the update of the playback thumb based on playback.
     *
     * @param seekbar The SeekBar in which the touch gesture began
     */
    public void onStopTrackingTouch(SeekBar seekbar) {
        _pfasset.setPLaybackTime(seekbar.getProgress());
        _pfasset.setPLaybackTime(0);

        Log.d("test", "onStopTrackingTouch: ON");
        _updateThumb = true;
    }


    /**
     * Notification that the user has touched a hotspot.
     *
     * @param hotspot The hotspot that was touched or triggered.
     */
    public void onClick(PFHotspot hotspot) {
//		hotspot.animate();
        hotspot.setEnabled(false);
        Log.d("SimplePlayer", "Hotspot clicked: " + hotspot.getTag());
    }


    @Override
    public void onFocusIn(PFHotspot arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onFocusOut(PFHotspot arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * Click listener for the stop/back button
     */
    private OnClickListener testListener = new OnClickListener() {
        public void onClick(View v) {
            Intent intent = getIntent();
            finish();
            intent.putExtra("id", id);
            startActivity(intent);
        }
    };

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
                movieDatas.appendMovieData(new MovieData(id, max, name));
                // Log.d("ReadMovieCsv", "id = "+id +", max = "+max+", name ="+name);
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
                    //if(s % 2 == 1)                   movieDatas.getMovieDataAt(i).setWatchbynumber(s,1);
                    //else movieDatas.getMovieDataAt(i).setWatchbynumber(s,0);
                    // Log.d("TEST", "ReadMovieCsv: id = "+i+",s ="+s+",watch = "+movieDatas.getMovieDataAt(i).getWatch(s));
                    s++;
                }
                // Log.d("TEST", "ReadMovieCsv: id = "+i+",watch = "+movieDatas.getMovieDataAt(i).getWatchtoString());
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
                Log.d("test", "SaveMovieCsv: id = " + md.getId() + ", watch =" + md.getWatchtoString());
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
        //Log.d("TEST", "Random: id="+id+",return ="+min%md.getMax());
        return min % md.getMax() + 1;
    }

    public void setReplayView(boolean check) {
        if (check) {
            backButton.setVisibility(View.VISIBLE);
            replayButton.setVisibility(View.VISIBLE);
            movieBg.setVisibility(View.VISIBLE);
        } else {
            backButton.setVisibility(View.INVISIBLE);
            replayButton.setVisibility(View.INVISIBLE);
            movieBg.setVisibility(View.INVISIBLE);
        }
    }
}
