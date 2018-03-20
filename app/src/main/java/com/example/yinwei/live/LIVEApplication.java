package com.example.yinwei.live;

import android.app.Application;
import android.content.Context;

import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.livesdk.ILVLiveConfig;
import com.tencent.livesdk.ILVLiveManager;

/**
 * Created by yinwei on 2018/3/19.
 */

public class LIVEApplication extends Application {



    private static LIVEApplication app;
    private static Context appContext;
    private ILVLiveConfig mLiveConfig;



    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        initLIVESDK();

    }

    private void initLIVESDK() {

        //appid和accounttype
        //是在腾讯云SDK后台自己创建的服务的信息
        ILiveSDK.getInstance().initSdk(getApplicationContext(), 1400075256, 23747);
        //初始化直播场景
        mLiveConfig = new ILVLiveConfig();
        ILVLiveManager.getInstance().init(mLiveConfig);

    }



    public static LIVEApplication getApplication() {
        return app;
    }


}
