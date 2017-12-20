package com.zhzao.menutwodemo.utils;

/**
 * Created by 张乔君 on 2017/12/18.
 */

public class StringUtils {

    private static final String USER_NAME_REGEX = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    private static final String PASSWORD_REGEX = "^[0-9]{3,20}$";


    public static boolean checkUserName(String userName) {
        return userName.matches(USER_NAME_REGEX);
    }

    public static boolean checkPassword(String pwd) {
        return pwd.matches(PASSWORD_REGEX);
    }
}