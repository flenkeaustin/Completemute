package com.first.tile;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends Activity {
    private Switch mSwitch,hSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

            mSwitch = (Switch) findViewById(R.id.switch_all);
            // 添加监听
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
                        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 0, 0);
                    } else {
                        AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
                        audioManager.setStreamVolume(AudioManager.STREAM_ALARM, audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_RING, audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION), 0);
                        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), 0);
                    }
                }
            });
        hSwitch = (Switch)findViewById(R.id.switch_hide);
        hSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showLauncherIcon(isChecked);
            }
        });
    }
    public void showLauncherIcon(boolean isShow){
        PackageManager packageManager = this.getPackageManager();
        int show = isShow? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                         : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        packageManager.setComponentEnabledSetting(getAliseComponentName(), show, PackageManager.DONT_KILL_APP);
    }
    private ComponentName getAliseComponentName(){
        return new ComponentName(MainActivity.this, "com.first.tile.MainActivityAlias");
        // 在AndroidManifest.xml中为MainActivity定义了一个别名为MainActivity-Alias的activity，是默认启动activity、是点击桌面图标后默认程序入口
    }
}
