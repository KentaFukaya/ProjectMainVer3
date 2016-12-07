package com.example.b1014100_2.projectmainver3.Result;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b1014100_2.projectmainver3.DesiginPattern.Iterator;
import com.example.b1014100_2.projectmainver3.HomeActivity;
import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.map.MapData;
import com.example.b1014100_2.projectmainver3.map.MapsActivity;

import java.util.ArrayList;

import static android.R.attr.id;

public class ResultActivity extends AppCompatActivity {
    ImageButton movie,zukan,quiz;
    ImageButton back;
    int mode = 1, mode_old = 1;
    AggregateResultData resultdates;
    ResultListAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ResultManager.titleCheckTrueorFalse(this,false);
        resultdates = new AggregateResultData(ResultsDatabase.getResultDatasAll(this));
        ResultsDatabase.setResultsTrueAll(this);
        listView = (ListView) findViewById(R.id.result_listview);
        //viewpagerのスクロールバーを消す
        listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        listView.setVerticalScrollBarEnabled(false);

        // add header and footer
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View footer = inflater.inflate(R.layout.result_list_footer, listView, false);
        listView.addFooterView(footer, null, false);
        listView.addHeaderView(footer, null, false);

        setAdapter();
        setResultBar();


        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                ResultData item = (ResultData) listView.getItemAtPosition(position);
                if(item.getState() == 1)
                    Toast.makeText(ResultActivity.this, item.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });



        back = (ImageButton) findViewById(R.id.result_button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        movie = (ImageButton) findViewById(R.id.result_movie);
        zukan = (ImageButton) findViewById(R.id.result_zukan);
        quiz = (ImageButton) findViewById(R.id.result_quiz);

        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 1;
                setButtons();
                setAdapter();
            }
        });

        zukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 2;
                setButtons();
                setAdapter();

            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 3;
                setButtons();
                setAdapter();
            }
        });
    }


    public void setButtons(){
        switch (mode_old){
            case 1:
                movie.setImageResource(R.drawable.result_button_movie);
                break;

            case 2:
                zukan.setImageResource(R.drawable.result_button_zukan);
                break;

            case 3:
                quiz.setImageResource(R.drawable.result_button_quiz);
                break;
        }

        switch (mode){
            case 1:
                movie.setImageResource(R.drawable.result_button_movie_onclick);
                break;

            case 2:
                zukan.setImageResource(R.drawable.result_button_zukan_onclick);
                break;

            case 3:
                quiz.setImageResource(R.drawable.result_button_quiz_onclick);

        }
    }

    public void setAdapter(){
        adapter = new ResultListAdapter(getApplicationContext());
        Iterator iterator = resultdates.Iterator();
        while(iterator.hasNext()){
            ResultData resultData = (ResultData) iterator.next();
            if(resultData.getMode() == mode)
                adapter.add(resultData);
        }
        // アダプターを設定します
        listView.setAdapter(adapter);
    }

    private void setResultBar(){
        double rate = ResultsDatabase.getRateResultsTrue(this) * 100.0;
        Log.d("resultactivity", "setResultBar: rate:" + rate);

        TextView rateParcent = (TextView) findViewById(R.id.result_parcent);
        TextView rateView = (TextView) findViewById(R.id.result_rate);
        rateView.setText(String.valueOf((int)rate));

        ImageView bar = (ImageView) findViewById(R.id.imageView);

        if(rate < 20)
            bar.setImageResource(R.drawable.tutorial_20);
        else if(rate < 40)
            bar.setImageResource(R.drawable.tutorial_40);
        else if(rate < 60)
            bar.setImageResource(R.drawable.tutorial_60);
        else if(rate < 80)
            bar.setImageResource(R.drawable.tutorial_80);
        else
            bar.setImageResource(R.drawable.tutorial_100);

    }
}
