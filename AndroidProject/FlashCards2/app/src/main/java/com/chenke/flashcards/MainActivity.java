package com.chenke.flashcards;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.chenke.flashcards.userinfo.UserCenter;


public class MainActivity extends ActivityGroup implements View.OnClickListener {
    private Bundle mBundle = new Bundle();  //声明一个包裹对象
    private static final String TAG = "MainActivity";
    private LinearLayout ll_first, ll_second, ll_third, ll_fourth, ll_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_container = findViewById(R.id.ll_container);
        ll_first = findViewById(R.id.ll_first);
        ll_second = findViewById(R.id.ll_second);
        ll_third = findViewById(R.id.ll_third);
        ll_fourth = findViewById(R.id.ll_fourth);
        ll_first.setOnClickListener(this);
        ll_second.setOnClickListener(this);
        ll_third.setOnClickListener(this);
        ll_fourth.setOnClickListener(this);
        mBundle.putString("tag", TAG);


        Intent intent = this.getIntent();
        String msg = intent.getStringExtra("msg");
        if (msg != null && msg.equals("loginsuccess")) {
            changeContainerView(ll_fourth, "center");
        } else if (msg != null && msg.equals("logout")) {
            changeContainerView(ll_fourth, "");
        } else {
            //默认显示首页
            changeContainerView(ll_first, "");
        }


    }

    //内容视图改为指定的视图
    private void changeContainerView(View v, String s) {
        ll_first.setSelected(false);
        ll_second.setSelected(false);
        ll_third.setSelected(false);
        ll_fourth.setSelected(false);
        v.setSelected(true);  //选中指定标签
        if (v == ll_first) {
            toActivity("fisrt", TabFirstActivity.class);
        } else if (v == ll_second) {
            toActivity("second", TabSecondActivity.class);
        } else if (v == ll_third) {
            toActivity("third", TabThirdActivity.class);
        } else if (v == ll_fourth && s.isEmpty()) {
            toActivity("fourth", TabFourthActivity.class);
        } else if (v == ll_fourth && s.equals("center")) {
            toActivity("usercenter", UserCenter.class);
        }
    }

    //把内容视图切换到对应的Activity
    private void toActivity(String label, Class<?> cls) {
        Intent intent = new Intent(this, cls).putExtras(mBundle);
        //移除内容框架下所有的下级视图
        ll_container.removeAllViews();
        //启动意图指向的活动，并获取该视图的顶层视图
        View v = getLocalActivityManager().startActivity(label, intent).getDecorView();
        //设置内容视图的布局参数
        v.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //把活动页面的内容视图添加到内容框架上
        ll_container.addView(v);
    }

    //重写onClick方法
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ll_fourth) {  //查找shared文件，
            SharedPreferences sp = getSharedPreferences("user_info", Context.MODE_PRIVATE);
            String phone = sp.getString("phone", "");
            if (!phone.isEmpty()) {  //说明用户登录过
                changeContainerView(v, "center");
            } else {
                changeContainerView(v, "");
            }
        } else {
            changeContainerView(v, ""); // 点击了哪个标签，就切换到该标签对应的内容视图
        }


    }

}
