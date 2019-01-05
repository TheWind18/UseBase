package zzx.zeffect.cn.usebaselib.anim;

import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 随便做点动画
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/08/23
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class AnimUtils {
    /***
     * 左右抖动动画
     * @param pView 控件
     */
    public static void jitter(View pView) {
        if (pView == null) return;
        TranslateAnimation tempAnimation = new TranslateAnimation(0, 5, 0, 0);
        tempAnimation.setDuration(300);
        tempAnimation.setInterpolator(new CycleInterpolator(3));
        tempAnimation.setRepeatCount(3);
        pView.startAnimation(tempAnimation);
    }
}
