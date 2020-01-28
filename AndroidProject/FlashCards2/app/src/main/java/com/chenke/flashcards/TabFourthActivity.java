package com.chenke.flashcards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chenke.flashcards.userinfo.Login;
import com.chenke.flashcards.userinfo.Register;

public class TabFourthActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_register;  //注册按钮
    private Button btn_login;    //登录按钮

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //判断用户是否登录
        isLogin();
        setContentView(R.layout.activity_tabfourth);

        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void isLogin() {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String phone = sp.getString("phone", ""); //若无数据phone为空
        if (phone.isEmpty()) {
            //本地没有保存过用户信息，登录
            doLogin();
        } else {
            //已经登录过，直接加载用户信息
            doUser();
        }
    }

    //用户未登录
    private void doLogin() {
        //默认当前页面，不做任何操作
    }
    //用户已登录
    private void doUser() {
        //跳转到已登录页面
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Login(v);
                break;
            case R.id.btn_register:
                Register(v);
                break;

        }
    }

    //点击注册按钮
    private void Register(View v) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    //点击登录按钮
    private void Login(View v) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
