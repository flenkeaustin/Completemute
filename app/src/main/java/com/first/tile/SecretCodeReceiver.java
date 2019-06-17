package com.first.tile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.first.tile.MainActivity;

public class SecretCodeReceiver extends BroadcastReceiver {
    public SecretCodeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent activityIntent = new Intent(context, MainActivity.class);
        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityIntent);
    }
}
