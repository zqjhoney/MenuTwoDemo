package com.zhzao.menutwodemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.zhzao.menutwodemo.adapter.FullyGridLayoutManager;
import com.zhzao.menutwodemo.adapter.GridImageAdapter;
import com.zhzao.menutwodemo.presenter.VideoPresenter;
import com.zhzao.menutwodemo.utils.MapLoadUtils;
import com.zhzao.menutwodemo.view.VideoView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Video_EditActivity extends BaseActivity implements VideoView {


    @BindView(R.id.video_recyclerView)
    RecyclerView videoRecyclerView;
    @BindView(R.id.video_cancle)
    TextView videoCancle;
    @BindView(R.id.video_public)
    TextView videoPublic;
    @BindView(R.id.video_bar)
    ProgressBar videoBar;

    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 2;
    private ArrayList<File> files;
    private VideoPresenter presenter;
    private MapLoadUtils map;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_video_edit;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        videoRecyclerView.setLayoutManager(manager);

        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        videoRecyclerView.setAdapter(adapter);


        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(Video_EditActivity.this);
                } else {
                    Toast.makeText(Video_EditActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });


        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(Video_EditActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Video_EditActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Video_EditActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });


    }

    @Override
    public void initdata() {
        files = new ArrayList<>();
        presenter = new VideoPresenter(this);
        videoCancle.setOnClickListener(this);
        videoPublic.setOnClickListener(this);
    }

    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.video_cancle:
                this.finish();
                break;
            case R.id.video_public://发表

                map = new MapLoadUtils();
                 map.getLoad(Video_EditActivity.this);
                double w = map.getW();//纬度
                double j = map.getJ();//精度
                if (selectList != null && selectList.size() > 0) {
                    if (selectList.size() == 1) {
                        File file = new File(selectList.get(0).getPath());
                        presenter.publicVideo(file,w,j);
                    } else {
                        toast("一次衹能上传一个视频");
                    }
                } else {
                    toast("发表的视频不能为空");
                }
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onStop();//定位停止
    }

    @Override
    public void success(String msg) {
          try {
            JSONObject oo=new JSONObject(msg);
              String code = oo.optString("code");
              if("0".equals(code)){//登录成功
                    startActivity(new Intent(this,EditOverActivity.class));
              }else{
                  toast("请检查原因，视频上传错误");
              }
          } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {

            // 进入相册 以下是例子：用不到的api可以不写
            PictureSelector.create(Video_EditActivity.this)
                    .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    // .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewVideo(true)// 是否可预览视频 true or false
                    .enablePreviewAudio(true) // 是否可播放音频 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    // .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                    .enableCrop(false)// 是否裁剪 true or false
                    .compress(false)// 是否压缩 true or false
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                    .openClickSound(false)// 是否开启点击声音 true or false
                    // .cropCompressQuality()// 裁剪压缩质量 默认90 int
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    .videoQuality(0)// 视频录制质量 0 or 1 int
                    .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                    //.recordVideoSecond()//视频秒数录制 默认60s int
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        }

    };


    @Override
    public void failure(String msg) {

    }

    @Override
    public void showLoading() {
        videoBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        videoBar.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        ToastShow.showToast(this, msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    for (LocalMedia localMedia : selectList) {
                        String path = localMedia.getPath();
                        System.out.println("图片地址" + path);
                    }
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }



}
