package com.hhd.wanandroid_mvvm.ui;


import com.hehandong.retrofithelper.utils.Utils;
import com.tencent.bugly.crashreport.CrashReport;

import androidx.multidex.MultiDexApplication;

/**
 * Created by Stay on 2/2/16.
 * Powered by www.stay4it.com
 */
public class App extends MultiDexApplication {

    private static App sApp;

    public static App getInstance() {
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;

        Utils.init(this);
//        AppStatusTracker.init(this);

        CrashReport.initCrashReport(getApplicationContext(), "4f1c3de025", false);
//        Utils.init(this);
    }
}
