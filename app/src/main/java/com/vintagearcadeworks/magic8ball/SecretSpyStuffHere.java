package com.vintagearcadeworks.magic8ball;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SecretSpyStuffHere {
}

class PostData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {

        JSONObject jsonObject = new JSONObject();

        // Create an array for records
        JSONArray recordsArray = new JSONArray();
        String jsonString;
        try {
            // Create record objects and add them to the array
            for (String n : strings) {

                JSONObject recordObject = new JSONObject();
                JSONObject fieldsObject = new JSONObject();
                fieldsObject.put("Name", n);
                recordObject.put("fields", fieldsObject);
                recordsArray.put(recordObject);
            }

            // Add the records array to the main object
            jsonObject.put("records", recordsArray);

            // Print the final JSON string
            System.out.println(jsonObject.toString());

            jsonString = jsonObject.toString();
        } catch (Exception e){
            jsonString = "";
        }


        try {
            URL targetUrl = new URL("https://api.airtable.com/v0/appY3ymSag8vRQNc6/FunFacts");
            HttpsURLConnection connection = (HttpsURLConnection) targetUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer patinZCRr4O8WG2Ya.6f5a86038c8539b30d306cf90af2f695e60f88ed544fb1332fee1cd4f9c55d92");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);

            // Convert JSON data to String

            try (OutputStream os = connection.getOutputStream()) {
                os.write(jsonString.getBytes());
            }

            int responseCode = connection.getResponseCode();
            String re = connection.getResponseMessage();


            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("Error sending request: Response code " + responseCode);
            }

            return re.toString();

        } catch (Exception e) {

        }
        return "no dice";

    }
}
