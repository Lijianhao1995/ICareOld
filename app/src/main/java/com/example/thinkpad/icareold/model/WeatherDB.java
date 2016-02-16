package com.example.thinkpad.icareold.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thinkpad.icareold.db.WeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lijianhao on 2015/11/19.
 */
public class WeatherDB {
    public static final String DB_NAME="weather";//name of the database
    public static final int VERSION=1;//version of the database
    private static WeatherDB weatherDB;
    private SQLiteDatabase db;
    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper=new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db=dbHelper.getWritableDatabase();
    }


    //获取WeatherDB的实例
    public synchronized/*同时只能有一个线程运行这个方法*/ static WeatherDB getInstance(Context context){
        if(weatherDB==null){
            weatherDB=new WeatherDB(context);
        }
        return weatherDB;
    }
    public void saveProvince(Province province){
        if(province!=null){
            ContentValues values=new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    public List<Province> loadProvince(){
        List<Province> list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);//获得一个游标
        if(cursor.moveToFirst())/*判断游标是否为空*/{
            do{
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while(cursor.moveToNext());

        }
        return list;
    }
    public void saveCity(City city){
        if(city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("provice_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }
    public List<City> loadCities(int provinceId){
        List<City> list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",new String[]{String.valueOf(provinceId)},null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }




}
