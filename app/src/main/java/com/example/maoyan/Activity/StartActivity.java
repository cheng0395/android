package com.example.maoyan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.maoyan.R;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;

public class StartActivity extends AppCompatActivity {
    TimeCount timeCount;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tomainActive();
        }
    };
    // 进入主页面
    private void tomainActive() {
        startActivity(new Intent(this, LoginActivity.class));
        // 跳转完成后注销
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // 1.延迟三秒执行 runnable
        handler.postDelayed(runnable, 3000);
        // 2.初始化,共执行四秒,一秒执行一次
        timeCount = new TimeCount(4000, 1000);
        timeCount.start();
    }

    // 计时器
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long l) {
        }
        @Override
        public void onFinish() {
            // 结束之后移除runnable(进入主页)
            handler.removeCallbacks(runnable);
        }
    }
}
