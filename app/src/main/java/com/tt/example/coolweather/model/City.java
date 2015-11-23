package com.tt.example.coolweather.model;

/**
 * Created by TT on 2015/11/9.
 */
//City表的实体类，方便后续的开发
public class City {
    private int id;
    private String CityName;
    private String CityCode;
    private int provinceId;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getCityName(){
        return CityName;
    }

    public void setCityName(String CityName){
        this.CityName=CityName;
    }

    public String getCityCode(){
        return CityCode;
    }

    public void setCityCode(String cityCode){
        this.CityCode=cityCode;
    }

    public int getProvinceId(){
        return provinceId;
    }

    public void setProvinceId(int provinceId){
        this.provinceId=provinceId;
    }
}
