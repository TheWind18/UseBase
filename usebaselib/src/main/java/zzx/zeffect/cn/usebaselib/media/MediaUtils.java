package zzx.zeffect.cn.usebaselib.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * <pre>
 *      author  ：zzx
 *      e-mail  ：zhengzhixuan18@gmail.com
 *      time    ：2017/08/17
 *      desc    ：
 *      version:：1.0
 * </pre>
 *
 * @author zzx
 */

public class MediaUtils {
    private static MediaUtils instance;
    private MediaPlayer mPlayer;

    public static MediaUtils getInstance() {
        if (instance == null) {
            synchronized (MediaUtils.class) {
                if (instance == null) {
                    instance = new MediaUtils();
                }
            }
        }
        return instance;
    }

    public void play(String path) {
        play(path, null);
    }


    public void playRaw(Context pContext, int id, OnPlayer pPlayer) {
        if (pPlayer != null) addPlayListener(pPlayer);
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(pContext, id);
        }
        try {
            mPlayer.start();
        } catch (IllegalStateException e) {
            mPlayer = null;
            mPlayer = MediaPlayer.create(pContext, id);
            mPlayer.start();
        }
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                stop();
            }

        });
        mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer player, int arg1, int arg2) {
                stop();
                return true;
            }
        });
    }

    public void prepare(String nowPath, OnPlayer pOnPlayer, final boolean prepare2start) {
        isPrepare = false;
        addPlayListener(pOnPlayer);
        if (TextUtils.isEmpty(nowPath)) {
            stop();
            return;
        }
        if (nowPath.startsWith("http://") || nowPath.startsWith("https://")) {
        } else {
            File tempFile = new File(nowPath);
            if (!tempFile.exists()) {
                stop();
                return;
            }
        }
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
        }
        try {
            mPlayer.reset();
        } catch (IllegalStateException e) {
            mPlayer = null;
            mPlayer = new MediaPlayer();
        }
        try {
            mPlayer.reset();
            mPlayer.setDataSource(nowPath);
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    isPrepare = true;
                    if (prepare2start) {
                        mp.start();
                    }
                }
            });
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer arg0) {
                    stop();
                }

            });
            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer player, int arg1, int arg2) {
                    stop();
                    return true;
                }
            });
        } catch (IOException e) {
            stop();
        }
    }

    private boolean isPrepare = false;

    public void start() {
        if (mPlayer != null && isPrepare) {
            mPlayer.start();
        }
    }

    private void play(String nowPath, OnPlayer pOnPlayer) {
        prepare(nowPath, onPlayer, true);
    }

    public void pause() {
        if (mPlayer != null) {
            try {
                if (mPlayer.isPlaying()) mPlayer.pause();
            } catch (Exception e) {
            }
        }
    }

    public void resume() {
        if (mPlayer != null) {
            if (mPlayer.getCurrentPosition() > 0) {
                mPlayer.start();
            }
        }
    }


    /***
     * 关闭播放
     */
    public void stop() {
        if (mPlayer != null) {
            try {
                if (mPlayer.isPlaying()) mPlayer.pause();
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
            } catch (IllegalStateException E) {
            }
        }
        removeListener();
    }


    private void removeListener() {
        if (onPlayer != null) {
            onPlayer.onComplete();
        }
        onPlayer = null;
    }


    private OnPlayer onPlayer;

    public interface OnPlayer {
        void onComplete();

        void onStart();
    }


    private void addPlayListener(OnPlayer pOnPlayer) {
        if (onPlayer != null) {
            onPlayer = null;
        }
        if (pOnPlayer == null) {
            return;
        }
        onPlayer = pOnPlayer;
    }


}
