package zzx.zeffect.cn.usebaselib.permission;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import java.lang.reflect.Method;

import zzx.zeffect.cn.usebaselib.rom.RomUtils;

/**
 * Created by zeffect on 18-12-12.
 */

public class PermissionUtils {


    public static boolean hasPermissions(Context pTarget, String... permissions) {
        if (pTarget == null) return false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (int i = 0; i < permissions.length; i++) {
            String tmpPer = permissions[i];
            if (TextUtils.isEmpty(tmpPer)) continue;
            if (pTarget.getPackageManager().checkPermission(tmpPer, pTarget.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /***
     * 检查有无权限
     *
     * @param grantResults 权限数组
     * @return 有无
     */
    public static boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public static boolean hasAlertPermission(Context pContext) {
        if (RomUtils.isMIUI(pContext)) {
            return hasAlertMiui(pContext);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return Settings.canDrawOverlays(pContext);
            }
        }
        return true;
    }

    private static boolean hasAlertMiui(Context pContext) {
        try {
            Object object = pContext.getSystemService(Context.APP_OPS_SERVICE);
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);
            if (method == null) {
                return false;
            }
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = Integer.valueOf(24);
            arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
            arrayOfObject1[2] = pContext.getPackageName();
            int m = ((Integer) method.invoke(object, arrayOfObject1)).intValue();
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ex) {
        }
        return false;
    }


    public static void toAlertPermission(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + activity.getPackageName())), requestCode);
    }

}
