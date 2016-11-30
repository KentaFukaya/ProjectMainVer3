package com.example.b1014100_2.projectmainver3.Result;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import static com.example.test.resultstest.ResultsDatabase.MOVIE;
import static com.example.test.resultstest.ResultsDatabase.QUIZ;
import static com.example.test.resultstest.ResultsDatabase.RESULT;
import static com.example.test.resultstest.ResultsDatabase.ZUKAN;
import static com.example.test.resultstest.ResultsDatabase.isRecordsIn;

/**
 * Created by on 2016/11/28.
 */

public class ResultManager {

    //RESULT MOVIE ZUKAN QUIZ の flag を true にする
    static void setRecordFlagTrue(Context context, int activityName, int id){
        //activityName が異なるとき
        if(activityName != MOVIE && activityName != ZUKAN && activityName != QUIZ){
            Log.d("ResultManager", "setRecordFlagTrue: " + "error activityName");
            return;
        }

        //flag を true にする
        ResultsDatabase.setRecordsTrue(context, activityName, id);
        titleCheckTrue(context);
    }

    static void titleCheckTrue(Context context){
        ArrayList<Result> results = ResultsDatabase.getResultsFalse(context);
        boolean elementFlag = true;

        //flag が false の result を全て確認
        for (int i = 0; i < results.size(); i++) {
            //必要な要素を , で区切って配列に入れる
            String[][] elements = new String[4][];
            if(results.get(i).getElements_result() != null)
                elements[RESULT] = results.get(i).getElements_result().split(",");
            if(results.get(i).getElements_movie() != null)
                elements[MOVIE] = results.get(i).getElements_movie().split(",");
            if(results.get(i).getElements_zukan() != null)
                elements[ZUKAN] = results.get(i).getElements_zukan().split(",");
            if(results.get(i).getElements_quiz() != null)
                elements[QUIZ] = results.get(i).getElements_quiz().split(",");

            //RESULT MOVIE ZUKAN QUIZ の4回
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
                ResultsDatabase.setResultsTrue(context, results.get(i).getId());
            }
        }
    }

}
