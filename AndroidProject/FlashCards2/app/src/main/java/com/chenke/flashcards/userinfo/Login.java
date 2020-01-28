package com.chenke.flashcards.userinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chenke.flashcards.MainActivity;
import com.chenke.flashcards.R;
import com.chenke.flashcards.bean.User;
import com.chenke.flashcards.util.TimeCount;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btn_verify;  //发送验证码按钮
    private Button btn_login_now;  //登录按钮
    private EditText edit_phone;  //手机号
    private EditText edit_verify;  //验证码

    private Toolbar tl_head;  //顶部工具栏

    private TimeCount time;  //重发验证码工具类



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        tl_head = findViewById(R.id.tl_head);
        //设置左边导航栏图标
        tl_head.setNavigationIcon(R.drawable.back);
        //设置标题
        tl_head.setTitle("登录");
        //设置标题颜色
        tl_head.setTitleTextColor(getResources().getColor(R.color.zfb));
        //替换系统自带的ActionBar
        setSupportActionBar(tl_head);
        //给导航图标设置监听器
        tl_head.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_verify = findViewById(R.id.btn_verify);
        btn_login_now = findViewById(R.id.btn_login_now);
        edit_phone = findViewById(R.id.edit_phone);
        edit_verify = findViewById(R.id.edit_verify);

        time = new TimeCount(60000,1000);
        //将按钮传给工具类
        time.btn_verify = btn_verify;


        btn_verify.setOnClickListener(this);
        btn_login_now.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_verify:
                sendVerify(v);
                break;
            case R.id.btn_login_now:
                login_now(v);
                break;

        }
    }


    //登录接口
    private void login_now(View v) {
        //获取输入框内手机号
        String phone = edit_phone.getText().toString();
        //获取输入框内验证码
        String verify = edit_verify.getText().toString();
        //如果手机号或者验证码为空。给出提示：不允许登录
        if (phone.isEmpty() || verify.isEmpty()) {
            //提示信息
            Toast.makeText(Login.this, "手机号或验证码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        //调用接口，判断手机号和验证码是否正确匹配
        if (true) {
            //记录登录状态，保存用户信息
            User user = new User();
            user.setPhone(phone);
            user.setUserName(phone);  //用户名暂定使用电话号码

            saveUser(user);

            //跳转到登录成功页面（用户中心）替换我的界面view
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("msg","loginsuccess");
            startActivity(intent);

        } else {
            //提示信息
            Toast.makeText(Login.this, "手机号或验证码错误",Toast.LENGTH_LONG).show();
            return;
        }


    }

    //发送验证码接口
    private void sendVerify(View v) {

        //将手机号传给服务端
        //获取输入框内手机号
        String phone = edit_phone.getText().toString();
        if (phone.isEmpty()) {
            //提示信息
            Toast.makeText(Login.this, "手机号不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        time.start();
        //调用服务端接口，将手机号传给服务端

    }

    //保存用户信息
    public void saveUser(User user) {
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",user.getUserName());
        editor.putString("phone",user.getPhone());
        editor.commit();//必须提交，否则保存不成功

    }
}


