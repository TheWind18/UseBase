package zzx.zeffect.cn.usebaselib.app;

import android.app.Application;

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionHandler.getInstance().init(this);
    }
}
