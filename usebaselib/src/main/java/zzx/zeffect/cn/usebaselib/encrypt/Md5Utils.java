package zzx.zeffect.cn.usebaselib.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5值加密
 *
 * @author fanjiao
 */
public class Md5Utils {


    /**
     * Md5 32位 or 16位 加密（全部返回）
     *
     * @param plainText 待加密内容
     * @return 32位加密
     */
    public static String md5(String plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (buf == null) {
            return null;
        }
        return buf.toString().toLowerCase();
    }


}
