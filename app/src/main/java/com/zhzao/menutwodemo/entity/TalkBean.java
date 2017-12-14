package com.zhzao.menutwodemo.entity;

import java.util.List;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class TalkBean {


    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"20:09  2017/11/30","createTime":"2017-11-30T20:09:56","imgUrls":null,"jid":510,"praiseNum":null,"shareNum":null,"uid":73,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/73.jpg","nickname":"蒋顺聪","praiseNum":"null"}},{"commentNum":null,"content":"Y","createTime":"2017-11-30T20:03:20","imgUrls":null,"jid":509,"praiseNum":null,"shareNum":null,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"。。。","createTime":"2017-11-30T20:02:07","imgUrls":"https://www.zhaoapi.cn/images/quarter/15120433270711512039281649.gif|https://www.zhaoapi.cn/images/quarter/15120433270711512038970764.gif","jid":508,"praiseNum":null,"shareNum":null,"uid":188,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"木子","praiseNum":"null"}},{"commentNum":null,"content":"Y","createTime":"2017-11-30T20:00:51","imgUrls":"https://www.zhaoapi.cn/images/quarter/15120432510711511967067836.jpeg","jid":507,"praiseNum":null,"shareNum":null,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"给你","createTime":"2017-11-30T19:56:27","imgUrls":"https://www.zhaoapi.cn/images/quarter/15120429879461512039281649.gif","jid":506,"praiseNum":null,"shareNum":null,"uid":188,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"木子","praiseNum":"null"}},{"commentNum":null,"content":"Y","createTime":"2017-11-30T19:55:58","imgUrls":null,"jid":505,"praiseNum":null,"shareNum":null,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"asdfsdfsdfasdfsdf","createTime":"2017-11-30T19:54:03","imgUrls":"https://www.zhaoapi.cn/images/quarter/1512042843461ic_launcher.png","jid":504,"praiseNum":null,"shareNum":null,"uid":115,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/115.jpg","nickname":"微风～","praiseNum":"null"}},{"commentNum":null,"content":"asdfsdfsdfasdfsdf","createTime":"2017-11-30T19:53:53","imgUrls":null,"jid":503,"praiseNum":null,"shareNum":null,"uid":115,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/115.jpg","nickname":"微风～","praiseNum":"null"}},{"commentNum":null,"content":"给你","createTime":"2017-11-30T19:30:18","imgUrls":"https://www.zhaoapi.cn/images/quarter/15120414186181512028312815.gif","jid":502,"praiseNum":null,"shareNum":null,"uid":188,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/188.jpg","nickname":"木子","praiseNum":"null"}},{"commentNum":null,"content":"老婆特别二，昨天被家里养的狗给咬了，这货居然不生气，还帮狗洗个澡，用电吹风吹干了，我正纳闷呢，难道她要以德报怨，可是高潮来了，她华丽丽的咬了狗一口，狗伴着凄惨的声音逃走了\u2026\u2026","createTime":"2017-11-30T19:23:49","imgUrls":"https://www.zhaoapi.cn/images/quarter/15120410299771500855347173.jpeg","jid":501,"praiseNum":null,"shareNum":null,"uid":148,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/148.jpg","nickname":"李灿灿","praiseNum":"null"}}]
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
         * commentNum : null
         * content : 20:09  2017/11/30
         * createTime : 2017-11-30T20:09:56
         * imgUrls : null
         * jid : 510
         * praiseNum : null
         * shareNum : null
         * uid : 73
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/73.jpg","nickname":"蒋顺聪","praiseNum":"null"}
         */

        private Object commentNum;
        private String content;
        private String createTime;
        private String imgUrls;
        private int jid;
        private Object praiseNum;
        private Object shareNum;
        private int uid;
        private UserBean user;
        private boolean isShow=false;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public Object getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(Object commentNum) {
            this.commentNum = commentNum;
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

        public String getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(String imgUrls) {
            this.imgUrls = imgUrls;
        }

        public int getJid() {
            return jid;
        }

        public void setJid(int jid) {
            this.jid = jid;
        }

        public Object getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(Object praiseNum) {
            this.praiseNum = praiseNum;
        }

        public Object getShareNum() {
            return shareNum;
        }

        public void setShareNum(Object shareNum) {
            this.shareNum = shareNum;
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

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : null
             * icon : https://www.zhaoapi.cn/images/73.jpg
             * nickname : 蒋顺聪
             * praiseNum : null
             */

            private Object age;
            private String fans;
            private String follow;
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

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
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
    }
}
