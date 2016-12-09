package com.example.b1014100_2.projectmainver3.Result;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
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

    static final int TABLE_RESULT = 0;
    static final int TABLE_MOVIE = 1;
    static final int TABLE_ZUKAN = 2;
    static final int TABLE_QUIZ = 3;
    static final int TABLE_QUIZ_CORRECT = 4;
    static final int TABLE_NEW = 5;

    static final int RECORDS_NEW_MOVIE = 1;
    static final int RECORDS_NEW_MOVIE_NORMAL = 2;
    static final int RECORDS_NEW_MOVIE_SPHERE = 3;
    static final int RECORDS_NEW_ZUKAN = 4;
    static final int RECORDS_NEW_QUIZ_CORRECT = 5;


    private static String ORDER_BY = "_id" + " ASC";//並べる順


    //------------------- Results -------------------//
    public static ArrayList<Result> getResultsAll(Context context) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "type", "elements_result", "elements_movie", "elements_zukan", "elements_quiz", "elements_quiz_correct", "elements_new"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12));
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
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "type", "elements_result", "elements_movie", "elements_zukan", "elements_quiz", "elements_quiz_correct", "elements_new"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "flag = ?", new String[]{"0"}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }

    public static ArrayList<Result> getResultsFlagOfNew(Context context) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "elements_result", "elements_movie", "elements_zukan", "elements_quiz", "elements_quiz_correct", "elements_new"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "flag_of_new = ?", new String[]{"1"}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }

    public static ArrayList<Result> getResultsType(Context context, int type) {
        String TABLE_NAME = "results";
        String[] FROM = {"_id", "name", "title", "star_level", "flag", "flag_of_new", "elements_result", "elements_movie", "elements_zukan", "elements_quiz", "elements_quiz_correct", "elements_new"};

        ArrayList<Result> results = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "type = ?", new String[]{String.valueOf(type)}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Result result = new Result(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8), c.getString(9), c.getString(10), c.getString(11), c.getString(12));
            results.add(result);
        }
        c.close();
        db.close();

        return results;
    }

    public static double getRateResultsTrue(Context context) {
        String TABLE_NAME = "results";

        int sum = 0;
        double rate = 0.0;

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        // db=SQLiteDatabase User.TABLE_NAME=users
        long recodeCount = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        long correctCount = DatabaseUtils.queryNumEntries(db, TABLE_NAME,"flag = ?",new String[]{ "1" });

        db.close();

        rate = (double) correctCount / (double) recodeCount;

        return rate;
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

    public static void setResultsTrueAll(Context context) {
        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String tableName = "results";
        ContentValues values = new ContentValues();
        values.put("flag", 1);
        values.put("flag_of_new", 1);

        try {
            db.update(tableName, values, null, null);
        } finally {
            db.close();
        }

    }

    public static void setResultsFalse(Context context, int id) {
        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String tableName = "results";
        ContentValues values = new ContentValues();
        values.put("flag", 0);
        values.put("flag_of_new", 0);

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

    public static ArrayList<RecordsQuizCorrect> getRecordsQuizCorrectFalse(Context context) {
        String TABLE_NAME = "records_quiz_correct";
        String[] FROM = {"_id", "correct", "flag"};

        ArrayList<RecordsQuizCorrect> recordsQuizCorrects = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "flag = ?", new String[]{"0"}, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            RecordsQuizCorrect recordsQuizCorrect = new RecordsQuizCorrect(c.getInt(0), c.getInt(1), c.getInt(2));
            recordsQuizCorrects.add(recordsQuizCorrect);
        }
        c.close();
        db.close();

        return recordsQuizCorrects;
    }

    public static int getSumRecordsQuizSumRight(Context context) {
        String TABLE_NAME = "records_quiz";
        String COLUMN_POINT = "sum_right";

        int sum=0;
        ArrayList<RecordsQuizCorrect> recordsQuizCorrects = new ArrayList<>();

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        //http://u64178.blogspot.com/2014/07/android-sqlite-sum-total.html
        Cursor c = db.rawQuery(String.format("SELECT SUM(%s) FROM %s", COLUMN_POINT, TABLE_NAME), null);

        if(c.moveToNext()){
            sum = c.getInt(0);
        }

        c.close();
        db.close();

        return sum;
    }

    public static int getRecordsQuizSumRight(Context context, int id) {
        String TABLE_NAME = "records_quiz";
        String[] FROM = {"sum_right"};

        int sumRight = 0;

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "_id = ?", new String[]{String.valueOf(id)}, null, null, ORDER_BY);//queryの実行

        if (c.moveToNext()) {
            sumRight = c.getInt(0);
        }
        c.close();
        db.close();

        return sumRight;
    }

    public static int getRecordsMovieId(Context context, String name) {
        String TABLE_NAME = "records_movie";
        String[] FROM = {"_id"};

        int id = 0;

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "name = ?", new String[]{ name }, null, null, ORDER_BY);//queryの実行

        if (c.moveToNext()) {
            id = c.getInt(0);
        }
        c.close();
        db.close();

        return id;
    }

    public static long getSumRecordsQuizTrue(Context context) {
        String TABLE_NAME = "records_quiz";

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        // db=SQLiteDatabase http://qiita.com/operandoOS/items/eb829a56f28d2c56a436
        long recodeCount = DatabaseUtils.queryNumEntries(db, TABLE_NAME,"flag = ?",new String[]{ "1" });
        db.close();

        return recodeCount;
    }

    public static boolean isRecordsIn(Context context, int tableName, String[] ids) {

        if(tableName != TABLE_RESULT && tableName != TABLE_MOVIE && tableName != TABLE_ZUKAN && tableName != TABLE_QUIZ
                && tableName != TABLE_QUIZ_CORRECT && tableName != TABLE_NEW){
            Log.d("ResultsDatabase", "isRecordsIn: " + "error tableName");
            return false;
        }

        String TABLE_NAME = "";
        String[] FROM = {"flag"};

        boolean flag = true;

        switch (tableName){
            case TABLE_RESULT:
                TABLE_NAME = "results";
                break;
            case TABLE_MOVIE:
                TABLE_NAME = "records_movie";
                break;
            case TABLE_ZUKAN:
                TABLE_NAME = "records_zukan";
                break;
            case TABLE_QUIZ:
                TABLE_NAME = "records_quiz";
                break;
            case TABLE_QUIZ_CORRECT:
                TABLE_NAME = "records_quiz_correct";
                break;
            case TABLE_NEW:
                TABLE_NAME = "records_new";
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

        if(tableName != TABLE_MOVIE && tableName != TABLE_ZUKAN && tableName != TABLE_QUIZ
                && tableName != TABLE_QUIZ_CORRECT && tableName != TABLE_NEW){
            Log.d("ResultsDatabase", "setRecordsTrue: " + "error tableName");
            return;
        }

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String TABLE_NAME = "";
        switch (tableName){
            case TABLE_MOVIE:
                TABLE_NAME = "records_movie";
                break;
            case TABLE_ZUKAN:
                TABLE_NAME = "records_zukan";
                break;
            case TABLE_QUIZ:
                TABLE_NAME = "records_quiz";
                break;
            case TABLE_QUIZ_CORRECT:
                TABLE_NAME = "records_quiz_correct";
                break;
            case TABLE_NEW:
                TABLE_NAME = "records_new";
                break;
        }
        ContentValues values = new ContentValues();
        values.put("flag", 1);

        String whereClause = "_id = ?";
        String whereArgs[] = new String[]{String.valueOf(id)};

        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();

    }

    public static void setRecordsQuizSumRight(Context context, int id, int oldSumRight) {

        SQLiteOpenFromAssets helper = new SQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getWritableDatabase();

        String TABLE_NAME = "records_quiz";
        int newCorrect = oldSumRight + 1;
        Log.d("databese", "setRecordsQuizSumRight: old:" + oldSumRight);

        ContentValues values = new ContentValues();
        values.put("sum_right", newCorrect);

        String whereClause = "_id = ?";
        String whereArgs[] = new String[]{String.valueOf(id)};

        db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();

    }


}
