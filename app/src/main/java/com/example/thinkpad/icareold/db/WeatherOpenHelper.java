package com.example.thinkpad.icareold.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lijianhao on 2015/11/19.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_PROVINCE="create table Province" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text)";
    //province建表语句
    public static final String CREATE_CITY="create table City" +
            "id integer primary key autoincrement," +
            "city_name text," +
            "city_code text,"+
            "province_id integer)";
    //city建表语句

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

