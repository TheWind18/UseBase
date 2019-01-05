package zzx.zeffect.cn.usebaselib.url;

import android.net.Uri;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/12/05
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class UrlUtils {


    public static String encodeUrl(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        final String URL_REGEX = "(\\w+:\\/\\/)([^/:]+)(:\\d*)?";
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(input);
        int start = -1, end = -1;
        while (matcher.find()) {
            start = matcher.start(0);
            end = matcher.end(0);
        }
        if (start >= end) {
            return Uri.encode(input, "-_.~!*'();:@&=+$,/?#[]");
        } else {
            if (start > 1) {
                input = input.substring(start);
                start = 0;
                end = end - start;
            }
            String tempUrl = input.substring(end);
            String httpUrl = input.substring(start, end);
            String encodeUrl = Uri.encode(tempUrl, "-_.~!*'();:@&=+$,/?#[]");
            if (encodeUrl.startsWith("/") && httpUrl.endsWith("/")) {
                return httpUrl + encodeUrl.substring(1);
            } else if (encodeUrl.startsWith("/") || httpUrl.endsWith("/")) {
                return httpUrl + encodeUrl;
            } else
                return httpUrl + "/" + encodeUrl;
        }

    }

    /***
     * 给图片增加默认地址
     * @param url 地址
     * @return 图片地址
     */
    public static String appendImgUrl(String url, String defaultAppend) {
        if (TextUtils.isEmpty(url)) return "";
        if (url.startsWith("http://")
                || url.startsWith("https://")
                || url.startsWith("assets://")
                || url.startsWith("file://")
                || url.startsWith("drawable://")
                || url.startsWith("content://")) return url;
        else return defaultAppend + url;
    }
}
