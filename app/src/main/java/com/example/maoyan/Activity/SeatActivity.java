package com.example.maoyan.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maoyan.Bean.SeatBean;
import com.example.maoyan.Data.SeatHelper;
import com.example.maoyan.R;

import java.util.List;

public class SeatActivity extends AppCompatActivity implements View.OnClickListener {

    private String hot;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private ImageView img9;
    private ImageView img10;
    private ImageView img11;
    private ImageView img12;
    private ImageView img13;
    private ImageView img14;
    private ImageView img15;
    private ImageView img16;
    private ImageView img17;
    private ImageView img18;


    private ImageView[] seatImageViews;
    private boolean[] seatStatus;

    private boolean isBuying; // 是否正在购买，用于控制点击事件
    private String username;
    private String movieName;
    private String img;
    private String moviePrice;
    private SeatHelper seatHelper;
    private ImageView imgBack;
    private TextView tvMovieName;
    private RadioGroup rgPay;
    private RadioButton rbYinlian;
    private RadioButton rbZhifubao;
    private RadioButton rbWeixin;
    private TextView tvPrice;
    private Button btnBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);
        initView();
        show();
    }

    private void show() {
        // 显示座位信息
        if (movieName != null) {
            // 设置单票价
            tvPrice.setText("单票价：" + moviePrice + " ￥");
            tvMovieName.setText(movieName);
            // 获取电影对应的所有座位信息
            List<SeatBean> allSeatsByMovie = seatHelper.getAllSeatsByMovie(movieName);
            if (allSeatsByMovie != null) {
                // 设置座位图片背景
                setSeatImagesBackground(allSeatsByMovie);
                // 设置座位点击事件
                setSeatClickListeners();
            } else {
                // 处理无法获取座位信息的情况
                Toast.makeText(this, "无法获取座位信息", Toast.LENGTH_SHORT).show();
            }
        } else {
            // 处理 movieName 为 null 的情况
            Toast.makeText(this, "电影名称为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSeatClickListeners() {
        // 设置座位点击事件监听器
        for (int i = 0; i < seatImageViews.length; i++) {
            int seatIndex = i;
            seatImageViews[seatIndex].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 处理座位点击事件
                    if (!isBuying) { // 如果不是在购买状态下，才能选择座位
                        if (seatIndex >= 0 && seatIndex < seatStatus.length && seatStatus[seatIndex]) {
                            // 取消选择座位
                            seatImageViews[seatIndex].setImageResource(R.drawable.wxzw);
                            seatStatus[seatIndex] = false;
                        } else {
                            // 选择座位
                            seatImageViews[seatIndex].setImageResource(R.drawable.yxzw);
                            seatStatus[seatIndex] = true;
                        }
                    }
                }
            });

            // 如果座位已售出，则禁用点击事件
            if (seatStatus[seatIndex]) {
                seatImageViews[seatIndex].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 提示该座位已售出
                        Toast.makeText(SeatActivity.this, "该座位已售出,请选择其他座位！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    // 根据查询到的座位信息设置图片背景
    private void setSeatImagesBackground(List<SeatBean> seats) {
        // 设置座位图片背景
        boolean anySeatAvailable = false; // 假设没有任何座位可用
        for (SeatBean seat : seats) {
            int seatIndex = seat.getSeatNumber(); // 座位索引
            if (seatIndex >= 0 && seatIndex < seatImageViews.length) {
                // 确保座位索引在有效范围内
                if (seat.isSelected()) {
                    // 已售出的座位显示已售罄状态
                    seatImageViews[seatIndex].setImageResource(R.drawable.zxzy);
                } else {
                    // 未售出的座位显示空闲状态
                    seatImageViews[seatIndex].setImageResource(R.drawable.wxzw);
                    anySeatAvailable = true; // 只要有一个座位可用，则不是所有座位都售出
                }
                seatStatus[seatIndex] = seat.isSelected();
            }
        }
    }

    // 初始化视图
    private void initView() {
        seatHelper = new SeatHelper(this);
        // 初始化座位图像视图
        seatImageViews = new ImageView[18]; // 根据实际座位数量初始化
        seatImageViews[0] = findViewById(R.id.img1);
        seatImageViews[1] = findViewById(R.id.img2);
        seatImageViews[2] = findViewById(R.id.img3);
        seatImageViews[3] = findViewById(R.id.img4);
        seatImageViews[4] = findViewById(R.id.img5);
        seatImageViews[5] = findViewById(R.id.img6);
        seatImageViews[6] = findViewById(R.id.img7);
        seatImageViews[7] = findViewById(R.id.img8);
        seatImageViews[8] = findViewById(R.id.img9);
        seatImageViews[9] = findViewById(R.id.img10);
        seatImageViews[10] = findViewById(R.id.img11);
        seatImageViews[11] = findViewById(R.id.img12);
        seatImageViews[12] = findViewById(R.id.img13);
        seatImageViews[13] = findViewById(R.id.img14);
        seatImageViews[14] = findViewById(R.id.img15);
        seatImageViews[15] = findViewById(R.id.img16);
        seatImageViews[16] = findViewById(R.id.img17);
        seatImageViews[17] = findViewById(R.id.img18);

        seatStatus = new boolean[18]; // 初始化状态数组

        movieName = getIntent().getStringExtra("movieName");
        moviePrice = getIntent().getStringExtra("price");
        img = getIntent().getStringExtra("imgUrl");

        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        imgBack = (ImageView) findViewById(R.id.img_back);
        tvMovieName = (TextView) findViewById(R.id.tv_movieName);
        rgPay = (RadioGroup) findViewById(R.id.rg_pay);
        rbYinlian = (RadioButton) findViewById(R.id.rb_yinlian);
        rbZhifubao = (RadioButton) findViewById(R.id.rb_zhifubao);
        rbWeixin = (RadioButton) findViewById(R.id.rb_weixin);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(this);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 处理按钮点击事件
        if (v.getId() == R.id.btn_buy) {
            // 购买操作
            isBuying = true;
            boolean purchaseSuccess = false; // 标记购买是否成功
            for (int i = 0; i < seatImageViews.length; i++) {
                if (seatStatus[i]) {
                    boolean seatExists = seatHelper.checkSeatExistence(movieName, i);
                    if (!seatExists) {
                        boolean success = seatHelper.addSeat(img, movieName, username, i, true, moviePrice);
                        if (success) {
                            seatImageViews[i].setImageResource(R.drawable.zxzy);
                            purchaseSuccess = true;
                        }
                    }
                }
            }

            if (purchaseSuccess) {
                // 购买成功提示
                Toast.makeText(SeatActivity.this, "购买成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SeatActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // 购买失败提示
                Toast.makeText(SeatActivity.this, "购买失败,请选择座位！", Toast.LENGTH_SHORT).show();
                Intent refreshIntent = getIntent();
                finish();
                startActivity(refreshIntent);
            }
        } else if (v.getId() == R.id.img_back) {
            finish();
        }
    }
}