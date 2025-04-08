package com.example.maoyan.Fragment;

import android.content.Context;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maoyan.Adapter.HotAdapter;
import com.example.maoyan.Adapter.ToBeShownAdapter;
import com.example.maoyan.Bean.Hot;
import com.example.maoyan.Bean.ToBeShown;
import com.example.maoyan.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView future, hot; // 未来影视、热门影视列表的RecyclerView
    private ToBeShownAdapter toBeShownAdapter; // 未来影视适配器
    private HotAdapter hotAdapter; // 热门影视适配器
    private List<ToBeShown> toBeShownList; // 未来影视列表
    private List<Hot> hotList; // 热门影视列表

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        banner(v); // 设置Banner
        daiying(v); // 初始化未来影视列表
        reying(v); // 初始化热门影视列表
        return v;
    }

    // 初始化热门影视列表
    private void reying(View v) {
        hot = v.findViewById(R.id.rv_hot); // 获取热门影视列表的RecyclerView
        hot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)); // 设置RecyclerView的布局管理器为水平线性布局
        hotList = new ArrayList<>(); // 初始化热门影视列表
        String jsonData = loadHotJSONFromAsset(); // 从JSON文件中加载数据
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Hot>>() {
        }.getType();
        hotList = gson.fromJson(jsonData, listType); // 使用Gson解析JSON数据为热门影视列表
        hotAdapter = new HotAdapter(hotList); // 创建热门影视适配器
        hot.setAdapter(hotAdapter); // 绑定数据到RecyclerView
    }

    // 初始化未来影视列表
    private void daiying(View v) {
        future = v.findViewById(R.id.rv_future); // 获取未来影视列表的RecyclerView
        future.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)); // 设置RecyclerView的布局管理器为水平线性布局
        toBeShownList = new ArrayList<>(); // 初始化未来影视列表
        String jsonData = loadJSONFromAsset(); // 从JSON文件中加载数据
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ToBeShown>>() {
        }.getType();
        toBeShownList = gson.fromJson(jsonData, listType); // 使用Gson解析JSON数据为未来影视列表
        toBeShownAdapter = new ToBeShownAdapter(toBeShownList); // 创建未来影视适配器
        future.setAdapter(toBeShownAdapter); // 绑定数据到RecyclerView
    }

    // 设置Banner
    private void banner(View v) {
        Banner banner = v.findViewById(R.id.banner); // 获取Banner控件
        List list = new ArrayList();
        list.add(R.drawable.banner01);
        list.add(R.drawable.banner02);
        list.add(R.drawable.banner03);
        banner.setImages(list); // 设置Banner图片列表
        banner.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 20); // 设置Banner圆角
            }
        });
        banner.setClipToOutline(true); // 开启裁剪
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path); // 显示Banner图片
            }
        });
        banner.start(); // 启动Banner
    }

    // 从assets文件夹中读取JSON文件
    private String loadJSONFromAsset() {
        String json;
        try {
            // 打开 film.json 文件的输入流
            InputStream is = getActivity().getAssets().open("film.json");
            // 获取文件字节数组的长度
            int size = is.available();
            // 创建一个与文件字节数组长度相同的 buffer 数组
            byte[] buffer = new byte[size];
            // 将文件内容读入 buffer 数组
            is.read(buffer);
            // 关闭输入流
            is.close();
            // 将 buffer 数组转换为 UTF-8 编码的字符串
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            // 处理读取文件发生的异常
            ex.printStackTrace();
            return null;
        }
        // 返回读取到的 JSON 字符串内容
        return json;
    }

    // 读取 hot.json 文件的方法
    private String loadHotJSONFromAsset() {
        String json;
        try {
            // 打开 hot.json 文件的输入流
            InputStream is = getActivity().getAssets().open("hot.json");
            // 获取文件字节数组的长度
            int size = is.available();
            // 创建一个与文件字节数组长度相等的 buffer 数组
            byte[] buffer = new byte[size];
            // 将文件内容读入 buffer 数组
            is.read(buffer);
            // 关闭输入流
            is.close();
            // 将 buffer 数组转换为 UTF-8 编码的字符串
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            // 处理读取文件发生的异常
            ex.printStackTrace();
            return null;
        }
        // 返回读取到的 hot.json 的 JSON 字符串内容
        return json;
    }
}