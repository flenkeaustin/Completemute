package com.first.tile;

import android.graphics.drawable.Icon;
import android.media.AudioManager;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;

import com.first.tile.Methods.VolumeControler;


public class QuickSettingService extends TileService{
    private final boolean MUTED_OFF = false;                // 不静音
    private final boolean MUTED_ON = true;                  // 静音
    private final String LOG_TAG = "QuickSettingService";
    private boolean toggleState = MUTED_OFF;


    /**
     * 当用户从Edit栏添加到快速设定中调用
     **/
    @Override
    public void onTileAdded() {
        Log.d(LOG_TAG, "onTileAdded");
    }

    /**
     * 当用户从快速设定栏中移除的时候调用
     **/
    @Override
    public void onTileRemoved() {
        Log.d(LOG_TAG, "onTileRemoved");
    }

    /**
     * 点击时
     **/
    @Override
    public void onClick() {
        Log.d(LOG_TAG, "onClick state = " + getQsTile().getState());
        Icon icon;
        if (VolumeControler.setAllVolumeMuted((AudioManager)getSystemService(AUDIO_SERVICE), toggleState)) {
            Log.d(LOG_TAG, "QS_ActionCompleted");
        } else {
            return ; // 设置音量失败时，不更改状态
        }

        if (toggleState == MUTED_ON) {                      // 静音
            toggleState = MUTED_OFF;
            icon =  Icon.createWithResource(getApplicationContext(), R.drawable.ic_volume_off);
            getQsTile().setState(Tile.STATE_INACTIVE); // 更改成非活跃状态

        } else {                                            // 恢复音量
            toggleState = MUTED_ON;
            icon = Icon.createWithResource(getApplicationContext(), R.drawable.ic_volume_up);
            getQsTile().setState(Tile.STATE_ACTIVE);  //  更改成活跃状态

        }

        getQsTile().setIcon(icon); // 设置图标
        getQsTile().updateTile();  // 更新Tile
    }


    /**
     * 打开下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
     * 在 TleAdded 之后会调用一次
     **/
    @Override
    public void onStartListening () {
        Log.d(LOG_TAG, "onStartListening");
    }


    /**
     *  关闭下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
     *  在 onTileRemoved 移除之前也会调用移除
     **/
    @Override
    public void onStopListening () {
        Log.d(LOG_TAG, "onStopListening");
    }
}
