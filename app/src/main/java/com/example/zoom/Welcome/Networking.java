package com.example.zoom.Welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.zoom.R;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class Networking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);

        AndroidNetworking.initialize(getApplicationContext());

//        OkHttpClient okHttpClient = new OkHttpClient() .newBuilder()
//                .addNetworkInterceptor(new StethoInterceptor())
//                .build();
//        AndroidNetworking.initialize(getApplicationContext(),okHttpClient);


        //Making a GET Request
        AndroidNetworking.get("https://api.agora.io/v1/apps/ed8f3b1376a64a8aba8e281bad2a7cef/" +
                        ".,," +
                        " cloud_recording/acquire")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.print("ABRAR"+response);
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        System.out.print("ABRAR"+error);
                        // handle error
                    }
                });

        //Making a POST Request
        AndroidNetworking.post("https://api.agora.io/v1/apps/ed8f3b1376a64a8aba8e281bad2a7cef/cloud_recording/acquire")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}


