package com.example.b1014100_2.projectmainver3.quiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by b1014169 on 2016/10/07.
 */
public class QuizActivity extends AppCompatActivity{

    private Button choices[] = new Button[3];
    private ImageButton re, re2;
    private boolean correct;
    private QuizO quiz_O;
    private QuizX quiz_X;
    private QuizOAnimation animeO;
    private QuizXAnimation animeX;
    protected AttributeSet as = null;
    protected QuizActivity activity;
    // database関係
    private Quiz quiz;
    private String DB_NAME = "noadd_quizdata.db";
    private String TABLE_NAME = "quizdata";
    private int DB_VERSION = 3;
    private String[] FROM = {"_id", "name", "question", "choice1", "choice2", "choice3", "answer", "comment", "image_name"};
    private String ORDER_BY = "_id" + " ASC";//並べる順

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // 図鑑から魚の id か何かを取得し、対応するクイズを表示
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        setQuiz(id);

        activity = this;
    }

    // i問目のクイズを取得
    private void setQuiz(int i){
        QuizSQLiteOpenFromAssets helper = new QuizSQLiteOpenFromAssets(this, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        c.moveToPosition(i - 1);
        quiz= new Quiz();

        quiz.setId(c.getInt(0));
        quiz.setName(c.getString(1));
        quiz.setQuestion(c.getString(2));
        quiz.setChoice1(c.getString(3));
        quiz.setChoice2(c.getString(4));
        quiz.setChoice3(c.getString(5));
        quiz.setAnswer(c.getString(6));
        quiz.setComment(c.getString(7));
        quiz.setImageName(c.getString(8));

        // 魚の画像を設定
        ImageView image = (ImageView) findViewById(R.id.quiz_image);
        //文字列から画像のdrawableのIDを取得する
        int imageId = getResources().getIdentifier(quiz.getImageName(), "drawable", getPackageName());
        image.setImageResource(imageId);
        // 問題文の設定
        TextView question = (TextView) findViewById(R.id.quiz_question);
        question.setText(quiz.getQuestion());
        // 選択肢ボタンの設定
        ArrayList<String> list= new ArrayList();
        list.add(quiz.getChoice1());
        list.add(quiz.getChoice2());
        list.add(quiz.getChoice3());
        // リストの並びをシャッフルします。
        Collections.shuffle(list);

        choices[0] = (Button) findViewById(R.id.quiz_choice1);
        choices[0].setText(list.get(0));
        choices[1] = (Button) findViewById(R.id.quiz_choice2);
        choices[1].setText(list.get(1));
        choices[2] = (Button) findViewById(R.id.quiz_choice3);
        choices[2].setText(list.get(2));

        quiz_O = (QuizO) findViewById(R.id.quiz_O);
        quiz_X = (QuizX) findViewById(R.id.quiz_X);

        choices[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 3; i++){
                    choices[i].setEnabled(false);
                }
                // 7問目はすべて正解
                if(choices[0].getText().toString().equals(quiz.getAnswer()) || quiz.getId() == 7) {
                    // 正解演出
                    correct = true;
                    // アニメーションセット
                    setAnime();
                    quiz_O.startAnimation(animeO);
                }else{
                    // 不正解演出
                    correct = false;
                    // アニメーションセット
                    setAnime();
                    quiz_X.startAnimation(animeX);
                }
            }
        });
        choices[0].setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    choices[0].setBackgroundResource(R.drawable.quiz_sentakushi_push);
                    choices[0].setTextColor(Color.rgb(245,124,0));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    choices[0].setBackgroundResource(R.drawable.quiz_sentakushi);
                    choices[0].setTextColor(Color.rgb(255,255,255));
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });

        choices[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 3; i++){
                    choices[i].setEnabled(false);
                }
                if(choices[1].getText().toString().equals(quiz.getAnswer()) || quiz.getId() == 7) {
                    // 正解演出
                    correct = true;
                    // アニメーションセット
                    setAnime();
                    quiz_O.startAnimation(animeO);
                }else{
                    // 不正解演出
                    correct = false;
                    // アニメーションセット
                    setAnime();
                    quiz_X.startAnimation(animeX);
                }
            }
        });
        choices[1].setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    choices[1].setBackgroundResource(R.drawable.quiz_sentakushi_push);
                    choices[1].setTextColor(Color.rgb(245,124,0));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    choices[1].setBackgroundResource(R.drawable.quiz_sentakushi);
                    choices[1].setTextColor(Color.rgb(255,255,255));
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });

        choices[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < 3; i++){
                    choices[i].setEnabled(false);
                }
                if(choices[2].getText().toString().equals(quiz.getAnswer()) || quiz.getId() == 7) {
                    // 正解演出
                    correct = true;
                    // アニメーションセット
                    setAnime();
                    quiz_O.startAnimation(animeO);
                }else{
                    // 不正解演出
                    correct = false;
                    // アニメーションセット
                    setAnime();
                    quiz_X.startAnimation(animeX);
                }
            }
        });
        choices[2].setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    choices[2].setBackgroundResource(R.drawable.quiz_sentakushi_push);
                    choices[2].setTextColor(Color.rgb(245,124,0));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    choices[2].setBackgroundResource(R.drawable.quiz_sentakushi);
                    choices[2].setTextColor(Color.rgb(255,255,255));
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });

        // 戻るボタンの設定
        re = (ImageButton) findViewById(R.id.quiz_return_button);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 図鑑画面に遷移
                finish();
            }
        });
        re.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    re.setImageResource(R.drawable.zukan_list_back_button_pressed);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    re.setImageResource(R.drawable.zukan_list_back_button);
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });
    }

    protected void setResult(){
        // result画面に変更
        setContentView(R.layout.activity_quiz2);
        // 正解・不正解の設定
        TextView O_X = (TextView) findViewById(R.id.quiz_correct);
        ImageView ika = (ImageView) findViewById(R.id.quiz_ika2);
        ImageView maru_batsu = (ImageView) findViewById(R.id.quiz_maru_batsu);
        if(correct || quiz.getId() == 7) {
            O_X.setText("正解");
            ika.setImageResource(R.drawable.quiz_ika_happy);
            maru_batsu.setImageResource(R.drawable.quiz_hanamaru);
        }else{
            O_X.setText("不正解");
            ika.setImageResource(R.drawable.quiz_ika_sad);
            maru_batsu.setImageResource(R.drawable.quiz_batsu);
        }
        // 正解の選択肢の設定
        TextView answer = (TextView) findViewById(R.id.quiz_answer);
        answer.setText("正解は・・・「" + quiz.getAnswer() + "」だよ");
        // 解説の設定
        TextView comment = (TextView) findViewById(R.id.quiz_comment);
        comment.setText(quiz.getComment());
        // 戻るボタンの設定
        re2 = (ImageButton) findViewById(R.id.quiz_return_button2);
        re2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 図鑑画面に遷移
                finish();
            }
        });
        re2.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //押したときの動作
                    re2.setImageResource(R.drawable.quiz_return_button_pressed);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //離したときの動作
                    re2.setImageResource(R.drawable.quiz_return_button);
                }
                return false; //trueにすると他のリスナーが呼ばれない
            }
        });
    }

    private void setAnime() {
        //正解アニメーション
        animeO = new QuizOAnimation(quiz_O, 360);
        // アニメーションの起動期間を設定
        animeO.setDuration(1000);
        animeO.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try{
                    Thread.sleep(400);
                }catch(InterruptedException e){
                }finally{
                    setResult();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //不正解アニメーション
        animeX = new QuizXAnimation(quiz_X, quiz_X.getPositionX1() + 2 * quiz_X.radius, quiz_X.getPositionY1() + 2 * quiz_X.radius, quiz_X.getPositionX2() - 2 * quiz_X.radius, quiz_X.getPositionY2() + 2 * quiz_X.radius);
        // アニメーションの起動期間を設定
        animeX.setDuration(1000);
        animeX.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try{
                    Thread.sleep(400);
                }catch(InterruptedException e){
                }finally{
                    setResult();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
