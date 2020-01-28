package com.chenke.flashcards.util;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.Button;

public class TimeCount extends CountDownTimer {
    public Button btn_verify;


    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn_verify.setBackgroundColor(Color.parseColor("#B6B6D8"));
        btn_verify.setClickable(false);
        btn_verify.setText(millisUntilFinished / 1000 +"秒后重发");
    }

    @Override
    public void onFinish() {
        btn_verify.setText("获取验证码");
        btn_verify.setClickable(true);
        btn_verify.setBackgroundColor(Color.parseColor("#108EE9"));

    }
}
