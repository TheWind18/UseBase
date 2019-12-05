package zeffect.cn.musicdemo;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogRecord;

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

    public void play(String nowPath, OnPlayer pOnPlayer) {
        prepare(nowPath, pOnPlayer);
    }

    private static final int ProgressCode = 0x10;

    private Handler progressHanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == ProgressCode) {
                int current = 0;
                int duration = 0;
                if (mPlayer != null) {
                    current = mPlayer.getCurrentPosition();
                    duration = mPlayer.getDuration();
                }
                if (onPlayer != null) {
                    onPlayer.onProgress(current, duration);
                }
                if (current < duration) {
                    progressHanlder.sendEmptyMessageDelayed(ProgressCode, 1000);//一秒刷新一次
                }
            }
        }
    };


    public void playRaw(Context pContext, int id, OnPlayer pPlayer) {
        addPlayListener(pPlayer);
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(pContext, id);
        }
        try {
            mPlayer.start();
        } catch (IllegalStateException e) {
            mPlayer = null;
            mPlayer = MediaPlayer.create(pContext, id);
            mPlayer.start();
            if (onPlayer != null) {
                onPlayer.onStart();
            }
            progressHanlder.sendEmptyMessage(ProgressCode);
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

    private void prepare(String nowPath, OnPlayer pOnPlayer) {
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
                    mp.start();
                    if (onPlayer != null) {
                        onPlayer.onStart();
                    }
                    progressHanlder.sendEmptyMessage(ProgressCode);
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
        if (progressHanlder.hasMessages(ProgressCode)) {
            progressHanlder.removeMessages(ProgressCode);
        }
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

        void onProgress(int current, int duration);

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
