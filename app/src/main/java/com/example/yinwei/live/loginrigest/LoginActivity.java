package com.example.yinwei.live.loginrigest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yinwei.live.MainActivity;
import com.example.yinwei.live.R;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;
import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;

public class LoginActivity extends AppCompatActivity {


    private EditText mAccountEdt;
    private EditText mPasswordEdt;
    private Button mLoginBtn;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findAllViews();
        setListeners();

    }


    private void findAllViews() {
        mAccountEdt = (EditText) findViewById(R.id.account);
        mPasswordEdt = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.login);
        mRegisterBtn = (Button) findViewById(R.id.register);
    }

    private void setListeners() {
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//登录操作
                login();
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//注册的操作
                register();
            }
        });
    }

    private void register() {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivity(intent);

    }

    private void login() {
        final String accountStr = mAccountEdt.getText().toString();
        String passwordStr = mPasswordEdt.getText().toString();

        if(accountStr.isEmpty() || passwordStr.isEmpty()){

            Toast.makeText(this,"用户名密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }

        //tencent 云通信SDK的登录login分为两步 tls 和 iLive
        //
        ILiveLoginManager.getInstance().tlsLogin(accountStr, passwordStr, new ILiveCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                //登陆成功。
                loginLive(accountStr, data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                //登录失败
                Toast.makeText(LoginActivity.this, "tls登录失败：" + errMsg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loginLive(String accountStr, String data) {
        //ilive登录

        ILiveLoginManager.getInstance().iLiveLogin(accountStr, data, new ILiveCallBack() {

            @Override
            public void onSuccess(Object data) {
                //最终登录成功
                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);

                //getSelfInfo();

                finish();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                //登录失败
                Toast.makeText(LoginActivity.this, "iLive登录失败：" + errMsg, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getSelfInfo() {
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
                Toast.makeText(LoginActivity.this, "获取信息失败：" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(TIMUserProfile timUserProfile) {
                //获取自己信息成功
                //LIVEApplication.getApplication().setSelfProfile(timUserProfile);
            }
        });
    }





}
