package com.zhzao.menutwodemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhzao.menutwodemo.entity.Version;
import com.zhzao.menutwodemo.presenter.VideoPresenter;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.VideoView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Sliding_menuLeftActivity extends BaseActivity implements VideoView{


    @BindView(R.id.back_back)
    TextView backBack;
    @BindView(R.id.versionCode_check)
    RelativeLayout versionCodeCheck;
    @BindView(R.id.idea_back)
    RelativeLayout ideaBack;
    @BindView(R.id.about_project)
    RelativeLayout aboutProject;
    @BindView(R.id.clear_cache)
    RelativeLayout clearCache;
    @BindView(R.id.verson_name)
    TextView versonName;
    @BindView(R.id.cache)
    TextView cache;
    @BindView(R.id.pre_back)
    Button preBack;
    private Unbinder bind;
    private int versionCode;
    private VideoPresenter presenter;

    @Override
    public Boolean isFull() {
        return false;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sliding_menu;
    }

    @Override
    public void initview() {
        bind = ButterKnife.bind(this);
        backBack.setOnClickListener(this);
        versionCodeCheck.setOnClickListener(this);
        ideaBack.setOnClickListener(this);
        aboutProject.setOnClickListener(this);
        clearCache.setOnClickListener(this);
        preBack.setOnClickListener(this);

        presenter = new VideoPresenter(this);
        initversion();
        initCache();
    }

    private void initCache() {//初始化计算缓存的大小
        long filesize = 0;
        filesize += getDirSize(getCacheDir());//缓存的大小
        filesize += getDirSize(getFilesDir());//文件大小
        cache.setText(formatFileSize(filesize));
    }

    private long getDirSize(File file) {//计算传来文件的大小
        if (file == null) {
            return 0;
        }
        long cacheLong = 0;
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()) {//如果是文件
                cacheLong += file1.length();
            } else if (file1.isDirectory()) {
                cacheLong += file1.length();//计算
                cacheLong += getDirSize(file1); //在次调用递归
            }
        }
        return cacheLong;


    }

    private void initversion() {//初始化版本号
        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            String versionName = info.versionName;
            versionCode = info.versionCode;
            versonName.setText("V" + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initdata() {

    }


    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.back_back:
                this.finish();
                break;
            case R.id.versionCode_check://版本更新

                final AlertDialog.Builder versionBuilder = new AlertDialog.Builder(this);
                versionBuilder.setTitle("检查版本");
                versionBuilder.setMessage("确定要版本更新？");
                versionBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                versionBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                   //     presenter.getVersion();
                    }
                });

                versionBuilder.show();
                break;
            case R.id.clear_cache://清除缓存
                final AlertDialog.Builder clearBuilder = new AlertDialog.Builder(this);
                clearBuilder.setTitle("清理缓存");
                clearBuilder.setMessage("确定要清理缓存？");
                clearBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                clearBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //清除缓存
                        clearCaches();
                    }
                });
                clearBuilder.show();

                break;
            case R.id.about_project://关于一刻钟

                break;
            case R.id.idea_back://意见反馈

                break;
            case R.id.per_back:
                SharePreUtils.removeShareprefer("uid");
                SharePreUtils.removeShareprefer("login");
                startActivity(new Intent(Sliding_menuLeftActivity.this,WelcomeActivity.class));

                break;

        }

    }

    private void updataVersion() {

    }

    /**
     * 清除缓存
     */
    private void clearCaches() {
        clearCaches(getCacheDir());//清除cache目录下缓存
        clearCaches(getFilesDir());//清除files目录下缓存
        //清除Shareprefrence
        File file = new File("/data/data" + getPackageName() + "/shared_prefs");
        clearCaches(file);
        cache.setText("0 B");
    }

    public void clearCaches(File dir) {

        if (dir != null && dir.isDirectory()) {

            //得到该目录下所有文件
            File[] f = dir.listFiles();
            //遍历删除该目录下所有文件
            if (f != null && f.length > 0) {
                for (File file1 : f) {
                    //删除方法
                    file1.delete();
                }
            }
            ToastShow.showToast(Sliding_menuLeftActivity.this, "清除成功");
        }
    }

    @Override
    public void success(String msg) {
        try {
            JSONObject oo=new JSONObject(msg);
            if(oo.optInt("code")==0){
                JSONObject data = oo.getJSONObject("data");
                String url=data.getString("apkUrl");
                int  newVersionCode= Integer.parseInt(data.optString("versionCode"));
                String newVersionName=data.optString("versionName");

                if(versionCode <= newVersionCode){
                    File file=new File(Environment.getExternalStorageDirectory()+"/version/app.apk");

                           if(file != null && file.exists()){
                               install(file);
                         //    走安装逻辑
                           }else{
                          //     下载逻辑
                            download();
                         }
                }
            }else{
                toast("Sliding_menuLeftActivity:"+oo.optString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //开始下载
    private void download() {
        //最简单
        // todo 要修改成retrofit+Rxjava



    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toast(String msg) {
        ToastShow.showToast(Sliding_menuLeftActivity.this,msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
    /**
     * 安装新版本
     */
    private void install(File file) {
        //调用系统安装器
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
        startActivity(intent);
    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");//保留两位小数
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


}
