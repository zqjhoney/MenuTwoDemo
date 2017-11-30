package com.zhzao.menutwodemo.utils;

import com.google.gson.internal.$Gson$Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/11/3.
 */

public abstract class MyCallback<T> {
    Type type;
    static Type getSuperclassTypeParameter(Class<?> subclass){
        Type superclass =subclass.getGenericSuperclass();
        if(superclass instanceof Class){
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterized=(ParameterizedType)superclass;
        //解析
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
    public MyCallback(){
        type=getSuperclassTypeParameter(getClass());
    }
    public abstract void onFailure(Call call, IOException e);
    public abstract void onSuccess(ResponseBody responsebody, Object t);//建议不要用ResponseBody，智能调用一次，否则报错
  //  public abstract void onError(Response response,String errorMsg);
    public abstract void onError(String errorMsg);
}
