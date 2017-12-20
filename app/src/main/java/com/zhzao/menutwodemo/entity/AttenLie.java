package com.zhzao.menutwodemo.entity;

import java.util.List;

/**
 * Created by 张乔君 on 2017/12/18.
 * 自己关注的用户列表
 */

public class AttenLie {

    /**
     * msg : 获取关注用户列表成功
     * code : 0
     * data : [{"age":null,"appkey":"ca37aa09e5365f3e","appsecret":"0B24FB0C1AE2494A5EB9643D293FBDD5","createtime":"2017-12-17T20:37:29","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1513510357310image.jpg","latitude":null,"longitude":null,"mobile":"13051089902","money":null,"nickname":"soul","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"E3498601C8D3A37869C60836790A63A1","uid":2956,"userId":null,"username":"13051089902"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-17T20:55:44","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/15131486893501.jpg","latitude":null,"longitude":null,"mobile":"15297526557","money":0,"nickname":"beautiful","password":"123456","praiseNum":null,"token":"D9C75CAADF0EE4C106A4C4D9B4DE1B19","uid":170,"userId":null,"username":"15297526557"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-17T20:05:45","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1513246264287cropped_1513246262263.jpg","latitude":null,"longitude":null,"mobile":"15810672623","money":0,"nickname":"小狼","password":"123456","praiseNum":null,"token":"28C3793F9AB8E555D523C46D8D867998","uid":150,"userId":null,"username":"15810672623"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-16T09:05:12","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/154.jpg","latitude":null,"longitude":null,"mobile":"13773359134","money":0,"nickname":"笑出腹肌的男人","password":"654321","praiseNum":null,"token":"B77920CD2D38574F335E2C137F821D61","uid":154,"userId":null,"username":"13773359134"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-17T21:45:24","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/188.jpg","latitude":null,"longitude":null,"mobile":"18201205512","money":0,"nickname":"muzi","password":"123456","praiseNum":null,"token":"1CF78CD99420BCC297A5673C248769BD","uid":188,"userId":null,"username":"18201205512"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : ca37aa09e5365f3e
         * appsecret : 0B24FB0C1AE2494A5EB9643D293FBDD5
         * createtime : 2017-12-17T20:37:29
         * email : null
         * fans : null
         * follow : null
         * gender : null
         * icon : https://www.zhaoapi.cn/images/1513510357310image.jpg
         * latitude : null
         * longitude : null
         * mobile : 13051089902
         * money : null
         * nickname : soul
         * password : 8F669074CAF5513351A2DE5CC22AC04C
         * praiseNum : null
         * token : E3498601C8D3A37869C60836790A63A1
         * uid : 2956
         * userId : null
         * username : 13051089902
         */

        private Object age;
        private String appkey;
        private String appsecret;
        private String createtime;
        private Object email;
        private Object fans;
        private Object follow;
        private Object gender;
        private String icon;
        private Object latitude;
        private Object longitude;
        private String mobile;
        private Object money;
        private String nickname;
        private String password;
        private Object praiseNum;
        private String token;
        private int uid;
        private Object userId;
        private String username;

        public Object getAge() {
            return age;
        }

        public void setAge(Object age) {
            this.age = age;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getFans() {
            return fans;
        }

        public void setFans(Object fans) {
            this.fans = fans;
        }

        public Object getFollow() {
            return follow;
        }

        public void setFollow(Object follow) {
            this.follow = follow;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMoney() {
            return money;
        }

        public void setMoney(Object money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
