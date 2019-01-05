package zzx.zeffect.cn.usebaselib.device;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import zzx.zeffect.cn.usebaselib.permission.PermissionUtils;
import zzx.zeffect.cn.usebaselib.sp.SpUtils;

/**
 * Created by Administrator on 2018/3/8.
 */

public class DeviceUtils {

    /**
     * 获取手机IMEI
     *
     * @param context 上下文
     * @return IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getImei(Context context) {
        if (context == null) {
            return "";
        }
        if (!PermissionUtils.hasPermissions(context, Manifest.permission.READ_PHONE_STATE)) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder imeiBuilder = new StringBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (int i = 0; i < telephonyManager.getPhoneCount(); i++) {
                imeiBuilder.append(telephonyManager.getDeviceId(i) + "|");
            }
        } else imeiBuilder.append(telephonyManager.getDeviceId());
        return imeiBuilder.toString();
    }


    public static final String DEFAULT_WAC = "02:00:00:00:00:00";

    /***
     * 获取机本Mac地址
     * 需要权限
     */
    public static String getMac(Context pTarget) {
        String mac = getMacAddr();
        if (mac.equalsIgnoreCase(DEFAULT_WAC)) {
            return SpUtils.get(pTarget, PHONE_MAC, DEFAULT_WAC);
        } else {
            SpUtils.put(pTarget, PHONE_MAC, mac);
            return mac;
        }
    }

    private static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return DEFAULT_WAC;
    }

    public static final String PHONE_MAC = "phone_mac";

    /**
     * 获取设备ID
     *
     * @param context h
     * @return 返回硬件序列号
     */
    public static String getAndroidID(Context context) {
        if (context == null) {
            return "";
        }
        return Build.SERIAL;
    }

    /**
     * 获取设备型号
     *
     * @return 设备型号字符串
     */
    public static String getAndroidType() {
        return Build.MANUFACTURER + " " + Build.DEVICE;
    }


}
