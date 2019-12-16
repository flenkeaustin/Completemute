package com.first.tile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.first.tile.Methods.VolumeControler;

public class MainActivity extends Activity {
    private Switch  mSwitch, hSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSwitch = findViewById(R.id.switch_all);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isMuted) {
                audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
                if (!VolumeControler.setAllVolumeMuted(audioManager, isMuted))
                    buttonView.setChecked(!isMuted);      // 设置音量失败就恢复按钮状态
            }
        });

        hSwitch = findViewById(R.id.switch_hide);
        hSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showLauncherIcon(!isChecked);
            }
        });
    }

    public void showLauncherIcon(boolean isShow){
        PackageManager packageManager = this.getPackageManager();
        int show = isShow ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                          : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        packageManager.setComponentEnabledSetting(getAliseComponentName(), show, PackageManager.DONT_KILL_APP);
    }

    private ComponentName getAliseComponentName(){
        return new ComponentName(MainActivity.this, "com.first.tile.MainActivityAlias");
        // 在AndroidManifest.xml中为MainActivity定义了一个别名为MainActivity-Alias的activity，是默认启动activity、是点击桌面图标后默认程序入口
    }
}
