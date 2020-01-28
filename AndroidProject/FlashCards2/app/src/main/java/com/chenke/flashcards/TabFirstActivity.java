package com.chenke.flashcards;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chenke.flashcards.study.ColorStudy;
import com.chenke.flashcards.study.NumberStudy;
import com.chenke.flashcards.study.ShapeStudy;

public class TabFirstActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_number;  //数字按钮
    private Button btn_shape;   //形状按钮
    private Button btn_color;   //颜色按钮

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabfirst);
        btn_number = findViewById(R.id.number);
        btn_shape = findViewById(R.id.shape);
        btn_color = findViewById(R.id.color);

        //注册监听器
        btn_number.setOnClickListener(this);
        btn_shape.setOnClickListener(this);
        btn_color.setOnClickListener(this);
    }

    //重写
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.number:
                onClickBtn_number(v);
                break;
            case R.id.shape:
                onClickBtn_shape(v);
                break;
            case R.id.color:
                onClickBtn_color(v);
                break;
        }
    }

    //数字学习
    private void onClickBtn_number(View view) {
        //处理逻辑
        Intent intent = new Intent(this, NumberStudy.class);
        startActivity(intent);
    }

    //形状学习
    private void onClickBtn_shape(View view) {
        //处理逻辑
        Intent intent = new Intent(this, ShapeStudy.class);
        startActivity(intent);
    }

    //颜色学习
    private void onClickBtn_color(View view) {
        //处理逻辑
        Intent intent = new Intent(this, ColorStudy.class);
        startActivity(intent);
    }




}
