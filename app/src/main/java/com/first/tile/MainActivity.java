package com.first.tile;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends Activity {
    private Switch mSwitch;
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
                        AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
                        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,0,0);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
                        audioManager.setStreamVolume(AudioManager.STREAM_RING,0,0);
                        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,0,0);
                        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,0,0);
                    } else {
                        AudioManager audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
                        audioManager.setStreamVolume(AudioManager.STREAM_ALARM,audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM),0);
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),0);
                        audioManager.setStreamVolume(AudioManager.STREAM_RING,audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),0);
                        audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION,audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION),0);
                        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),0);
                    }
                }
            });
        }

    }
