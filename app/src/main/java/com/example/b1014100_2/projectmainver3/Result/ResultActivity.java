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
import android.widget.ListView;
import android.widget.ScrollView;
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
    int mode = 0, mode_old = 0;
    AggregateResultData resultdates = new AggregateResultData();
    ResultListAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ArrayList<ResultData> resultDatas = ResultsDatabase.getResultDatasAll(this);

        resultdates.appendResultData(new ResultData(0,1,0,1,"テスト0","説明0"));
        resultdates.appendResultData(new ResultData(1,1,0,2,"テスト1","説明1"));
        resultdates.appendResultData(new ResultData(2,1,0,3,"テスト2","説明2"));
        resultdates.appendResultData(new ResultData(3,1,0,4,"テスト3","説明3"));
        resultdates.appendResultData(new ResultData(4,1,0,5,"テスト4","説明4"));
        resultdates.appendResultData(new ResultData(5,0,0,1,"テスト5","説明5"));
        resultdates.appendResultData(new ResultData(6,0,0,2,"テスト6","説明6"));
        resultdates.appendResultData(new ResultData(7,0,0,3,"テスト7","説明7"));
        resultdates.appendResultData(new ResultData(8,0,0,4,"テスト8","説明8"));
        resultdates.appendResultData(new ResultData(9,0,0,5,"テスト9","説明9"));

        resultdates.appendResultData(new ResultData(10,1,1,1,"テスト10","説明10"));
        resultdates.appendResultData(new ResultData(11,1,1,2,"テスト11","説明11"));
        resultdates.appendResultData(new ResultData(12,1,1,3,"テスト12","説明12"));
        resultdates.appendResultData(new ResultData(13,1,1,4,"テスト13","説明13"));
        resultdates.appendResultData(new ResultData(14,1,1,5,"テスト14","説明14"));
        resultdates.appendResultData(new ResultData(15,0,1,1,"テスト15","説明15"));
        resultdates.appendResultData(new ResultData(16,0,1,2,"テスト16","説明16"));
        resultdates.appendResultData(new ResultData(17,0,1,3,"テスト17","説明17"));
        resultdates.appendResultData(new ResultData(18,0,1,4,"テスト18","説明18"));
        resultdates.appendResultData(new ResultData(19,0,1,5,"テスト19","説明19"));

        resultdates.appendResultData(new ResultData(20,1,2,1,"テスト20","説明20"));
        resultdates.appendResultData(new ResultData(21,1,2,2,"テスト21","説明21"));
        resultdates.appendResultData(new ResultData(22,1,2,3,"テスト22","説明22"));
        resultdates.appendResultData(new ResultData(23,1,2,4,"テスト23","説明23"));
        resultdates.appendResultData(new ResultData(24,1,2,5,"テスト24","説明24"));
        resultdates.appendResultData(new ResultData(25,0,2,1,"テスト25","説明25"));
        resultdates.appendResultData(new ResultData(26,0,2,2,"テスト26","説明26"));
        resultdates.appendResultData(new ResultData(27,0,2,3,"テスト27","説明27"));
        resultdates.appendResultData(new ResultData(28,0,2,4,"テスト28","説明28"));
        resultdates.appendResultData(new ResultData(29,0,2,5,"テスト29","説明29"));

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



        // リストビューのアイテムがクリックされた時に呼び出されるコールバックリスナーを登録します
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します
                ResultData item = (ResultData) listView.getItemAtPosition(position);
                Toast.makeText(ResultActivity.this, item.getDesc(), Toast.LENGTH_LONG).show();
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
                mode = 0;
                setButtons();
                setAdapter();
            }
        });

        zukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 1;
                setButtons();
                setAdapter();

            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 2;
                setButtons();
                setAdapter();
            }
        });
    }


    public void setButtons(){
        switch (mode_old){
            case 0:
                movie.setImageResource(R.drawable.result_button_movie);
                break;

            case 1:
                zukan.setImageResource(R.drawable.result_button_zukan);
                break;

            case 2:
                quiz.setImageResource(R.drawable.result_button_quiz);
                break;
        }

        switch (mode){
            case 0:
                movie.setImageResource(R.drawable.result_button_movie_onclick);
                break;

            case 1:
                zukan.setImageResource(R.drawable.result_button_zukan_onclick);
                break;

            case 2:
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
}
