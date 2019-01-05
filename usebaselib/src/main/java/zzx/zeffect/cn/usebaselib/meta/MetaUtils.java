package zzx.zeffect.cn.usebaselib.meta;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

public class MetaUtils {
    public static <T extends Object> T getMetaData(Context context, String key, T defaultObject) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        try {
            Bundle sp = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData;
            if (defaultObject instanceof String) {
                return (T) sp.getString(key, (String) defaultObject);
            } else if (defaultObject instanceof Integer) {
                return (T) Integer.valueOf(sp.getInt(key, (Integer) defaultObject));
            } else if (defaultObject instanceof Boolean) {
                return (T) Boolean.valueOf(sp.getBoolean(key, (Boolean) defaultObject));
            } else if (defaultObject instanceof Float) {
                return (T) Float.valueOf(sp.getFloat(key, (Float) defaultObject));
            } else if (defaultObject instanceof Long) {
                return (T) Long.valueOf(sp.getLong(key, (Long) defaultObject));
            }
            return defaultObject;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return defaultObject;
    }
}
