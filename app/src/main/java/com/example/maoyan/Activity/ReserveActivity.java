package com.example.maoyan.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maoyan.Adapter.ReserveAdapter;
import com.example.maoyan.Bean.ReserveBean;
import com.example.maoyan.Data.ReserveHelper;
import com.example.maoyan.R;

import java.util.Collections;
import java.util.List;

public class ReserveActivity extends AppCompatActivity {

    private View view15;
    private ImageView imgBack;
    private TextView textView20;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        initView();
        back();
        show();
    }

    private void back() {
        imgBack.setOnClickListener(v -> {
                finish();
        });
    }

    private void show() {
        // 获取数据库中的预订信息
        ReserveHelper reserveHelper = new ReserveHelper(this);
        List<ReserveBean> reserveBeanList = reserveHelper.getAllReservations();

        // 对列表进行倒序处理
        Collections.reverse(reserveBeanList);

        // 设置适配器
        ReserveAdapter adapter = new ReserveAdapter(this, reserveBeanList);
        lv.setAdapter(adapter);
    }

    private void initView() {
        view15 = findViewById(R.id.view15);
        imgBack = findViewById(R.id.img_back);
        textView20 = findViewById(R.id.textView20);
        lv = findViewById(R.id.lv);
    }
}