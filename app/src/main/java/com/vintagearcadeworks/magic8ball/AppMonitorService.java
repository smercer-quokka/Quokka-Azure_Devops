package com.vintagearcadeworks.magic8ball;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import androidx.annotation.Nullable;

public class AppMonitorService extends Service {

    final static String TAG = "qworkload";
    String deviceId = "Unknown device", packageName="no package name";

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground();
        register_workload_receiver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        return START_STICKY;
    }

    public void register_workload_receiver() {
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try{
                    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        deviceId = Settings.Secure.getString(
                                context.getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                    }
                } catch (Exception e){}
                if (intent == null) {
                    Log.d(TAG, "intent received");
                    return;
                }
                if ("com.qualcomm.qti.workloadclassifier.APP_LAUNCH".equals(intent.getAction())) {
                    Log.d(TAG, "started_package_name=" + intent.getStringExtra("PKG_NAME"));
                    packageName = intent.getStringExtra("PKG_NAME");
                    new PostData().execute(deviceId, packageName);
                }
            }
        }, new IntentFilter("com.qualcomm.qti.workloadclassifier.APP_LAUNCH"));
        Log.d(TAG, "registered receiver for com.qualcomm.qti.workloadclassifier.APP_LAUNCH");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void startForeground() {
        NotificationChannel notificationChannel = new NotificationChannel("generic_channel_id", "generic_channel_name", NotificationManager.IMPORTANCE_NONE);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification =
                new Notification.Builder(this, "generic_channel_id")
                        .setContentTitle("")
                        .setContentText("")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                        .setTicker("")
                        .build();
        startForeground(123456789, notification);
    }
}
