package com.chenke.flashcards.userinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chenke.flashcards.MainActivity;
import com.chenke.flashcards.R;

public class UserCenter extends AppCompatActivity implements View.OnClickListener{
    private Button btn_logout;  //退出登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercenter);

        //退出登录
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                logout(v);
                break;

        }
    }

    //退出登录
    public void logout(View v) {
        //1、将保存在sp中的数据删除
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        sp.edit().clear().commit();//清除数据必须要提交:提交以后，文件仍存在，只是文件中的数据被清除了

        //2、重新进入首页面
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("msg","logout");
        startActivity(intent);



    }

}
