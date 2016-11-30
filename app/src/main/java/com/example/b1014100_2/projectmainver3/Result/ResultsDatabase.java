package com.example.b1014100_2.projectmainver3.Result;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

//データベースstatic変数をインポート

/*
 * Created by b1014159
 */
public class ResultsDatabase {
    private static final String DB_NAME = "results.db";
    private static final int DB_VERSION = 9;

    static final int RESULT = 0;
    static final int MOVIE = 1;
    static final int ZUKAN = 2;
    static final int QUIZ = 3;

    private static String ORDER_BY = "_id" + " ASC";//並べる順


    //------------------- Results -------------------//
    public static ArrayList<Result> getResultsAll(Context context) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "type", "elements_result", "elements_movie", "elements_zukan", "elements_quiz"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }


    public static ArrayList<ResultData> getResultDatasAll(Context context) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "flag", "type", "star_level", "title", "name"};

        ArrayList<ResultData> resultDatas = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            ResultData resultData = new ResultData(c.getInt(0), c.getInt(1), c.getInt(2), c.getInt(3), c.getString(4), c.getString(5));
            resultDatas.add(resultData);
        }
        c.close();
        db.close();

        return resultDatas;
    }

    public static ArrayList<Result> getResultsFalse(Context context) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "type", "elements_result", "elements_movie", "elements_zukan", "elements_quiz"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "flag = ?", new String[]{"0"}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }

    public static ArrayList<Result> getResultsFlagOfNew(Context context) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "elements_result", "elements_movie", "elements_zukan", "elements_quiz"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "flag_of_new = ?", new String[]{"1"}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }

    public static ArrayList<Result> getResultsType(Context context, int type) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "elements_result", "elements_movie", "elements_zukan", "elements_quiz"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "type = ?", new String[]{String.valueOf(type)}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }

    public static boolean isResultsFlagOfNew(Context context) {

        String TABLE_NAME = "results";
        String[] FROM = {"flag_of_new"};

        boolean flag = false;

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "flag_of_new = ?", new String[]{"1"}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            if(c.getInt(0) == 1){
                flag = true;
                break;
            }
        }
        c.close();
        db.close();

        return flag;
    }

    public static void setResultsTrue(Context context, int id) {
        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String tableName = "results";
        ContentValues values = new ContentValues();
        values.put("flag", 1);
        values.put("flag_of_new", 1);

        String whereClause = "_id = ?";
        String whereArgs[] = new String[]{String.valueOf(id)};

        try {
            db.update(tableName, values, whereClause, whereArgs);
        } finally {
            db.close();
        }

    }

    public static void resetResultsFlagOfNew(Context context) {
        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String tableName = "results";
        ContentValues values = new ContentValues();
        values.put("flag_of_new", 0);

        String whereClause = "flag_of_new = ?";
        String whereArgs[] = new String[]{"1"};

        try {
            db.update(tableName, values, whereClause, whereArgs);
        } finally {
            db.close();
        }

    }


    //------------------- Records -------------------//
    public static ArrayList<RecordsZukan> getRecordsZukanAll(Context context) {
        String TABLE_NAME = "records_zukan";
        String[] FROM = {"_id", "flag"};

        ArrayList<RecordsZukan> recordsZukens = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            RecordsZukan recordsZukan = new RecordsZukan(c.getInt(0), c.getString(1));
            recordsZukens.add(recordsZukan);
        }
        c.close();
        db.close();

        return recordsZukens;
    }

    public static ArrayList<RecordsZukan> getRecordsZukanIn(Context context, String[] ids) {
        String TABLE_NAME = "records_zukan";
        String[] FROM = {"_id", "flag"};

        ArrayList<RecordsZukan> recordsZukens = new ArrayList<>();

        if(ids != null && ids.length != 0) {

            String where = "_id in (";
            String sep = "";
            for (int i = 0; i < ids.length; i++) {
                where += sep + "?";
                sep = ", ";
            }
            where += ")";

            SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor c = db.query(TABLE_NAME, FROM, where, ids, null, null, ORDER_BY);//queryの実行

            while (c.moveToNext()) {
                RecordsZukan recordsZukan = new RecordsZukan(c.getInt(0), c.getString(1));
                recordsZukens.add(recordsZukan);
            }
            c.close();
            db.close();

        }
        return recordsZukens;
    }

    public static boolean isRecordsIn(Context context, int tableName, String[] ids) {

        if(tableName != RESULT && tableName != MOVIE && tableName != ZUKAN && tableName != QUIZ){
            Log.d("ResultsDatabase", "isRecordsIn: " + "error tabelName");
            return false;
        }

        String TABLE_NAME = "";
        String[] FROM = {"flag"};

        boolean flag = true;

        switch (tableName){
            case RESULT:
                TABLE_NAME = "results";
                break;
            case MOVIE:
                TABLE_NAME = "records_movie";
                break;
            case ZUKAN:
                TABLE_NAME = "records_zukan";
                break;
            case QUIZ:
                TABLE_NAME = "records_quiz";
                break;
        }

        //検索要素 ids があれば
        if(ids != null && ids.length != 0) {

            String where = "_id in (";
            String sep = "";
            for (String ignored : ids) {
                where += sep + "?";
                sep = ", ";
            }
            where += ")";

            SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor c = db.query(TABLE_NAME, FROM, where, ids, null, null, ORDER_BY);//queryの実行

            while (c.moveToNext()) {
                int intFlag = c.getInt(0);
                if(intFlag == 0){
                    flag = false;
                    break;
                }
            }
            c.close();
            db.close();

        }
        return flag;
    }

    public static void setRecordsTrue(Context context, int tableName, int id) {

        if(tableName != MOVIE && tableName != ZUKAN && tableName != QUIZ){
            Log.d("ResultsDatabase", "setRecordsTrue: " + "error tabelName");
            return;
        }

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String TABLE_NAME = "";
        switch (tableName){
            case MOVIE:
                TABLE_NAME = "records_movie";
                break;
            case ZUKAN:
                TABLE_NAME = "records_zukan";
                break;
            case QUIZ:
                TABLE_NAME = "records_quiz";
                break;
        }
        ContentValues values = new ContentValues();
        values.put("flag", 1);

        String whereClause = "_id = ?";
        String whereArgs[] = new String[]{String.valueOf(id)};

        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();

    }


}
