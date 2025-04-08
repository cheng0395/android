package com.example.maoyan.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.maoyan.Adapter.OrderAdapter;
import com.example.maoyan.Bean.SeatBean;
import com.example.maoyan.Data.SeatHelper;
import com.example.maoyan.R;

import java.util.Collections;
import java.util.List;

public class OrderFragment extends Fragment {

    private ListView lv;
    private SeatHelper seatHelper;
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        initView(v);
        show();
        return v;
    }

    private void show() {
        // 获取用户所有的座位信息
        List<SeatBean> allSeatsByUsername = seatHelper.getAllSeatsByUsername(username);
        if (allSeatsByUsername != null) {
            // 将数据倒序排列
            Collections.reverse(allSeatsByUsername);

            // 使用自定义适配器将数据显示在列表视图中
            OrderAdapter adapter = new OrderAdapter(getContext(), allSeatsByUsername);
            lv.setAdapter(adapter);
        }
    }

    private void initView(View v) {
        lv = v.findViewById(R.id.lv);
        seatHelper = new SeatHelper(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
    }
}