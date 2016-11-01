package com.example.b1014100_2.projectmainver3.quiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b1014100_2.projectmainver3.R;
import com.example.b1014100_2.projectmainver3.zukan.ZukanActivity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by b1014169 on 2016/10/07.
 */
public class QuizActivity extends AppCompatActivity{

    private Button choices[] = new Button[3];
    private ImageView O_img;
    private ImageView X_img;
    private AlphaAnimation O_Anime;
    private AlphaAnimation X_Anime;
    private boolean correct;
    // database関係
    private Quiz quiz;
    private String DB_NAME = "quizdata.db";
    private String TABLE_NAME = "quizdata";
    private int DB_VERSION = 4;
    private String[] FROM = {"_id", "name", "question", "choice1", "choice2", "choice3", "answer", "comment", "image_name"};
    private String ORDER_BY = "_id" + " ASC";//並べる順

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // 図鑑から魚の id か何かを取得し、対応するクイズを表示
        Intent intent = getIntent();
        int id = intent.getIntExtra("ID", 0);
        setQuiz(48);

        //アニメーションセット
        setAnime();

        //正解　画像セット
        O_img = (ImageView) findViewById(R.id.quiz_maru);
        O_img.setVisibility(View.GONE);
        //不正解　画像セット
        X_img = (ImageView) findViewById(R.id.quiz_batsu);
        X_img.setVisibility(View.GONE);
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
        int imageId = getResources().getIdentifier(quiz.getImageName(), "drawable", this.getPackageName());
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

        choices[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(choices[0].getText().toString().equals(quiz.getAnswer()) || quiz.getId() == 7) {
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
                if(choices[1].getText().toString().equals(quiz.getAnswer()) || quiz.getId() == 7) {
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
                if(choices[2].getText().toString().equals(quiz.getAnswer()) || quiz.getId() == 7) {
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
        Button r = (Button) findViewById(R.id.quiz_return_button);
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
        Button r = (Button) findViewById(R.id.quiz_return_button2);
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
        O_Anime.setDuration(1000);
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
        X_Anime.setDuration(1000);
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
