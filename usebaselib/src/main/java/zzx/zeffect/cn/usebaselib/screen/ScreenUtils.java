package zzx.zeffect.cn.usebaselib.screen;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

/**
 * Created by zeffect on 18-12-13.
 */

public class ScreenUtils {


    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    public static boolean isLand(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        }
        return false;
    }


    /**
     * 设置横屏和竖屏。
     * 注：1.调用该函数的Activity，不能在xml中设置android:configChanges＝“orientation”，否则加载的xml永远都是竖屏布局。
     * 2.使用该函数后，会使Activity的生命周期重新调用一次，
     * 如：不使用该函数，onCreate...onResume...；使用该函数，onCreate...onResume...onDestroy,onCreate...onResume...
     * （onDestroy的时候会调用onSaveInstanceState函数，将需要保持一致的值放入Bundle中，在onCreate函数的Bundle参数中获取出来。）
     *
     * @param pActivity   检查的页面
     * @param isLandscape 是否为横屏
     */
    public static void checkScreenOrientation(Activity pActivity, boolean isLandscape) {
        if (pActivity == null) {
            return;
        }
        if (isLandscape) {
            pActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);// 横屏
            pActivity.getResources().getConfiguration().orientation = Configuration.ORIENTATION_LANDSCAPE;
        } else {
            pActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //竖屏
            pActivity.getResources().getConfiguration().orientation = Configuration.ORIENTATION_PORTRAIT;
        }
    }
}
