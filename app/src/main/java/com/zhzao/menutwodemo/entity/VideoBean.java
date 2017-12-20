package com.zhzao.menutwodemo.entity;

import java.util.List;

/**
 * Created by 张乔君 on 2017/12/2.
 */

public class VideoBean {


    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":2,"comments":[{"cid":152,"content":"hi","createTime":"2017-12-19T11:30:12","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":228},{"cid":153,"content":"%E5%B0%8F%E5%A7%90%E5%A7%90","createTime":"2017-12-19T11:30:46","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":228}],"cover":"https://www.zhaoapi.cn/images/quarter/151365404914420171219112654.jpg","createTime":"2017-12-19T11:27:29","favoriteNum":0,"latitude":"2535","localUri":null,"longitude":"5865","playNum":1,"praiseNum":0,"uid":2997,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512108751006e.png","nickname":"李薇","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513654049144PictureSelector_20171218_205908.mp4","wid":228,"workDesc":"1234"},{"commentNum":1,"comments":[{"cid":155,"content":"hi","createTime":"2017-12-19T11:33:15","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":227}],"cover":"https://www.zhaoapi.cn/images/quarter/15136539240981500855350229.jpeg","createTime":"2017-12-19T11:25:24","favoriteNum":0,"latitude":"40.0404678583578","localUri":null,"longitude":"116.30005933357513","playNum":5,"praiseNum":0,"uid":148,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/15136540738631500855344482.jpeg","nickname":"李灿灿","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513653924098PictureSelector_20171206_115353.mp4","wid":227,"workDesc":"retrofit"},{"commentNum":4,"comments":[{"cid":148,"content":"hi","createTime":"2017-12-19T11:20:01","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":226},{"cid":149,"content":"nihao ","createTime":"2017-12-19T11:20:24","jid":null,"mvp":null,"nickname":"muzi","praiseNum":0,"uid":188,"wid":226},{"cid":150,"content":"nihao ","createTime":"2017-12-19T11:20:29","jid":null,"mvp":null,"nickname":"muzi","praiseNum":0,"uid":188,"wid":226},{"cid":154,"content":"lcc","createTime":"2017-12-19T11:32:08","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":226}],"cover":"https://www.zhaoapi.cn/images/quarter/151365357411620171219111915.jpg","createTime":"2017-12-19T11:19:34","favoriteNum":0,"latitude":"100","localUri":null,"longitude":"100","playNum":4,"praiseNum":0,"uid":195,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513653574116video_20171219_111845.mp4","wid":226,"workDesc":""},{"commentNum":8,"comments":[{"cid":108,"content":"888888","createTime":"2017-12-18T16:11:50","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":225},{"cid":110,"content":"你好啊","createTime":"2017-12-18T16:14:05","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":225},{"cid":114,"content":"Mo","createTime":"2017-12-18T16:29:34","jid":null,"mvp":null,"nickname":"Mo","praiseNum":0,"uid":98,"wid":225},{"cid":117,"content":"%E4%B8%89%E6%A5%BC%E5%A4%A7%E4%BD%AC","createTime":"2017-12-18T16:47:46","jid":null,"mvp":null,"nickname":"笑出腹肌的男人","praiseNum":0,"uid":154,"wid":225},{"cid":118,"content":"123","createTime":"2017-12-18T16:50:25","jid":null,"mvp":null,"nickname":"笑出腹肌的男人","praiseNum":0,"uid":154,"wid":225},{"cid":121,"content":"Mo","createTime":"2017-12-18T19:34:32","jid":null,"mvp":null,"nickname":"Mo","praiseNum":0,"uid":98,"wid":225},{"cid":145,"content":"666","createTime":"2017-12-19T11:04:25","jid":null,"mvp":null,"nickname":"祝又忠","praiseNum":0,"uid":99,"wid":225},{"cid":146,"content":"666","createTime":"2017-12-19T11:13:01","jid":null,"mvp":null,"nickname":"祝又忠","praiseNum":0,"uid":99,"wid":225}],"cover":"https://www.zhaoapi.cn/images/quarter/151358372249720170919_202309_1506213685322.jpg","createTime":"2017-12-18T15:55:22","favoriteNum":7,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":85,"praiseNum":4,"uid":150,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1513246264287cropped_1513246262263.jpg","nickname":"小狼","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/151358372249720170919_194626.mp4","wid":225,"workDesc":"瞎搞"},{"commentNum":8,"comments":[{"cid":78,"content":"%E5%93%88%E5%93%88%E5%93%88","createTime":"2017-12-18T09:22:02","jid":null,"mvp":null,"nickname":"14","praiseNum":0,"uid":3028,"wid":224},{"cid":82,"content":"bbb","createTime":"2017-12-18T13:53:29","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":224},{"cid":87,"content":"try","createTime":"2017-12-18T13:56:57","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":224},{"cid":88,"content":"iii","createTime":"2017-12-18T13:58:45","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":224},{"cid":102,"content":"%E4%BD%A0%E5%A5%BD","createTime":"2017-12-18T15:36:53","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":224},{"cid":107,"content":"123","createTime":"2017-12-18T15:58:14","jid":null,"mvp":null,"nickname":"aa","praiseNum":0,"uid":2750,"wid":224},{"cid":109,"content":"123456","createTime":"2017-12-18T16:12:19","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":224},{"cid":144,"content":"aaa","createTime":"2017-12-18T23:21:17","jid":null,"mvp":null,"nickname":"解杰\n","praiseNum":0,"uid":88,"wid":224}],"cover":"https://www.zhaoapi.cn/images/quarter/151351005887120171217192730.jpg","createTime":"2017-12-17T19:27:38","favoriteNum":11,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":null,"praiseNum":4,"uid":2956,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1513510357310image.jpg","nickname":"soul","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513510058871cmhvideo.mp4","wid":224,"workDesc":"这是视频是视频"},{"commentNum":8,"comments":[{"cid":89,"content":"测试1","createTime":"2017-12-18T13:59:26","jid":null,"mvp":null,"nickname":"123","praiseNum":0,"uid":2719,"wid":223},{"cid":97,"content":"mdzz","createTime":"2017-12-18T15:04:52","jid":null,"mvp":null,"nickname":"_zhang","praiseNum":0,"uid":551,"wid":223},{"cid":100,"content":"陈","createTime":"2017-12-18T15:33:52","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":223},{"cid":104,"content":"%E4%BD%A0%E7%9F%A5%E9%81%93","createTime":"2017-12-18T15:38:56","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":223},{"cid":105,"content":"l'k'l","createTime":"2017-12-18T15:40:49","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":223},{"cid":111,"content":"欢迎","createTime":"2017-12-18T16:14:51","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":223},{"cid":125,"content":"\u2026","createTime":"2017-12-18T20:51:02","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":223},{"cid":130,"content":"。。。","createTime":"2017-12-18T21:09:37","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":223}],"cover":"https://www.zhaoapi.cn/images/quarter/151338850155520171216094126.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":7,"latitude":"null","localUri":null,"longitude":"null","playNum":22,"praiseNum":4,"uid":170,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/15131486893501.jpg","nickname":"beautiful","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513388501555VID_20171216_094056.3gp","wid":223,"workDesc":"视频"},{"commentNum":5,"comments":[{"cid":57,"content":"bbj","createTime":"2017-12-16T09:48:56","jid":null,"mvp":null,"nickname":"muzi","praiseNum":0,"uid":188,"wid":222},{"cid":65,"content":"lcc","createTime":"2017-12-16T11:53:05","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":222},{"cid":101,"content":"哇哇哇","createTime":"2017-12-18T15:34:32","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":222},{"cid":112,"content":"好好？","createTime":"2017-12-18T16:17:34","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":222},{"cid":147,"content":"666","createTime":"2017-12-19T11:15:12","jid":null,"mvp":null,"nickname":"祝又忠","praiseNum":0,"uid":99,"wid":222}],"cover":"https://www.zhaoapi.cn/images/quarter/15133878221070ffff64b399bee9d0413aee42c886f7e.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":5,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":11,"praiseNum":5,"uid":150,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1513246264287cropped_1513246262263.jpg","nickname":"小狼","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/15133878221071512894754287.mp4","wid":222,"workDesc":"卡丁车"},{"commentNum":6,"comments":[{"cid":59,"content":"%E4%BD%A0%E5%A5%BD","createTime":"2017-12-16T11:16:00","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":221},{"cid":70,"content":"yy","createTime":"2017-12-16T11:56:56","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":221},{"cid":96,"content":"123","createTime":"2017-12-18T14:51:02","jid":null,"mvp":null,"nickname":"123","praiseNum":0,"uid":2719,"wid":221},{"cid":98,"content":"狗","createTime":"2017-12-18T15:26:52","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":221},{"cid":113,"content":"好好？","createTime":"2017-12-18T16:29:19","jid":null,"mvp":null,"nickname":"笑出腹肌的男人","praiseNum":0,"uid":154,"wid":221},{"cid":124,"content":"额","createTime":"2017-12-18T20:43:20","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":221}],"cover":"https://www.zhaoapi.cn/images/quarter/1513334984104cover.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":3,"latitude":"","localUri":null,"longitude":"","playNum":63,"praiseNum":3,"uid":154,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/154.jpg","nickname":"笑出腹肌的男人","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/15133349841041513246461819.mp4","wid":221,"workDesc":"  "},{"commentNum":3,"comments":[{"cid":66,"content":"aa","createTime":"2017-12-16T11:53:51","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":220},{"cid":122,"content":"额","createTime":"2017-12-18T20:38:43","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":220},{"cid":136,"content":"。。。","createTime":"2017-12-18T21:21:36","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":220}],"cover":"https://www.zhaoapi.cn/images/quarter/151332147980820171215150428.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":1,"latitude":"40.040443","localUri":null,"longitude":"116.300159","playNum":24,"praiseNum":4,"uid":188,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"muzi","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513321479808video_20171215_150419.mp4","wid":220,"workDesc":""},{"commentNum":3,"comments":[{"cid":99,"content":"完整","createTime":"2017-12-18T15:29:44","jid":null,"mvp":null,"nickname":"linnnn","praiseNum":0,"uid":114,"wid":219},{"cid":115,"content":"Mo","createTime":"2017-12-18T16:29:56","jid":null,"mvp":null,"nickname":"Mo","praiseNum":0,"uid":98,"wid":219},{"cid":135,"content":"。。","createTime":"2017-12-18T21:18:13","jid":null,"mvp":null,"nickname":"小五","praiseNum":0,"uid":313,"wid":219}],"cover":"https://www.zhaoapi.cn/images/quarter/1513320695418avator_thump.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":5,"latitude":"40.040438843506415","localUri":null,"longitude":"116.30001389784546","playNum":19,"praiseNum":3,"uid":148,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/15136540738631500855344482.jpeg","nickname":"李灿灿","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513320695418PictureSelector_20171206_115353.mp4","wid":219,"workDesc":"cc"}]
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
         * commentNum : 2
         * comments : [{"cid":152,"content":"hi","createTime":"2017-12-19T11:30:12","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":228},{"cid":153,"content":"%E5%B0%8F%E5%A7%90%E5%A7%90","createTime":"2017-12-19T11:30:46","jid":null,"mvp":null,"nickname":"李灿灿","praiseNum":0,"uid":148,"wid":228}]
         * cover : https://www.zhaoapi.cn/images/quarter/151365404914420171219112654.jpg
         * createTime : 2017-12-19T11:27:29
         * favoriteNum : 0
         * latitude : 2535
         * localUri : null
         * longitude : 5865
         * playNum : 1
         * praiseNum : 0
         * uid : 2997
         * user : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512108751006e.png","nickname":"李薇","praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1513654049144PictureSelector_20171218_205908.mp4
         * wid : 228
         * workDesc : 1234
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
        private UserBean user;
        private String videoUrl;
        private int wid;
        private String workDesc;
        private List<CommentsBean> comments;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
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

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : false
             * icon : https://www.zhaoapi.cn/images/1512108751006e.png
             * nickname : 李薇
             * praiseNum : null
             */

            private Object age;
            private String fans;
            private boolean follow;
            private String icon;
            private String nickname;
            private String praiseNum;

            public Object getAge() {
                return age;
            }

            public void setAge(Object age) {
                this.age = age;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public boolean isFollow() {
                return follow;
            }

            public void setFollow(boolean follow) {
                this.follow = follow;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPraiseNum() {
                return praiseNum;
            }

            public void setPraiseNum(String praiseNum) {
                this.praiseNum = praiseNum;
            }
        }

        public static class CommentsBean {
            /**
             * cid : 152
             * content : hi
             * createTime : 2017-12-19T11:30:12
             * jid : null
             * mvp : null
             * nickname : beautiful
             * praiseNum : 0
             * uid : 170
             * wid : 228
             */

            private int cid;
            private String content;
            private String createTime;
            private Object jid;
            private Object mvp;
            private String nickname;
            private int praiseNum;
            private int uid;
            private int wid;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getJid() {
                return jid;
            }

            public void setJid(Object jid) {
                this.jid = jid;
            }

            public Object getMvp() {
                return mvp;
            }

            public void setMvp(Object mvp) {
                this.mvp = mvp;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
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

            public int getWid() {
                return wid;
            }

            public void setWid(int wid) {
                this.wid = wid;
            }
        }
    }
}
