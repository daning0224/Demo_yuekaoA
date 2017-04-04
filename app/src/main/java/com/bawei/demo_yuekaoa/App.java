package com.bawei.demo_yuekaoa;

import android.app.Application;

import com.bawei.demo_yuekaoa.util.OkHttpUtils;

/**
 * 作    者：云凯文
 * 时    间：2017/4/4
 * 描    述：
 * 修改时间：
 */

public class App extends Application {

    public static OkHttpUtils okHttpUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化网络请求
        okHttpUtils = OkHttpUtils.getOkHttpUtils();
    }
}
