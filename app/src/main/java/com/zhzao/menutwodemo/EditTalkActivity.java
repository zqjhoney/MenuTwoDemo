package com.zhzao.menutwodemo;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.adapter.FullyGridLayoutManager;
import com.zhzao.menutwodemo.adapter.GridImageAdapter;
import com.zhzao.menutwodemo.presenter.EditPresenter;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.EditView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EditTalkActivity extends BaseActivity implements EditView {
    @BindView(R.id.edit_cancle)
    TextView editCancle;
    @BindView(R.id.edit_public)
    TextView editPublic;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.edit_bar)
    ProgressBar editBar;
    @BindView(R.id.editr_ecycle)
    RecyclerView recyclerView;
    private PopupWindow mPopupWindow;
    private View rootview;
    private WindowManager.LayoutParams params;
    private EditPresenter presenter;
    private Unbinder bind;
    private GridImageAdapter adapter;
    private List<LocalMedia> selectList = new ArrayList<>();
    private int maxSelectNum = 9;
    private ArrayList<File> filelist;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_edit_talk;
    }

    @Override
    public void initview() {
        bind = ButterKnife.bind(this);
        editCancle.setOnClickListener(this);//取消
        editPublic.setOnClickListener(this);//发表
        presenter = new EditPresenter(this);

    }

    @Override
    public void initdata() {
        getpopWindow();//popWindow
        getPhotos();
    }

    private void getpopWindow() {
        View pop = View.inflate(this, R.layout.popwindow_save, null);
        mPopupWindow = new PopupWindow(pop, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(pop);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));//设置背景透明
        mPopupWindow.setOutsideTouchable(true);//设置点击外边取消

        TextView save = pop.findViewById(R.id.save_msg);
        TextView discard = pop.findViewById(R.id.discard_msg);
        save.setOnClickListener(this);
        discard.setOnClickListener(this);
        //显示PopupWindow
        rootview = LayoutInflater.from(this).inflate(R.layout.activity_edit_talk, null);
        params = getWindow().getAttributes();//设置背景半透明
        //当pop结束时，半透明恢复
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });
    }

    private void getPhotos() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        recyclerView.setAdapter(adapter);


        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(EditTalkActivity.this);
                } else {
                    Toast.makeText(EditTalkActivity.this,
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
                            PictureSelector.create(EditTalkActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(EditTalkActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(EditTalkActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }



    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.edit_cancle:
                params.alpha = 0.5f;
                getWindow().setAttributes(params);
                mPopupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
                        backkeys();//伸缩小键盘
                break;//取消
            case R.id.edit_public://发表
                String uid = SharePreUtils.getShareprefervalue("uid");
                if (uid == null || uid.equals("")) {
                    jumpActivity();//重新登录
                }
                String msg = editContent.getText().toString();
                //处理图片
                filelist = new ArrayList<>();
                for (LocalMedia media : selectList) {
                    File file=new File(media.getPath());
                    filelist.add(file);
                }
                presenter.editMsg(msg, uid,filelist);//传递图片
                backkeys();
                break;
            case R.id.save_msg://保存
                ToastShow.showToast(this, "保存");
                mPopupWindow.dismiss();
                break;
            case R.id.discard_msg://不保存
                ToastShow.showToast(this, "不保存");
                mPopupWindow.dismiss();
                break;
        }
    }

    private void backkeys() {//缩回键盘
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);//键盘
        if(imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
        }
    }

    private void jumpActivity() {
        Intent intent = new Intent(this, WelcomePhoneActivity.class);
        intent.putExtra("sign", true);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0) {
            if (mPopupWindow != null && !mPopupWindow.isShowing()) {
                mPopupWindow.showAtLocation(findViewById(R.id.all), Gravity.BOTTOM, 0, 0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void success(String msg) {

        try {
            JSONObject oo = new JSONObject(msg);
            String code = oo.getString("code");
            String m = oo.getString("msg");
            if ("0".equals(code)) {//成功
                startActivity(new Intent(this, EditOverActivity.class));
            } else {//失败重新登录
                toast(m);
                //jumpActivity();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failure(String msg) {
    }

    @Override
    public void showLoading() {
        editBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        editBar.setVisibility(View.GONE);
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
                        System.out.println("图片地址"+path);
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

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            // 进入相册 以下是例子：用不到的api可以不写
            PictureSelector.create(EditTalkActivity.this)
                    .openGallery(1)//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    // .theme(R.style.picture_default_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                    .maxSelectNum(9)// 最大图片选择数量 int
                    .minSelectNum(1)// 最小选择数量 int
                    .imageSpanCount(4)// 每行显示个数 int
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片 true or false
                    .previewVideo(true)// 是否可预览视频 true or false
                    .enablePreviewAudio(true) // 是否可播放音频 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                    .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                    .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    // .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                    .enableCrop(false)// 是否裁剪 true or false
                    .compress(false)// 是否压缩 true or false
                    .glideOverride(300,300)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(3,2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                    .isGif(true)// 是否显示gif图片 true or false
                    // .compressSavePath(getPath())//压缩图片保存地址
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                    .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                    .openClickSound(false)// 是否开启点击声音 true or false
                     .selectionMedia(selectList)// 是否传入已选图片 List<LocalMedia> list
                    .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                    // .cropCompressQuality()// 裁剪压缩质量 默认90 int
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .synOrAsy(true)//同步true或异步false 压缩 默认同步
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                    .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                    .videoQuality(0)// 视频录制质量 0 or 1 int
                    .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
                    .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                    //.recordVideoSecond()//视频秒数录制 默认60s int
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

        }

    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disPatch();
        bind.unbind();
    }
}
