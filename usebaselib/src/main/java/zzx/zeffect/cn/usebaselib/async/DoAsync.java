package zzx.zeffect.cn.usebaselib.async;


import android.content.Context;

import com.litesuits.android.async.SafeTask;

import java.lang.ref.WeakReference;

/**
 * 防止内存泄露的AsyncTask
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/07/14
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public abstract class DoAsync<Params, Progress, Result> extends SafeTask<Params, Progress, Result> {
    protected final WeakReference<Context> mTarget;

    public DoAsync(Context pWeakTarget) {
        if (pWeakTarget == null) {
            throw new NullPointerException("weak target is null");
        }
        mTarget = new WeakReference<Context>(pWeakTarget);
    }

    @Override
    protected final void onPreExecuteSafely() throws Exception {
        super.onPreExecuteSafely();
        final Context target = mTarget.get();
        if (target != null) {
            try {
//                L.d(this.hashCode() + "： on pre execute,class:" + target.getClass().getName());
                this.onPreExecute(target);//运行前的准备
            } catch (Exception e) {
//                L.d(this.hashCode() + "： on pre execute error!!!,class:" + target.getClass().getName());
            }
        } else {
//            L.d(this.hashCode() + "： on pre execute context release!!!!!");
        }
    }


    protected void onPreExecute(Context pTarget) throws Exception {
    }

    @Override
    protected final Result doInBackgroundSafely(Params... paramses) throws Exception {
        final Context target = mTarget.get();
        if (target != null) {
            try {
//                L.d(this.hashCode() + "： on do in background execute,class:" + target.getClass().getName());
                return this.doInBackground(target, paramses);//后台运行中
            } catch (Exception e) {
//                L.d(this.hashCode() + "： on do in background error!!!,class:" + target.getClass().getName());
                return null;
            }
        } else {
//            L.d(this.hashCode() + "： on do in background context release!!!!!");
            return null;
        }
    }


    protected abstract Result doInBackground(Context pTarget, Params... params) throws Exception;

    @Override
    protected final void onPostExecuteSafely(Result result, Exception e) throws Exception {
        super.onPostExecuteSafely(result, e);
        final Context target = mTarget.get();
        if (target != null) {
            try {
//                L.d(this.hashCode() + "： on post execute,class:" + target.getClass().getName());
                this.onPostExecute(target, result);
            } catch (Exception e1) {
//                L.d(this.hashCode() + "： on post execute,class:" + target.getClass().getName());
            }
        } else {
//            L.d(this.hashCode() + "： on post context release!!!!!");
        }
    }




    protected void onPostExecute(Context pTarget, Result pResult) throws Exception {

    }

    @Override
    protected final void onProgressUpdateSafely(Progress... values) throws Exception {
        super.onProgressUpdateSafely(values);
        final Context target = mTarget.get();
        if (target != null) {
            try {
//                L.d(this.hashCode() + "： on progress execute,class:" + target.getClass().getName());
                this.onProgressUpdate(target, values);
            } catch (Exception e) {
//                L.d(this.hashCode() + "： on progress execute,class:" + target.getClass().getName());
            }
        } else {
//            L.d(this.hashCode() + "： on progress context release!!!!!");
        }
    }


    protected void onProgressUpdate(Context pTarget, Progress... values) throws Exception {
    }

}
