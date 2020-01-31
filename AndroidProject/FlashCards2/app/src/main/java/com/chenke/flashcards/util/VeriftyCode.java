package com.chenke.flashcards.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class VeriftyCode extends AppCompatActivity {

    private OkHttpClient okHttpClient;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //发送验证码
    public void sendVeriftyCode(final String phone) {
        okHttpClient = new OkHttpClient();
        Log.e("phone",phone);
        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://localhost:8080//user/getTestCode?phoneNumbe=" + phone)
                        .build();
                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    Log.e("msg",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Log.e("msg","发送成功");
    }
}
