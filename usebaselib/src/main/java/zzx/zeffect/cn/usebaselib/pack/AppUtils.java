package zzx.zeffect.cn.usebaselib.pack;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    
    
    /**
     * 获取手机上的非系统应用列表。
     *
     * @param context 上下文
     * @return 应用信息列表
     */
    public static List<PackageInfo> getUserInstallAppInfo(Context context) {
        List<PackageInfo> pinfos = getAllInstalledAppInfo(context);
        List<PackageInfo> UserApp = new ArrayList<>();
        if (pinfos == null || pinfos.size() <= 0) {
            return null;
        }
        // 遍历每个应用包信息
        for (PackageInfo info : pinfos) {
            ApplicationInfo appInfo = info.applicationInfo;
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                // 非系统应用。
                UserApp.add(info);
            }
        }
        return UserApp;
    }

    /**
     * 获取手机上的系统应用列表。
     *
     * @param context 上下文
     * @return 应用信息列表
     */
    public static List<PackageInfo> getSystemInstallAppInfo(Context context) {
        List<PackageInfo> pinfos = getAllInstalledAppInfo(context);
        List<PackageInfo> UserApp = new ArrayList<>();
        if (pinfos == null || pinfos.size() <= 0) {
            return null;
        }
        // 遍历每个应用包信息
        for (PackageInfo info : pinfos) {
            ApplicationInfo appInfo = info.applicationInfo;
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0) {
                // 系统应用。
                UserApp.add(info);
            }
        }
        return UserApp;
    }

    public static List<PackageInfo> getAllInstalledAppInfo(Context context) {
        // 获取所有的安装在手机上的应用软件的信息，并且获取这些软件里面的权限信息
        PackageManager pm = context.getPackageManager();// 获取系统应用包管理
        // 获取每个包内的androidmanifest.xml信息，它的权限等等
        List<PackageInfo> pinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_PERMISSIONS);
        return pinfos;
    }
}
