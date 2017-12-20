package com.zhzao.menutwodemo.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onetime.platform.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.zhzao.menutwodemo.common.Api;
import com.zhzao.menutwodemo.entity.UserLie;
import com.zhzao.menutwodemo.utils.RetorfitFactory;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zzhao.utils.utils.ToastShow;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by 张乔君 on 2017/12/19.
 */

public class Copy1Adapter extends BaseAdapter{
    private ArrayList<UserLie.DataBean> list;
    private Context context;
    private final Map<String, String> map;
    public Copy1Adapter(ArrayList<UserLie.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        map = new HashMap<>();
    }


    /**
     * 弹出AlertDialog 框
     */
    private void jumpPop(final Map<String,String> mp) {

        final EditText et=new EditText(context);
        final AlertDialog.Builder b=new AlertDialog.Builder(context);
        b.setTitle("请输入你的评论");
        b.setView(et);
        b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = et.getText().toString();
                map.put("content",s);
                RetorfitFactory.getInstance().getConsumer(Api.Works_COMMENT, mp, new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject o=new JSONObject(responseBody.string());
                        String msg=o.getString("msg");
                        ToastShow.showToast(context,msg);
                    }
                });
            }
        });
        b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        b.create();
        b.show();

    }

    /**
     * 关注
     * @param follow
     * @param map1
     */
    private void atten(String follow, Map<String, String> map1) {
        RetorfitFactory.getInstance().getConsumer(follow, map1, new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                JSONObject o=new JSONObject(responseBody.string());
                String msg=o.getString("msg");
                ToastShow.showToast(context,msg);
            }
        });
    }

    /**
     * 分享
     * @param position
     */
    private void show(int position) {
        ToastShow.showToast(context,"第"+position+"条点击");
        UMVideo video = new UMVideo(list.get(position).getVideoUrl());
        video.setTitle(list.get(position).getVideoUrl());//视频的标题
        UMImage iv=new UMImage(context,list.get(position).getCover());
        video.setThumb(iv);//视频的缩略图，参数为对象
        video.setDescription(list.get(position).getWorkDesc());//视频的描述
        new ShareAction((Activity) context)
                .withText("hello")
                .withMedia(video)//设传递的类型
                .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener)
                .open();
    }

    /**
     * 分享的工具类
     */
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastShow.showToast(context,"成功了");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastShow.showToast(context,"失败"+t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastShow.showToast(context,"取消了");
        }
    };
    /**
     * 收藏
     * @param api
     * @param map1
     */
    public void  collect(String api,Map<String,String> map1){
        RetorfitFactory.getInstance().getConsumer(api, map1, new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                JSONObject o=new JSONObject(responseBody.string());
                String msg=o.getString("msg");
                ToastShow.showToast(context,msg);
            }
        });
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        map.clear();
        View itemView=View.inflate(context,R.layout.f1_item,null);
        TextView name=itemView.findViewById(R.id.host_name);
        TextView  time=itemView.findViewById(R.id.host_time);
        TextView title=itemView.findViewById(R.id.host_title);
        final ImageView open=itemView.findViewById(R.id.host_open);

        CircleImageView host_ic=itemView.findViewById(R.id.host_ic);
        final RelativeLayout p1=itemView.findViewById(R.id.photo1);
        final RelativeLayout  p2=itemView.findViewById(R.id.photo2);
        final RelativeLayout  p3=itemView.findViewById(R.id.photo3);
        final ImageView  xh=itemView.findViewById(R.id.attention_iv);//关注
        final ImageView  sc=itemView.findViewById(R.id.collect_iv);//收藏
        ImageView  fx=itemView.findViewById(R.id.share_iv);//分享
        ImageView  pl=itemView.findViewById(R.id.review_iv);//评论
        TextView  xh1=itemView.findViewById(R.id.attention_tv);
        TextView  sc1=itemView.findViewById(R.id.collect_tv);
        TextView  fx1=itemView.findViewById(R.id.share_tv);
        TextView  pl1=itemView.findViewById(R.id.review_tv);
        JCVideoPlayer  player=itemView.findViewById(R.id.videocontroller1);

        TextView name1=itemView.findViewById(R.id.comm_name1);
        TextView content1=itemView.findViewById(R.id.comm_content1);
        TextView  more=itemView.findViewById(R.id.check_more);
        String uid = SharePreUtils.getShareprefervalue("uid");
        map.put("uid",uid);
//        holder.name.setText(list.get(position).get);用户名
        time.setText(list.get(position).getCreateTime());
       title.setText(list.get(position).getWorkDesc());
        Glide.with(context).load(list.get(position).getCover()).into(host_ic);
        //点击条目跳转详情
      itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳到详情页

            }
        });

        //视频
       player.setUp(list.get(position).getVideoUrl(),list.get(position).getWorkDesc());
        Glide.with(context).load(list.get(position).getCover()).into(player.ivThumb);
        xh1.setText(list.get(position).getFavoriteNum()+"");//关注num
       fx1.setText(list.get(position).getPraiseNum()+"");//分享num
       sc1.setText(list.get(position).getPlayNum()+"");//收藏num

//        //展示评论
//        if(list.get(position).get){//长度大的时候
//            holder.pl1.setText(list.get(position).getComments().size()+"");//评论num
//            holder.name1.setText(list.get(position).getComments().get(0).getNickname());
//            holder.content1.setText(list.get(position).getComments().get(0).getContent());
////          List<VideoBean.DataBean.CommentsBean>  beans=list.get(position).getComments();
////          CommAdapter ada= new CommAdapter((ArrayList<VideoBean.DataBean.CommentsBean>) beans,context);
//        }
        if(list.get(position).isShow()){
            p1.setVisibility(View.VISIBLE);
            p2.setVisibility(View.VISIBLE);
            p3.setVisibility(View.VISIBLE);
            open.setImageResource(R.drawable.icon_packup);
            ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( p1, "translationX", 0,-95);
            ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(p2, "translationX", 0,-185);
            ObjectAnimator animatorSend = ObjectAnimator.ofFloat( p3, "translationX", 0,-295);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(20);
            animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
            animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
            animatorSet.start();
        }else{
            p1.setVisibility(View.GONE);
          p2.setVisibility(View.GONE);
            p3.setVisibility(View.GONE);
        open.setImageResource(R.drawable.icon_open);
        }
        //TODO 关注
       xh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(xh.isSelected()){
                    ToastShow.showToast(context,"已经关注了");
                }else{
                    map.put("followId",list.get(position).getUid()+"");//添加参数
                    xh.setSelected(true);
                    atten(Api.Follow,map);
                }

            }
        });

//        if(holder.xh.isSelected()){
//            //TODO 点赞
//            ToastShow.showToast(context,"已经点赞了，还不能取消");
//        }else{
//            holder.xh.setSelected(true);
//            map.put("wid",list.get(position).getWid()+"");
//            //todo 接口
//            RetorfitFactory.getInstance().getConsumer(Api.Talk_ZAN, map, new Consumer<ResponseBody>() {
//                @Override
//                public void accept(ResponseBody responseBody) throws Exception {
//                    JSONObject o=new JSONObject(responseBody.string());
//                    String code=o.getString("code");
//                    String msg=o.getString("msg");
//                    ToastShow.showToast(context,msg);
//                }
//            });
//        }

        //TODO 收藏
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("wid",list.get(position).getWid()+"");
                if(sc.isSelected()){
                    //TODO 取消收藏
                    sc.setSelected(false);
                    collect(Api.Works_CANCEL,map);
                }else{
                    //todo 收藏
                   sc.setSelected(true);
                    collect(Api.Works_COLLECT,map);
                }
            }
        });
        //TODO 分享
       fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(position);
            }
        });
        //TODO 评论
       pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("wid",list.get(position).getWid()+"");
                jumpPop(map);

            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!list.get(position).isShow()){
                    list.get(position).setShow(true);
                   p1.setVisibility(View.VISIBLE);
                   p2.setVisibility(View.VISIBLE);
                   p3.setVisibility(View.VISIBLE);
                   open.animate().rotation(360).setDuration(200).start();
                   open.setImageResource(R.drawable.icon_packup);
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat( p1, "translationX", 0,-95);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat( p2, "translationX", 0,-185);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat( p3, "translationX", 0,-295);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();

                }else{
                    list.get(position).setShow(false);
                    open.animate().rotation(0).setDuration(200).start();
                   open.setImageResource(R.drawable.icon_open);
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(p1, "translationX",-95,0);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(p2, "translationX", -185,0);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(p3, "translationX",-295,0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(200);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                           p1.setVisibility(View.GONE);
                          p2.setVisibility(View.GONE);
                            p3.setVisibility(View.GONE);
                        }
                    });

                }

            }
        });

        return itemView;
    }


}
