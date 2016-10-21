package com.example.b1014100_2.projectmainver3.quiz;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.map.MapsActivity;
import com.example.b1014100_2.projectmainver3.zukan.ZukanActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by b1014169 on 2016/10/07.
 */
public class QuizActivity extends AppCompatActivity{

    Button choices[] = new Button[3];
    ImageView O_img;
    ImageView X_img;
    AlphaAnimation O_Anime;
    AlphaAnimation X_Anime;
    boolean correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // 図鑑から魚の id か何かを取得し、対応するクイズを表示
        setQuiz(1);

        //アニメーションセット
        setAnime();

        //正解　画像セット
        O_img = (ImageView) findViewById(R.id.quiz_O);
        O_img.setVisibility(View.GONE);
        //不正解　画像セット
        X_img = (ImageView) findViewById(R.id.quiz_X);
        X_img.setVisibility(View.GONE);
    }

    // 1～63
    private void setQuiz(int i){
        // クイズデータの取得
        QuizData.set(i);

        // 魚の画像を設定
        ImageView image = (ImageView) findViewById(R.id.quiz_image);
        image.setImageResource(QuizData.imageId);
        // 問題文の設定
        TextView question = (TextView) findViewById(R.id.quiz_question);
        question.setText(QuizData.question);
        // 選択肢ボタンの設定
        // 配列からListへ変換します。
        List<String> list= Arrays.asList(QuizData.choices);
        // リストの並びをシャッフルします。
        Collections.shuffle(list);
        choices[0] = (Button) findViewById(R.id.quiz_choice1);
        choices[0].setText(list.get(0));
        choices[1] = (Button) findViewById(R.id.quiz_choice2);
        choices[1].setText(list.get(1));
        choices[2] = (Button) findViewById(R.id.quiz_choice3);
        choices[2].setText(list.get(2));

        choices[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choices[0].getText().toString().equals(QuizData.answer)) {
                    // 正解演出
                    correct = true;
                    O_img.setVisibility(View.VISIBLE);
                    O_img.startAnimation(O_Anime);
                }else{
                    // 不正解演出
                    correct = false;
                    X_img.setVisibility(View.VISIBLE);
                    X_img.startAnimation(X_Anime);
                }
            }
        });

        choices[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choices[1].getText().toString().equals(QuizData.answer)) {
                    // 正解演出
                    correct = true;
                    O_img.setVisibility(View.VISIBLE);
                    O_img.startAnimation(O_Anime);
                }else{
                    // 不正解演出
                    correct = false;
                    X_img.setVisibility(View.VISIBLE);
                    X_img.startAnimation(X_Anime);
                }
            }
        });

        choices[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choices[2].getText().toString().equals(QuizData.answer)) {
                    // 正解演出
                    correct = true;
                    O_img.setVisibility(View.VISIBLE);
                    O_img.startAnimation(O_Anime);
                }else{
                    // 不正解演出
                    correct = false;
                    X_img.setVisibility(View.VISIBLE);
                    X_img.startAnimation(X_Anime);
                }
            }
        });

        // 戻るボタンの設定
        Button r = (Button) findViewById(R.id.main_return_button);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 図鑑画面に遷移
                Intent intent = new Intent(QuizActivity.this, ZukanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setResult(){
        // result画面に変更
        setContentView(R.layout.result_quiz);
        // 正解・不正解の設定
        TextView O_X = (TextView) findViewById(R.id.quiz_correct);
        ImageView ika = (ImageView) findViewById(R.id.result_ika);
        ImageView ox = (ImageView) findViewById(R.id.quiz_ox);
        if(correct) {
            O_X.setText("正解");
            ika.setImageResource(R.drawable.ika_o);
            ox.setImageResource(R.drawable.quiz_o);
        }else{
            O_X.setText("不正解");
            // 調整中
            ika.setImageResource(R.drawable.ika_x);
            ika.setMaxWidth(250);
            ika.setMaxHeight(250);
            ox.setImageResource(R.drawable.quiz_x);
        }
        // 正解の選択肢の設定
        TextView answer = (TextView) findViewById(R.id.quiz_answer);
        answer.setText(QuizData.getAnswer());
        // 解説の設定
        TextView comment = (TextView) findViewById(R.id.quiz_comment);
        comment.setText(QuizData.getComment());
        // 戻るボタンの設定
        Button r = (Button) findViewById(R.id.result_return_button);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 図鑑画面に遷移
                Intent intent = new Intent(QuizActivity.this, ZukanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAnime() {
        //正解アニメーション
        O_Anime = new AlphaAnimation(0, 1);
        O_Anime.setDuration(2000);
        O_Anime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //表示され終わったとき
            @Override
            public void onAnimationEnd(Animation animation) {
                setResult();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        //不正解アニメーション
        X_Anime = new AlphaAnimation(0, 1);
        X_Anime.setDuration(2000);
        X_Anime.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            //表示され終わったとき
            @Override
            public void onAnimationEnd(Animation animation) {
                setResult();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

}