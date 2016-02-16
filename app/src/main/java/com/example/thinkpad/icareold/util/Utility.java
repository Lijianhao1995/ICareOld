package com.example.thinkpad.icareold.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.example.thinkpad.icareold.model.City;
import com.example.thinkpad.icareold.model.Province;
import com.example.thinkpad.icareold.model.WeatherDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Lijianhao on 2015/12/7.
 */
public class Utility {
    public synchronized static boolean handleProvincesResponse(WeatherDB weatherDB, String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces=response.split(",");
            if(allProvinces!=null && allProvinces.length>0){
                for(String p:allProvinces){
                    String[] array=p.split("\\|");
                    Province province=new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    weatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    public static boolean handleCitiesResponse(WeatherDB weatherDB,String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] allCities=response.split(",");
            if(allCities!=null && allCities.length>0){
                for(String c:allCities){
                    String[] array=c.split("\\|");
                    City city=new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    weatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    public static void handleWeatherResponse(Context context,String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONObject weatherInfo=jsonObject.getJSONObject("data");
            JSONArray weatherForecast=weatherInfo.getJSONArray("forecast");
            String Advice=weatherInfo.getString("ganmao");
            String windDirection=((JSONObject)weatherForecast.get(0)).getString("fengxiang");
            String highTemperature=((JSONObject)weatherForecast.get(0)).getString("high");
            String lowTemperature=((JSONObject)weatherForecast.get(0)).getString("low");
            String weatherType=((JSONObject) weatherForecast.get(0)).getString("type");



            String cityName = weatherInfo.getString("city");
            //String weatherCode=weatherInfo.getString("cityid");
            //String temp1=weatherInfo.getString("temp1");
            //String temp2=weatherInfo.getString("temp2");
            //String weatherDesp=weatherInfo.getString("weather");
            //String publishTime=weatherInfo.getString("ptime");
            saveWeatherInfo(context,cityName,highTemperature,lowTemperature,weatherType,Advice);



        }catch (Exception e){

        }
    }
    public static void saveWeatherInfo(Context context,String cityName,
                                       String temp1,String temp2,String weatherDesp,String Advice){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年M月dr日", Locale.CHINA);
        SharedPreferences.Editor editor= PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("city_selected",true);
        editor.putString("city_name",cityName);
        editor.putString("temp1",temp1);
        editor.putString("temp2",temp2);
        editor.putString("weather_desp", weatherDesp);
        editor.putString("current_date",sdf.format(new Date()));
        editor.putString("advice",Advice);
        editor.commit();
    }
}