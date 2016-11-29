package com.example.b1014100_2.projectmainver3.Result;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.b1014100_2.projectmainver3.HomeActivity;
import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.map.MapsActivity;

public class ResultActivity extends AppCompatActivity {
    ImageButton movie,zukan,quiz;
    ImageButton back;
    int mode = 0, mode_old = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d("TEST", "onCreate: resultStarted");


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
            }
        });

        zukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 1;
                setButtons();
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode_old = mode;
                mode = 2;
                setButtons();
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

}
