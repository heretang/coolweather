package com.tt.example.coolweather.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.tt.example.coolweather.model.City;
import com.tt.example.coolweather.model.County;
import com.tt.example.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 2015/11/9.
 */
//封装数据库常用的操作
public class CoolWeatherDB  {

    /*
    数据库的名字
     */
    public static final String DB_NAME="cool_weather";

    /*
    数据库的版本
     */
    public static final int VERSION=1;

    private static CoolWeatherDB coolWeatherDB;

    private SQLiteDatabase db;

    /*
    将构造方法私有化
     */
    private CoolWeatherDB(Context context){
       CoolWeatherOpenHelper dbHelper=new CoolWeatherOpenHelper(
       context,DB_NAME,null,VERSION);
        //用DBhelper获得SQlitedb的实例
        db=dbHelper.getWritableDatabase();
    }

    /*
    获取CoolweatherDB的实例
     */
    // synchronized定义了一次只能一个线程访问
    public synchronized static CoolWeatherDB getInstance(Context context){
        if (coolWeatherDB==null){
            coolWeatherDB=new CoolWeatherDB(context);
        }
        return coolWeatherDB;

    }


/****************************************Province*****************************************************/
    /*
    将Province实例储存到数据库
     */
    public void saveProvince(Province province){
        if (province!=null){
            ContentValues values=new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    /*
    从数据库读取全国所有省份的信息
     */
    public List<Province> loadProvinces(){
        List<Province>list=new ArrayList<Province>();
        Cursor cursor=db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province=new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }
/**************************************City********************************************************/
    /*
    将CIty实例存储到数据库
     */
    public void saveCity(City city){
        if (city!=null){
            ContentValues values=new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }
    /*
    从数据库读取某省下的所有城市信息
     */
    public List<City> loadCities(int provinceId){
        List<City>list=new ArrayList<City>();
        Cursor cursor=db.query("City",null,"province_id=?",new String[]
                {String.valueOf(provinceId)},null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city=new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }
 /*****************************************County**************************************************/
    /*
    将COUNTY实例储存到数据库
     */
    public void saveCounty(County county){
        if (county!=null){
            ContentValues values=new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }
    /*
    从数据库读取某城市下所有县的消息
     */
    public List<County> loadCounties(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor=db.query("County",null,"city_id=?",
                new String[]{String.valueOf(cityId) },null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county=new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return list;
    }
    

}









