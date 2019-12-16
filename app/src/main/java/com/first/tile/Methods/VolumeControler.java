package com.first.tile.Methods;

import android.media.AudioManager;


public class VolumeControler {
    private static int AlarmVolume;
    private static int MusicVolume;
    private static int RingVolume;
    private static int VoiceVolume;
    private static int NotificationVolume;

    public static boolean setAllVolumeMuted(AudioManager audioManager, boolean isMuted) {
        try {
            if (isMuted) {
                // 记住静音前的音量
                AlarmVolume = audioManager.getStreamVolume(AudioManager.STREAM_ALARM);
                MusicVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                RingVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
                VoiceVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
                NotificationVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);

                // 或许有直接设置全部静音的方法呢
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, 0, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, 0, 0);
            } else {
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, AlarmVolume, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, MusicVolume, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_RING, RingVolume, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_NOTIFICATION, NotificationVolume, 0);
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, VoiceVolume, 0);
            }
        } catch (SecurityException Se){
            System.out.println("本软件在 勿扰模式/静音模式 下工作还有问题");
            Se.printStackTrace();
            return false;
        }
        return true;
    }

}
