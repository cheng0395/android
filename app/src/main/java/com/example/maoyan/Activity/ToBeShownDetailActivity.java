package com.example.maoyan.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.maoyan.Bean.ToBeShown;
import com.example.maoyan.Data.ReserveHelper;
import com.example.maoyan.R;
import com.google.gson.Gson;

public class ToBeShownDetailActivity extends AppCompatActivity {

    private ImageView back;
    private ImageView img;
    private TextView nm;
    private TextView star;
    private TextView showInfo;
    private TextView version;
    private TextView sc;
    private TextView wish;
    private Button btnReserve;
    private String type;
    private ReserveHelper reserveHelper;
    private String username;
    private String movieName;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_be_shown_detail);
        initView();
        back();
        content();
        Reserve();
    }

    private void Reserve() {
        // 设置按钮点击监听器
        btnReserve.setOnClickListener(v -> {
            // 如果电影类型为"预售"
            if (type.equals("预售")) {
                // 如果时间为空，则设置为"等待通知！"
                if (time == null) {
                    time = "等待通知！";
                }
                // 检查用户是否已经预约过该电影
                boolean reservationExists = reserveHelper.checkReservationExistence(username, movieName);
                if (reservationExists) {
                    // 如果用户已经预约过，显示提示信息
                    Toast.makeText(ToBeShownDetailActivity.this, "您已经预约过了！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // 如果用户未预约过，则添加预约信息
                    boolean reserve = reserveHelper.addReservation(username, "预售", movieName, time);
                    if (reserve) {
                        // 如果预约成功，显示成功信息
                        Toast.makeText(ToBeShownDetailActivity.this, "预约成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        // 如果预约失败，显示失败信息
                        Toast.makeText(ToBeShownDetailActivity.this, "预约失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void back() {
        back.setOnClickListener(v -> {
            finish();
        });
    }

    private void content() {
        // 获取传递的 JSON 字符串
        Intent intent = getIntent();
        String toBeShown = intent.getStringExtra("ToBeShown");
        // 使用 Gson 将 JSON 字符串转换为 Hot 对象
        Gson gson = new Gson();
        ToBeShown toBeShown1 = gson.fromJson(toBeShown, ToBeShown.class);
        // 使用加载图像库（如Glide）加载图片
        Glide.with(this).load(toBeShown1.getImg()).into(img);
        movieName = toBeShown1.getNm();
        time = toBeShown1.getShowInfo();
        nm.setText(movieName);
        star.setText("演员:" + toBeShown1.getStar());
        showInfo.setText(toBeShown1.getShowInfo());
        if (toBeShown1.getVersion().isEmpty()) {
            version.setVisibility(View.GONE);
        } else {
            version.setText("版本:" + toBeShown1.getVersion());
            version.setVisibility(View.VISIBLE);
        }
        sc.setText(toBeShown1.getSc());
        wish.setText(toBeShown1.getWish());
    }

    private void initView() {
        reserveHelper = new ReserveHelper(this);
        back = (ImageView) findViewById(R.id.back);
        img = (ImageView) findViewById(R.id.img);
        nm = (TextView) findViewById(R.id.nm);
        star = (TextView) findViewById(R.id.star);
        showInfo = (TextView) findViewById(R.id.showInfo);
        version = (TextView) findViewById(R.id.version);
        sc = (TextView) findViewById(R.id.sc);
        wish = (TextView) findViewById(R.id.wish);
        btnReserve = findViewById(R.id.btn_reserve);
        type = getIntent().getStringExtra("type");
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");


    }
}