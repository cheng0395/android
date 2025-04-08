package com.example.maoyan.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.maoyan.Bean.Hot;
import com.example.maoyan.Data.ReserveHelper;
import com.example.maoyan.R;
import com.google.gson.Gson;

public class HotDetailActivity extends AppCompatActivity {
    private ImageView back;
    private ImageView img;
    private TextView nm;
    private TextView scm;
    private TextView cat;
    private TextView desc;
    private TextView pubDesc;
    private TextView ver;
    private TextView scoreLabel;
    private TextView wish;
    private TextView sc;
    private TextView snum;
    private TextView boxInfo;
    private TextView showInfo;
    private TextView videoName;
    private VideoView videourl;
    private ProgressDialog progressDialog;
    private String type;
    private ReserveHelper reserveHelper;
    private String username;
    private String movieName;
    private String time;
    private TextView textView4;
    private View view4;
    private Button btnBuy;
    private ScrollView scrollView2;
    private View view3;
    private ImageView imageView6;
    private TextView textView14;
    private RelativeLayout relativeLayout;
    private ProgressBar progressBar;
    private String price;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_detail);
        initView();
        content();
        back();
        Buy();

    }

    private void Buy() {
        // 设置按钮点击监听器
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Toast.makeText(HotDetailActivity.this, "您已经预约过了！", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // 如果用户未预约过，则添加预约信息
                        boolean reserve = reserveHelper.addReservation(username, "预售", movieName, time);
                        if (reserve) {
                            // 如果预约成功，显示成功信息
                            Toast.makeText(HotDetailActivity.this, "预约成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            // 如果预约失败，显示失败信息
                            Toast.makeText(HotDetailActivity.this, "预约失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // 如果电影类型不是"预售"，跳转到选座页面
                    Intent intent = new Intent(HotDetailActivity.this, SeatActivity.class);
                    intent.putExtra("imgUrl", imgUrl);
                    intent.putExtra("movieName", movieName);
                    intent.putExtra("price", price);
                    startActivity(intent);
                }
            }
        });
    }

    private void content() {
        // 获取传递的 JSON 字符串
        Intent intent = getIntent();
        String hotJson = intent.getStringExtra("Hot");
        // 使用 Gson 将 JSON 字符串转换为 Hot 对象
        Gson gson = new Gson();
        Hot hot = gson.fromJson(hotJson, Hot.class);

        imgUrl = hot.getImg();
        // 使用加载图像库（如Glide）加载图片
        Glide.with(this).load(imgUrl).into(img);

        movieName = hot.getNm();
        time = hot.getShowInfo();
        nm.setText(movieName);
        if (hot.getScm().isEmpty()) {
            scm.setVisibility(View.GONE);
        } else {
            scm.setVisibility(View.VISIBLE);
            scm.setText(hot.getScm());
        }
        cat.setText(hot.getCat());
        desc.setText(hot.getDesc());
        pubDesc.setText(hot.getPubDesc());
        ver.setText(hot.getVer());
        scoreLabel.setText(hot.getScoreLabel());
        wish.setText(hot.getWish());
        sc.setText(hot.getSc());
        snum.setText(hot.getSnum() + " 人评");
        boxInfo.setText(hot.getBoxInfo());
        showInfo.setText(hot.getShowInfo());
        videoName.setText(hot.getVideoName());
        pubDesc.setText(hot.getPubDesc());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("视频加载中...");
        progressDialog.setCancelable(false);
        playVideo(hot.getVideourl());
        price = hot.getPrice();
        if (type.equals("预售")) {
            btnBuy.setText("立即预约");
        } else {
            btnBuy.setText("票价：" + price + "￥，特惠购票");
        }

    }

    private void back() {
        back.setOnClickListener(v -> {
            finish();
        });
    }

    private void playVideo(String videoUrl) {
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        // 设置进度条可见
        progressBar.setVisibility(View.VISIBLE);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videourl);
        videourl.setMediaController(mediaController);
        videourl.setVideoURI(Uri.parse(videoUrl));
        videourl.requestFocus();
        videourl.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // 视频准备就绪后，隐藏进度条
                progressBar.setVisibility(View.GONE);
                videourl.start();
            }
        });
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        img = (ImageView) findViewById(R.id.img);
        nm = (TextView) findViewById(R.id.nm);
        scm = (TextView) findViewById(R.id.scm);
        cat = (TextView) findViewById(R.id.cat);
        desc = (TextView) findViewById(R.id.desc);
        pubDesc = (TextView) findViewById(R.id.pubDesc);
        ver = (TextView) findViewById(R.id.ver);
        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        wish = (TextView) findViewById(R.id.wish);
        sc = (TextView) findViewById(R.id.sc);
        snum = (TextView) findViewById(R.id.snum);
        boxInfo = (TextView) findViewById(R.id.boxInfo);
        showInfo = (TextView) findViewById(R.id.showInfo);
        videoName = (TextView) findViewById(R.id.videoName);
        videourl = (VideoView) findViewById(R.id.videourl);
        reserveHelper = new ReserveHelper(this);
        type = getIntent().getStringExtra("type");
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        textView4 = findViewById(R.id.textView4);
        view4 = findViewById(R.id.view4);
        btnBuy = findViewById(R.id.btn_buy);
        scrollView2 = findViewById(R.id.scrollView2);
        view3 = findViewById(R.id.view3);
        imageView6 = findViewById(R.id.imageView6);
        textView14 = findViewById(R.id.textView14);
        relativeLayout = findViewById(R.id.relativeLayout);
        progressBar = findViewById(R.id.progressBar);
    }
}