package com.example.b1014100_2.projectmainver3.Result;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.TABLE_MOVIE;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.TABLE_QUIZ;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.TABLE_RESULT;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.TABLE_ZUKAN;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.TABLE_QUIZ_CORRECT;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.TABLE_NEW;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.RECORDS_NEW_MOVIE;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.RECORDS_NEW_MOVIE_NORMAL;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.RECORDS_NEW_MOVIE_SPHERE;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.RECORDS_NEW_ZUKAN;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.RECORDS_NEW_QUIZ_CORRECT;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.isRecordsIn;
import static com.example.b1014100_2.projectmainver3.Result.ResultsDatabase.setRecordsTrue;

/**
 * Created by on 2016/11/28.
 */

public class ResultManager {

    public static final int MOVIE_ACTIVITY = TABLE_MOVIE;
    public static final int ZUKAN_ACTIVITY = TABLE_ZUKAN;
    public static final int QUIZ_ACTIVITY = TABLE_QUIZ;

    public static final int MOVIE_NORMAL = 0;
    public static final int MOVIE_SPEHER = 1;


    //TABLE_MOVIE の flag を true にする 動画専用
    static void setRecordFlagTrue(Context context, int activityName, int id, int movieType) {
        //activityName が異なるとき
        if(activityName != MOVIE_ACTIVITY){
            Log.d("ResultManager", "setRecordFlagTrue: " + "error activityName");
            return;
        }

        int recordsNewId;

        if(movieType == MOVIE_NORMAL)
            recordsNewId = RECORDS_NEW_MOVIE_NORMAL;
        else
            recordsNewId = RECORDS_NEW_MOVIE_SPHERE;

        //動画の初めてチェック
        if(!isRecordsIn(context, TABLE_NEW, new String[]{String.valueOf(RECORDS_NEW_MOVIE)})){
            setRecordsTrue(context, TABLE_NEW, RECORDS_NEW_MOVIE);
        }
        //普通 or 360° の動画チェック
        if(!isRecordsIn(context, TABLE_NEW, new String[]{String.valueOf(recordsNewId)})){
            setRecordsTrue(context, TABLE_NEW, recordsNewId);
        }

        //引数の異なる方を呼ぶ
        setRecordFlagTrue(context, activityName, id);
    }

    //TABLE_RESULT TABLE_MOVIE TABLE_ZUKAN TABLE_QUIZ の flag を true にする
    public static void setRecordFlagTrue(Context context, int activityName, int id){
        //activityName が異なるとき
        if(activityName != MOVIE_ACTIVITY && activityName != ZUKAN_ACTIVITY && activityName != QUIZ_ACTIVITY){
            Log.d("ResultManager", "setRecordFlagTrue: " + "error activityName");
            return;
        }

        //flag を true にする
        ResultsDatabase.setRecordsTrue(context, activityName, id);

        //図鑑の初めてチェック
        if(activityName == ZUKAN_ACTIVITY){
            //初めてだった時
            if(!isRecordsIn(context, TABLE_NEW, new String[]{String.valueOf(RECORDS_NEW_ZUKAN)})){
                ResultsDatabase.setRecordsTrue(context, TABLE_NEW, RECORDS_NEW_ZUKAN);
            }
        }

        //クイズの正解の初めて 正解数 チェック
        if(activityName == QUIZ_ACTIVITY){

            //正解数を＋１
            int oldSumRight = ResultsDatabase.getRecordsQuizSumRight(context, id);
            ResultsDatabase.setRecordsQuizSumRight(context, id, oldSumRight);

            //初めてだった時
            if(!isRecordsIn(context, TABLE_NEW, new String[]{String.valueOf(RECORDS_NEW_QUIZ_CORRECT)})){
                ResultsDatabase.setRecordsTrue(context, TABLE_NEW, RECORDS_NEW_QUIZ_CORRECT);
            }

            //正解数チェック
            ArrayList<RecordsQuizCorrect> recordsQuizCorrects = ResultsDatabase.getRecordsQuizCorrectFalse(context);
            if(!recordsQuizCorrects.isEmpty()){
                //正解数テーブル(quiz_correct)の要素が false のものだけをチェック
                for (int i = 0; i < recordsQuizCorrects.size(); i++) {
//                    int sum = ResultsDatabase.getSumRecordsQuizSumRight(context);
                    int sum = (int)ResultsDatabase.getSumRecordsQuizTrue(context);
                    Log.d("ResultManager", "setRecordFlagTrue: sumRecordsQuiz:" + sum);
                    //各正解数をチェック
                    if(recordsQuizCorrects.get(i).getCorrect() <= sum){
                        int idQuizCorrect = recordsQuizCorrects.get(i).getId();
                        setRecordsTrue(context, TABLE_QUIZ_CORRECT, idQuizCorrect);
                    }
                }
            }

        }
        titleCheckTrueorFalse(context,true);
    }

    static void titleCheckTrueorFalse(Context context,Boolean check){
        ArrayList<Result> results = ResultsDatabase.getResultsFalse(context);
        boolean elementFlag;

        //flag が false の result を全て確認
        for (int i = 0; i < results.size(); i++) {
            elementFlag = true;
            //必要な要素を , で区切って配列に入れる
            String[][] elements = new String[6][];
            if(results.get(i).getElements_result() != null)
                elements[TABLE_RESULT] = results.get(i).getElements_result().split(",");
            if(results.get(i).getElements_movie() != null)
                elements[TABLE_MOVIE] = results.get(i).getElements_movie().split(",");
            if(results.get(i).getElements_zukan() != null)
                elements[TABLE_ZUKAN] = results.get(i).getElements_zukan().split(",");
            if(results.get(i).getElements_quiz() != null)
                elements[TABLE_QUIZ] = results.get(i).getElements_quiz().split(",");
            if(results.get(i).getElements_quiz_correct() != null)
                elements[TABLE_QUIZ_CORRECT] = results.get(i).getElements_quiz_correct().split(",");
            if(results.get(i).getElements_new() != null)
                elements[TABLE_NEW] = results.get(i).getElements_new().split(",");

            //TABLE_RESULT TABLE_MOVIE TABLE_ZUKAN TABLE_QUIZ TABLE_QUIZ_CORRECT TABLE_NEW の6回
            for (int j = 0; j < elements.length; j++) {
                if(elementFlag) {
                    //要素が null 0 空文字　以外の時
                    if (elements[j] != null && elements[j].length != 0 && !elements[j][0].equals("")) {
                        //false があれば
                        if(!isRecordsIn(context, j, elements[j])){
                            elementFlag = false;
                        }
                    }
                }
            }

            //全ての要素が true だった場合、flag を true にする
            if(elementFlag){
                Toast.makeText(context, results.get(i).getName()+" "+results.get(i).getTitle(), Toast.LENGTH_LONG).show();
                if(check)
                   ResultsDatabase.setResultsTrue(context, results.get(i).getId());
                else
                   ResultsDatabase.setResultsTrue(context, results.get(i).getId());

            }
        }
    }

}
