package com.vintagearcadeworks.magic8ball;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    final static String TAG = "qworkload";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.d(TAG, "null intent received");
            return;
        }
        Log.d(TAG, "action=" + intent.getAction());
        BootReceiver.startAppMonitorService(context);
    }

    public static void startAppMonitorService(Context context) {
        Log.d(TAG, "startAppMonitorService");
        Intent intent = new Intent(context, AppMonitorService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}