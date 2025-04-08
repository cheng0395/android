package com.example.maoyan.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.maoyan.Fragment.HomeFragment;
import com.example.maoyan.Fragment.MineFragment;
import com.example.maoyan.Fragment.OrderFragment;
import com.example.maoyan.R;

public class MainActivity extends AppCompatActivity {


    private LinearLayout llHome;
    private ImageView imgHome;
    private TextView tvHome;
    private FrameLayout fr;
    private LinearLayout linearLayout;
    private View view8;
    private LinearLayout llOrder;
    private ImageView imgOrder;
    private TextView tvOrder;
    private LinearLayout llMine;
    private ImageView imgMine;
    private TextView tvMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Navigation();
        String Main = getIntent().getStringExtra("Main");
        if (Main != null && !Main.isEmpty()) {
            llOrder.callOnClick();
        } else {
            llHome.callOnClick();//默认点击首页
        }

    }

    // 点击控件进行页面转换
    private void Navigation() {
        llHome.setOnClickListener(v -> {
            setFragment(0);
            imgHome.setImageResource(R.drawable.homed);
            tvHome.setTextColor(Color.parseColor("#F03D37"));
            imgOrder.setImageResource(R.drawable.order);
            tvOrder.setTextColor(Color.parseColor("#bfbfbf"));
            imgMine.setImageResource(R.drawable.mine);
            tvMine.setTextColor(Color.parseColor("#bfbfbf"));
        });

        llOrder.setOnClickListener(v -> {
            setFragment(1);
            imgHome.setImageResource(R.drawable.home);
            tvHome.setTextColor(Color.parseColor("#bfbfbf"));
            imgOrder.setImageResource(R.drawable.orderd);
            tvOrder.setTextColor(Color.parseColor("#F03D37"));
            imgMine.setImageResource(R.drawable.mine);
            tvMine.setTextColor(Color.parseColor("#bfbfbf"));
        });

        llMine.setOnClickListener(v -> {
            setFragment(2);
            imgHome.setImageResource(R.drawable.home);
            tvHome.setTextColor(Color.parseColor("#bfbfbf"));
            imgOrder.setImageResource(R.drawable.order);
            tvOrder.setTextColor(Color.parseColor("#bfbfbf"));
            imgMine.setImageResource(R.drawable.mined);
            tvMine.setTextColor(Color.parseColor("#F03D37"));
        });

    }

    //切换fg页面
    private void setFragment(int id) {
        Fragment fragment = null;
        switch (id) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new OrderFragment();
                break;
            case 2:
                fragment = new MineFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fr, fragment).commit();

    }


    private void initView() {
        llHome = findViewById(R.id.ll_home);
        imgHome = findViewById(R.id.img_home);
        tvHome = findViewById(R.id.tv_home);
        fr = findViewById(R.id.fr);
        linearLayout = findViewById(R.id.linearLayout);
        view8 = findViewById(R.id.view8);
        llOrder = findViewById(R.id.ll_order);
        imgOrder = findViewById(R.id.img_order);
        tvOrder = findViewById(R.id.tv_order);
        llMine = findViewById(R.id.ll_mine);
        imgMine = findViewById(R.id.img_mine);
        tvMine = findViewById(R.id.tv_mine);
    }
}
