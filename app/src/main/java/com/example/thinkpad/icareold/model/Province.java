package com.example.thinkpad.icareold.model;

/**
 * Created by Lijianhao on 2015/11/19.
 */
public class Province {
    private   int id;
    private String provinceName;
    private String provinceCode;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName(String ProvinceName){
        this.provinceName=provinceName;
    }
    public String getProvinceCode(){
        return provinceCode;
    }
    public void setProvinceCode(String provinceCode){
        this.provinceCode=provinceCode;
    }

}
