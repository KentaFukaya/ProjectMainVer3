package com.example.b1014100_2.projectmainver3.Result;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.b1014100_2.projectmainver3.R;

public class ResultActivity extends AppCompatActivity {
    ImageButton movie,zukan,quiz;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d("TEST", "onCreate: resultStarted");
    }

}
