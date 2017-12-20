package com.zhzao.menutwodemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.entity.UserInfo;
import com.zhzao.menutwodemo.photoutils.ClipImageActivity;
import com.zhzao.menutwodemo.photoutils.FileUtil;
import com.zhzao.menutwodemo.presenter.Per_presenter;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.PerView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.BuildConfig;
import com.zzhao.utils.utils.ToastShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import static com.zhzao.menutwodemo.photoutils.FileUtil.getRealFilePathFromUri;

public class Per_Msg_Activity extends BaseActivity implements PerView {

    @BindView(R.id.per_photo)
    ImageView perPhoto;
    @BindView(R.id.per_ic)
    RelativeLayout perIc;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.per_name)
    RelativeLayout perName;
    @BindView(R.id.photo_name)
    TextView photoName;
    @BindView(R.id.per_nkname)
    RelativeLayout perNkname;

    @BindView(R.id.per_wait)
    GifImageView perWait;
    @BindView(R.id.per_back)
    ImageView perBack;

    private Per_presenter presenter;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;
    // 1: qq, 2: weixin
    private int type;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_per_msg;
    }

    @Override
    public void initview() {
        ButterKnife.bind(this);
        presenter = new Per_presenter(this);
        perIc.setOnClickListener(this);
        perName.setOnClickListener(this);
        perNkname.setOnClickListener(this);
        perBack.setOnClickListener(this);
    }

    @Override
    public void initdata() {
        //  presenter.getPerMsg();获取用户信息
        String name = SharePreUtils.getShareprefervalue("name");
        String knname = SharePreUtils.getShareprefervalue("knname");
        String icon=SharePreUtils.getShareprefervalue("icon");
        photoName.setText(knname);
        username.setText(name);
        Glide.with(this).load(icon).into(perPhoto);

        //设置动画
        GifDrawable gifDrawable = null;
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.dd);
            perWait.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.per_ic://头像
                type = 1;
                uploadHeadImage();
                break;
            case R.id.per_name://用户名

                break;
            case R.id.per_nkname://用户昵称

                break;
            case R.id.per_back:
                this.finish();
                break;
        }
    }

    @Override
    public void success(UserInfo msg) {



    }


    @Override
    public void success(String msg) {
        try {
            JSONObject oo = new JSONObject(msg);
            String m = oo.optString("msg");
            toast(m);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void failure(String msg) {
        toast(msg);
    }

    @Override
    public void showLoading() {
        perWait.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        perWait.setVisibility(View.GONE);
    }

    @Override
    public void toast(String msg) {
        ToastShow.showToast(this, msg);
    }

    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = view.findViewById(R.id.btn_camera);
        TextView btnPhoto = view.findViewById(R.id.btn_photo);
        TextView btnCancel = view.findViewById(R.id.btn_cancel);
        //设置popup
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(Per_Msg_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(Per_Msg_Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamera();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(Per_Msg_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(Per_Msg_Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCamera();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            }
        }
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Log.d("evan", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 跳转到照相机
     */
    private void gotoCamera() {
        Log.d("evan", "*****************打开相机********************");
        //创建拍照存储的图片文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"), System.currentTimeMillis() + ".jpg");

        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(Per_Msg_Activity.this, BuildConfig.APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    if (type == 1) {
                        Glide.with(Per_Msg_Activity.this).load(cropImagePath).into(perPhoto);
                        SharePreUtils.putShareprefer("icon", cropImagePath);
                    }
//                    else {
//                        perPhoto.setImageBitmap(bitMap);
//                    }
                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                    //----------------------------------------图片上传---------------------
                    //判断sd卡存在
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                        File file = new File(cropImagePath);
                        if (file.exists()) {//判断存在
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        System.out.println("xxxxxxxxxxxxx图片保存路径" + cropImagePath + "xxxxxxxxxxx获取路径" + file.getPath());
                        presenter.upPerPhoto(file);
                    }

                }
                break;
        }
    }

    /**
     * 打开截图界面
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
