package com.example.b1014100_2.projectmainver3.zukan;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.a.a.a.D;

import java.util.ArrayList;

//データベースstatic変数をインポート
import static com.example.b1014100_2.projectmainver3.zukan.ZukanSQLiteOpenHelper.*;

/*
 * Created by b1014159
 */
public class ZukanDatabase {
    private static SQLiteDatabase mDb;
//    private static String[] FROM = {"_id", "name", "content", "length", "season", "image_name"};
    private static String[] FROM = {"_id", "name", "content", "type", "length", "season", "image_name"};
    private static String ORDER_BY = "_id" + " ASC";//並べる順

    /*
    // データベースに登録する。
    public static void setQuizData(QuizDetail quizDetail) {
        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getWritableDatabase();
        //Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行
        if(!c.moveToPosition(quizDetail.getId())) {

            // ContentValuesにデータを格納
            ContentValues values = new ContentValues();
            // カラム名に値を渡す
            values.put("_id", quizDetail.getId());
            values.put("ImageUrl", quizDetail.getImageUrl());
            values.put("fishId", quizDetail.getFishId());
            values.put("question", quizDetail.getQuestion());
            values.put("choice1", quizDetail.getChoices()[0]);
            values.put("choice2", quizDetail.getChoices()[1]);
            values.put("choice3", quizDetail.getChoices()[2]);
            try {
                // データの挿入
                //mDb.insert(ZukanSQLiteOpenHelper.TABLE_NAME, null, values);
            } finally {
                mDb.close();
            }
        }
        mDb.close();
        c.close();
    }
    */

    /*
    // データベース上のすべてのdataをQuizDetailsで返す。
    public static ArrayList<QuizDetail> getQuizDetailsAll() {
        ArrayList<QuizDetail> quizDetails = new ArrayList<>();

        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getReadableDatabase();
        //Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            QuizDetail quizDetail = new QuizDetail();
            quizDetail.setId(c.getInt(0));
            quizDetail.setImageUrl(c.getInt(1));
            quizDetail.setFishId(c.getInt(2));
            quizDetail.setQuestion(c.getString(3));
            quizDetail.setChoices(new String[]{c.getString(4), c.getString(5), c.getString(6)});
            //選択肢をシャッフルする
            quizDetail.shuffleChoices();
            quizDetails.add(quizDetail);
        }
        c.close();
        mDb.close();

        return quizDetails;
    }
    */

    /*
    // データベース上の指定fishIdのdataをQuizDetailsで返す。
    public static ArrayList<QuizDetail> getQuizDetails(int fishId) {
        ArrayList<QuizDetail> quizDetails = new ArrayList<>();

        ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(QuizActivity.getContext());
        mDb = helper.getReadableDatabase();
        //Cursor c = mDb.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, "fishId = ?", new String[]{ "" + fishId }, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            QuizDetail quizDetail = new QuizDetail();
            quizDetail.setId(c.getInt(0));
            quizDetail.setImageUrl(c.getInt(1));
            quizDetail.setFishId(c.getInt(2));
            quizDetail.setQuestion(c.getString(3));
            quizDetail.setChoices(new String[]{c.getString(4), c.getString(5), c.getString(6)});
            //選択肢をシャッフルする
            quizDetail.shuffleChoices();
            quizDetails.add(quizDetail);
        }
        c.close();
        mDb.close();

        return quizDetails;
    }
    */

    // Fromassets
    public static ArrayList<Zukan> getZukanAll() {
        ArrayList<Zukan> zukans = new ArrayList<>();

        //ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanActivity.getContext());
        //mDb = helper.getReadableDatabase();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(ZukanActivity.getContext(), DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();
            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setType(c.getString(3));
            zukan.setLength(c.getInt(4));
            zukan.setSeason(c.getString(5));
            zukan.setImageName(c.getString(6));
            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }

    // Fromassets
    public static ArrayList<Zukan> getZukanSeason(String season) {
        ArrayList<Zukan> zukans = new ArrayList<>();

        //ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanActivity.getContext());
        //mDb = helper.getReadableDatabase();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(ZukanActivity.getContext(), DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "season = ?", new String[]{ season }, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();
            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setType(c.getString(3));
            zukan.setLength(c.getInt(4));
            zukan.setSeason(c.getString(5));
            zukan.setImageName(c.getString(6));
            Log.d("ZukanDatabase", "getZukanSeason: " + zukan.printall());
            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }


    // Fromassets テスト
    public static ArrayList<Zukan> getZukan(String syllabary, String type, String season) {
        ArrayList<Zukan> zukans = new ArrayList<>();

        //ZukanSQLiteOpenHelper helper = new ZukanSQLiteOpenHelper(ZukanActivity.getContext());
        //mDb = helper.getReadableDatabase();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(ZukanActivity.getContext(), DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(ZukanSQLiteOpenHelper.TABLE_NAME, FROM, getSelection(syllabary, type, season), null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();
            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setType(c.getString(3));
            zukan.setLength(c.getInt(4));
            zukan.setSeason(c.getString(5));
            zukan.setImageName(c.getString(6));
            Log.d("ZukanDatabase", "getZukan: " + zukan.printall());
            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }

    private static String getSelection(String syllabary, String type, String season){
        String sqlSelection = "";
        boolean sqlFrag = false;

        //50音順
        if(syllabary != null){
            sqlSelection = sqlSelection + "syllabary = '" + syllabary + "'";
            sqlFrag = true;
        }

        //種類
        if(type != null){
            if(sqlFrag == true){
                sqlSelection = sqlSelection + " and ";
            }
            sqlSelection = sqlSelection + "type = '" + type + "'";
            sqlFrag = true;
        }

        //季節
        if(season != null){
            if(sqlFrag == true){
                sqlSelection = sqlSelection + " and ";
            }
            sqlSelection = sqlSelection + "season = '" + season + "'";
        }

        return sqlSelection;
    }
}

class createWhere{
    private String sqlSelection;
    private String[] sqlSelectionArgs;

    public String getSqlSelection() {
        return sqlSelection;
    }
    public void setSqlSelection(String sqlSelection) {
        this.sqlSelection = sqlSelection;
    }
    public String[] getSqlSelectionArgs() {
        return sqlSelectionArgs;
    }
    public void setSqlSelectionArgs(String[] sqlSelectionArgs) {
        this.sqlSelectionArgs = sqlSelectionArgs;
    }

    public createWhere(String syllabary, String type, String season) {
        int argsSize = 0;
        if(syllabary != null) argsSize++;
        if(type != null) argsSize++;
        if(season != null) argsSize++;

        sqlSelectionArgs = new String[argsSize];

        ArrayList<String> args = new ArrayList<>();
        boolean sqlFrag = false;

        //50音順
        if(syllabary != null){
            this.sqlSelection = this.sqlSelection + "syllabary = ?";
            args.add(syllabary);
            sqlFrag = true;
        }

        //種類
        if(type != null){
            if(sqlFrag == true){
                this.sqlSelection = this.sqlSelection + " and ";
            }
            this.sqlSelection = this.sqlSelection + "type = ?";
            args.add(type);
            sqlFrag = true;
        }

        //季節
        if(season != null){
            if(sqlFrag == true){
                this.sqlSelection = this.sqlSelection + " and ";
            }
            this.sqlSelection = this.sqlSelection + "season = ?";
            args.add(season);
        }

    }
}