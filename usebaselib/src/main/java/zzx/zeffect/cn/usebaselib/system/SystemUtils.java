package zzx.zeffect.cn.usebaselib.system;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Calendar;

import zzx.zeffect.cn.usebaselib.R;
import zzx.zeffect.cn.usebaselib.permission.PermissionUtils;

/**
 *
 */
public class SystemUtils {

    public static void addCalendarEvent(Context pContext, String title, String location, Calendar begin, Calendar end) {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        if (intent.resolveActivity(pContext.getPackageManager()) != null) {
            pContext.startActivity(intent);
        }
    }

    public static void startTimer(Context pContext, String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(pContext.getPackageManager()) != null) {
            pContext.startActivity(intent);
        }
    }

    public static void createAlarm(Context pContext, String message, int hour, int minutes, ArrayList<Integer> days) {
        if (TextUtils.isEmpty(message)) {
            message = pContext.getString(R.string.base_default_alarm_message);
        }
        if (hour < 0) {
            hour = 0;
        } else if (hour > 23) {
            hour = 23;
        }
        if (minutes < 0) {
            minutes = 0;
        } else if (minutes > 59) {
            minutes = 59;
        }
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (days != null && !days.isEmpty()) {
            intent.putExtra(AlarmClock.EXTRA_DAYS, days);
        }
        if (intent.resolveActivity(pContext.getPackageManager()) != null) {
            pContext.startActivity(intent);
        }
    }

    public static int getCurBattery(Context pContext) {
        BatteryManager batteryManager = (BatteryManager) pContext.getSystemService(Context.BATTERY_SERVICE);
        int defaultBattery = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            defaultBattery = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        }
        return defaultBattery;
    }


    public static void maxVolume(Context pContext) {
        maxVolume(pContext, AudioManager.STREAM_MUSIC);
    }

    public static void maxVolume(Context pContext, int volumeType) {
        AudioManager am = (AudioManager) pContext.getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(volumeType);
        am.setStreamVolume(volumeType, max, AudioManager.FLAG_SHOW_UI);
    }

    public static void minVolume(Context pContext) {
        minVolume(pContext, AudioManager.STREAM_MUSIC);
    }

    public static void minVolume(Context pContext, int volumeType) {
        AudioManager am = (AudioManager) pContext.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(volumeType, 0, AudioManager.FLAG_SHOW_UI);
    }

    public static void raiseVolume(Context pContext) {
        raiseVolume(pContext, AudioManager.STREAM_MUSIC);
    }

    public static void raiseVolume(Context pContext, int volumeType) {
        AudioManager am = (AudioManager) pContext.getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(volumeType);
        int now = am.getStreamVolume(volumeType);
        int tmpVolume = now + max / 5;
        if (tmpVolume > max) {
            tmpVolume = max;
        }
        am.setStreamVolume(volumeType, tmpVolume, AudioManager.FLAG_SHOW_UI);
    }

    public static void lowerVolume(Context pContext) {
        lowerVolume(pContext, AudioManager.STREAM_MUSIC);
    }

    public static void lowerVolume(Context pContext, int volumeType) {
        AudioManager am = (AudioManager) pContext.getSystemService(Context.AUDIO_SERVICE);
        int max = am.getStreamMaxVolume(volumeType);
        int now = am.getStreamVolume(volumeType);
        int tmpVolume = now - max / 5;
        if (tmpVolume < 0) {
            tmpVolume = 0;
        }
        am.setStreamVolume(volumeType, tmpVolume, AudioManager.FLAG_SHOW_UI);
    }

    /**
     * 获取屏幕当前亮度
     *
     * @param pContext
     * @return
     */
    public static int getScreenBrightness(Context pContext) {
//        Manifest.permission.WRITE_SETTINGS,需要权限
        if (!PermissionUtils.hasPermissions(pContext, Manifest.permission.WRITE_SETTINGS)) {
            return 0;
        }
        ContentResolver contentResolver = pContext.getContentResolver();
        int defVal = 125;
        return Settings.System.getInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, defVal);
    }

    public static void lowerBrightness(Context context) {
        int now = getScreenBrightness(context);
        now = now - 51;
        if (now < 51) {
            now = 51;
        }
        setScreenBrightness(context, now);
    }

    public static void raiseBrightness(Context context) {
        int now = getScreenBrightness(context);
        now = now + 51;
        if (now < 255) {
            now = 255;
        }
        setScreenBrightness(context, now);
    }

    /**
     * 保存屏幕亮度
     *
     * @param pContext
     * @param lighit
     */
    public static void setScreenBrightness(Context pContext, int lighit) {
        setScrennManualMode(pContext);
        if (lighit > 255) {
            lighit = 255;
        } else if (lighit < 0) {
            lighit = 0;
        }
        ContentResolver contentResolver = pContext.getContentResolver();
        Settings.System.putInt(contentResolver,
                Settings.System.SCREEN_BRIGHTNESS, lighit);
    }

    /**
     * 首先，需要明确屏幕亮度有两种调节模式：
     * Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC：值为1，自动调节亮度。
     * Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL：值为0，手动模式。
     * 如果需要实现亮度调节，首先需要设置屏幕亮度调节模式为手动模式。
     *
     * @param pContext
     */
    public static void setScrennManualMode(Context pContext) {
        ContentResolver contentResolver = pContext.getContentResolver();
        try {
            int mode = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置当前程序亮度
     *
     * @param activity
     * @param brightness
     */
    public static void setWindowBrightness(Activity activity, int brightness) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        if (brightness < 0) {
            brightness = 0;
        } else if (brightness > 255) {
            brightness = 255;
        }
        lp.screenBrightness = brightness / 255.0f;
        window.setAttributes(lp);
    }

}
