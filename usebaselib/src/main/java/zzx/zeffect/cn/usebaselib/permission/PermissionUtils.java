package zzx.zeffect.cn.usebaselib.permission;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

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
}
