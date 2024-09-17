package com.vintagearcadeworks.magic8ball;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "qworkload";
    private MyBroadcastReceiver MyReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAppMonitorService();
        hookupRoliBroadcast();

        // link all the variables with its id
        final ImageView imageView = (ImageView) findViewById(R.id.image_eightBall);
        Button button = (Button) findViewById(R.id.askButton);

        // create an array to store all the images
        final int a[] = {R.drawable.ball2, R.drawable.ball3, R.drawable.ball4, R.drawable.ball5};

        // ask button's onClick function
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // generate a number using Random() function
                Random random = new Random();
                int x = random.nextInt(4);


                // set the image to the view
                imageView.setImageResource(a[x]);
            }
        });
    }

    public void startAppMonitorService() {
        Log.d(TAG, "startAppMonitorService");
        Intent intent = new Intent(this, AppMonitorService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startForegroundService(intent);
        else
            startService(intent);
        //finishAndRemoveTask();
    }


    public void hookupRoliBroadcast() {
        //this line makes the broadcastreceiver
        MyReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("newPosition");
        if(intentFilter != null)
        {
            registerReceiver(MyReceiver, intentFilter);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(MyReceiver != null)
            unregisterReceiver(MyReceiver);
    }


}