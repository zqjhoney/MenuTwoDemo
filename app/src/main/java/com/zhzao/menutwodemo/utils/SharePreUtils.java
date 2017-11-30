package com.zhzao.menutwodemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.zhzao.menutwodemo.common.App;
import com.zzhao.utils.Base.BaseApp;


/**
 * Created by 张乔君 on 2017/9/28.
 */

public class SharePreUtils {
    private final static String NAME="comm_name";

    public static SharedPreferences getPreferences(){
        return  App.mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    /**
     * 添加
     * @param key
     * @param value
     */
    public static void putShareprefer(String key,String value){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putString(key,value).commit();
    }
    /**
     * 添加数字
     * @param key
     * @param value
     */
    public static void putSharepreferNum(String key,int value){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putInt(key,value).commit();
    }

    public static void putSharepreferBoolean(String key,Boolean b){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.putBoolean(key,b).commit();
    }

    /**
     * 查询
     */
    public static String getShareprefervalue(String key){

        return getPreferences().getString(key,"");

    }
    /**
     * 查询数字
     */
    public static int getSharepreferNumvalue(String key){

        return getPreferences().getInt(key,0);

    }
    public static boolean getSharepreferBooleanvalue(String key){

        return getPreferences().getBoolean(key,false);

    }

    /**
     * 清除
     */

    public static void removeShareprefer(String key){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.remove(key).commit();
    }
}
