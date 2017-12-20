package com.zhzao.menutwodemo.entity;

import java.util.List;

/**
 * Created by 张乔君 on 2017/12/18.
 * 个人的作品集
 */

public class UserLie {

    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/1512530949271y.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"4545454","localUri":null,"longitude":"4545","playNum":0,"praiseNum":0,"uid":142,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512530949271PictureSelector_20171205_150755.mp4","wid":65,"workDesc":"管饭管饭管饭"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/1512545259631y.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"4545454","localUri":null,"longitude":"4545","playNum":0,"praiseNum":0,"uid":142,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512545259631PictureSelector_20171205_150755.mp4","wid":83,"workDesc":"叮叮当"}]
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
         * commentNum : 0
         * cover : https://www.zhaoapi.cn/images/quarter/1512530949271y.jpg
         * createTime : 2017-12-17T19:20:44
         * favoriteNum : 0
         * latitude : 4545454
         * localUri : null
         * longitude : 4545
         * playNum : 0
         * praiseNum : 0
         * uid : 142
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512530949271PictureSelector_20171205_150755.mp4
         * wid : 65
         * workDesc : 管饭管饭管饭
         */

        private int commentNum;
        private String cover;
        private String createTime;
        private int favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private int playNum;
        private int praiseNum;
        private int uid;
        private String videoUrl;
        private int wid;
        private String workDesc;
        private boolean isShow=false;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getLocalUri() {
            return localUri;
        }

        public void setLocalUri(Object localUri) {
            this.localUri = localUri;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getPlayNum() {
            return playNum;
        }

        public void setPlayNum(int playNum) {
            this.playNum = playNum;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getWid() {
            return wid;
        }

        public void setWid(int wid) {
            this.wid = wid;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }
    }
}
