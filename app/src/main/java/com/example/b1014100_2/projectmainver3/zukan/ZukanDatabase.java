package com.example.b1014100_2.projectmainver3.zukan;

import android.content.ContentValues;
import android.content.Context;
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
    private static String[] FROM = {"_id", "name", "content", "length", "syllabary", "type", "season_spring", "season_summer", "season_fall", "season_winter", "image_name"};
    private static String ORDER_BY = "_id" + " ASC";//並べる順

    private Context context;

    public final static int SEASON_SPRING = 0;
    public final static int SEASON_SUMMER = 1;
    public final static int SEASON_FALL = 2;
    public final static int SEASON_WINTER = 3;

    public ZukanDatabase(Context context) {
            this.context = context;
    }

    // Fromassets
    public ArrayList<Zukan> getZukanAll() {
        ArrayList<Zukan> zukans = new ArrayList<>();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();

            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setLength(c.getInt(3));
            zukan.setSyllabary(c.getString(4));
            zukan.setType(c.getString(5));
            zukan.setSeason_spring(c.getInt(6));
            zukan.setSeason_summer(c.getInt(7));
            zukan.setSeason_fall(c.getInt(8));
            zukan.setSeason_winter(c.getInt(9));
            zukan.setImageName(c.getString(10));

            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }

    //id
    public Zukan getZukanId(int id) {
        Zukan zukan = new Zukan();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(context, DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "_id = ?", new String[]{ String.valueOf(id) }, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setLength(c.getInt(3));
            zukan.setSyllabary(c.getString(4));
            zukan.setType(c.getString(5));
            zukan.setSeason_spring(c.getInt(6));
            zukan.setSeason_summer(c.getInt(7));
            zukan.setSeason_fall(c.getInt(8));
            zukan.setSeason_winter(c.getInt(9));
            zukan.setImageName(c.getString(10));
        }
        c.close();
        db.close();

        return zukan;
    }


    public ArrayList<Zukan> getZukanSeason(int season) {
        ArrayList<Zukan> zukans = new ArrayList<>();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(context, DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        String seasonWhere = "season_spring";

        switch (season) {
            case SEASON_SPRING:
                seasonWhere = "season_spring = ?";
                    break;
            case SEASON_SUMMER:
                seasonWhere = "season_summer = ?";
                break;
            case SEASON_FALL:
                seasonWhere = "season_fall = ?";
                break;
            case SEASON_WINTER:
                seasonWhere = "season_winter = ?";
                break;
            default:
                break;

        }

        Cursor c = db.query(TABLE_NAME, FROM, seasonWhere, new String[]{ "1" }, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();

            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setLength(c.getInt(3));
            zukan.setSyllabary(c.getString(4));
            zukan.setType(c.getString(5));
            zukan.setSeason_spring(c.getInt(6));
            zukan.setSeason_summer(c.getInt(7));
            zukan.setSeason_fall(c.getInt(8));
            zukan.setSeason_winter(c.getInt(9));
            zukan.setImageName(c.getString(10));

            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }

    //syllabary
    public ArrayList<Zukan> getZukanSyllabary(String syllabary) {
        ArrayList<Zukan> zukans = new ArrayList<>();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(context, DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

//        Cursor c = db.query(TABLE_NAME, FROM, "syllabary = ?", new String[]{ syllabary }, null, null, ORDER_BY);//queryの実行
        Cursor c = db.query(true, TABLE_NAME, FROM, "syllabary = ?", new String[]{ syllabary }, null, null, ORDER_BY, null);

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();

            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setLength(c.getInt(3));
            zukan.setSyllabary(c.getString(4));
            zukan.setType(c.getString(5));
            zukan.setSeason_spring(c.getInt(6));
            zukan.setSeason_summer(c.getInt(7));
            zukan.setSeason_fall(c.getInt(8));
            zukan.setSeason_winter(c.getInt(9));
            zukan.setImageName(c.getString(10));

            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }

    //type
    public ArrayList<Zukan> getZukanType(String type) {
        ArrayList<Zukan> zukans = new ArrayList<>();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(context, DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(TABLE_NAME, FROM, "type_romaji = ?", new String[]{ type }, null, null, ORDER_BY);//queryの実行

        while (c.moveToNext()) {
            Zukan zukan = new Zukan();

            zukan.setId(c.getInt(0));
            zukan.setName(c.getString(1));
            zukan.setContent(c.getString(2));
            zukan.setLength(c.getInt(3));
            zukan.setSyllabary(c.getString(4));
            zukan.setType(c.getString(5));
            zukan.setSeason_spring(c.getInt(6));
            zukan.setSeason_summer(c.getInt(7));
            zukan.setSeason_fall(c.getInt(8));
            zukan.setSeason_winter(c.getInt(9));
            zukan.setImageName(c.getString(10));

            zukans.add(zukan);
        }
        c.close();
        db.close();

        return zukans;
    }

    public ArrayList<String> getZukanListSortTypeRomajis() {
        ArrayList<String> types = new ArrayList<>();

        ZukanSQLiteOpenFromAssets helper = new ZukanSQLiteOpenFromAssets(context, DB_NAME, null,DB_VERSION);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c = db.query(true ,TABLE_NAME, new String[]{ "type_romaji" }, null, null, null, null, null, null);//queryの実行

        while (c.moveToNext()) {
            types.add(c.getString(0));
        }
        c.close();
        db.close();

        return types;
    }

    /*
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
    */

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
