package com.zhzao.menutwodemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.onetime.platform.R;
import com.zhzao.menutwodemo.entity.User;
import com.zhzao.menutwodemo.presenter.PhonePresenter;
import com.zhzao.menutwodemo.utils.SharePreUtils;
import com.zhzao.menutwodemo.view.PhoneView;
import com.zzhao.utils.Base.BaseActivity;
import com.zzhao.utils.utils.ToastShow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WelcomePhoneActivity extends BaseActivity implements PhoneView {


    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_pwd)
    EditText userPwd;
    @BindView(R.id.un_user)
    TextView unUser;
    @BindView(R.id.phone_login)
    Button phoneLogin;

    String LOG = "WelcomePhoneActivity";
    @BindView(R.id.progress)
    ProgressBar progress;
    private PhonePresenter presenter;
    private Unbinder bind;
    private boolean b;

    @Override
    public Boolean isFull() {
        return true;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcome_phone;
    }

    @Override
    public void initview() {
        bind = ButterKnife.bind(this);
            Intent intent = getIntent();
            b = intent.getBooleanExtra("sign",false);
            unUser.setOnClickListener(this);
            phoneLogin.setOnClickListener(this);
            presenter = new PhonePresenter(this);

    }

    @Override
    public void initdata() {
    }


    @Override
    public void setClick(View view) {
        switch (view.getId()) {
            case R.id.un_user:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.phone_login:
                String phonemsg = userPhone.getText().toString();
                String pwdmsg = userPwd.getText().toString();
                presenter.login(phonemsg, pwdmsg);
                break;
        }

    }

    @Override
    public void success(String msg) {
        Gson gson = new Gson();
        System.out.println("xxxxxxxx"+msg);
        User user = gson.fromJson(msg, User.class);
        String token = user.getData().getToken();
        String code=user.getCode();
        int uid=user.getData().getUid();//uid
        String name=user.getData().getUsername();//用户名
        String knname=user.getData().getNickname();
        String icon=user.getData().getIcon();//头像
    if("0".equals(code)){//成功
        SharePreUtils.putShareprefer("token", token);
        SharePreUtils.putShareprefer("uid",uid+"");
        SharePreUtils.putShareprefer("icon",icon);
        SharePreUtils.putShareprefer("name",name);
        SharePreUtils.putShareprefer("knname",knname);
        if(b){
            finish();
        }else{
            SharePreUtils.putSharepreferBoolean("login",true);
           iumpActivity();
        }

    }else{//失败
        toast("登录失败");
    }


    }

    //跳转
    private void iumpActivity() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.bottom_to_top_in,R.anim.bottom_to_top_out);
    }

    @Override
    public void failure(String msg) {
        Log.e(LOG, msg);
    }

    @Override
    public void toast(String msg) {
        ToastShow.showToast(this, msg);
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
         progress.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.disPatch();
        bind.unbind();
    }


}
