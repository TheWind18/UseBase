package zzx.zeffect.cn.usebase;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import zzx.zeffect.cn.usebaselib.permission.PermissionUtils;
import zzx.zeffect.cn.usebaselib.system.SystemUtils;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showTv = (TextView) findViewById(R.id.showTv);
        findViewById(R.id.testAll).setOnClickListener(this);
        findViewById(R.id.setMaxVolumeBtn).setOnClickListener(this);
        findViewById(R.id.setMinVolumeBtn).setOnClickListener(this);
        findViewById(R.id.raiseBrightBtn).setOnClickListener(this);
        findViewById(R.id.raiseVolumeBtn).setOnClickListener(this);
        findViewById(R.id.lowerBrightBtn).setOnClickListener(this);
        findViewById(R.id.lowerVolumeBtn).setOnClickListener(this);
    }


    private void showMsg(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        showTv.setText(msg + "\n" + showTv.getText());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.testAll) {
            testAll();
        } else if (v.getId() == R.id.lowerBrightBtn) {
//            SystemUtils.lowerBrightness(this);
            SystemUtils.lowerBrightness(MainActivity.this);
        } else if (v.getId() == R.id.lowerVolumeBtn) {
            SystemUtils.lowerVolume(this);
        } else if (v.getId() == R.id.raiseBrightBtn) {

            SystemUtils.raiseBrightness(this);

        } else if (v.getId() == R.id.raiseVolumeBtn) {
            SystemUtils.raiseVolume(this);
        } else if (v.getId() == R.id.setMaxVolumeBtn) {
            SystemUtils.maxVolume(this);
        } else if (v.getId() == R.id.setMinVolumeBtn) {
            SystemUtils.minVolume(this);
        }
    }


    private void testAll() {
        showMsg("当前电量：" + SystemUtils.getCurBattery(this));
        showMsg("屏幕亮度：" + SystemUtils.getWindowBrightness(this));


    }
}
