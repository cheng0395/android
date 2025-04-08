package com.example.maoyan.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.maoyan.Activity.ToBeShownDetailActivity;
import com.example.maoyan.Bean.Hot;
import com.example.maoyan.Bean.ToBeShown;
import com.example.maoyan.R;

import java.util.List;

public class ToBeShownAdapter extends RecyclerView.Adapter<ToBeShownAdapter.ViewHolder> {

    private List<ToBeShown> toBeShownList;
    private static List<Hot> hotList;

    public ToBeShownAdapter(List<ToBeShown> toBeShownList) {
        this.toBeShownList = toBeShownList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;
        private Button btn;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.nm);
            img = view.findViewById(R.id.img);
            btn = view.findViewById(R.id.content);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tobeshown_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ToBeShown toBeShown = toBeShownList.get(position);
        holder.title.setText(toBeShown.getNm());
        holder.btn.setText(toBeShown.getShowStateButton().getContent());
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(10)); // 指定圆角半径
        Glide.with(holder.img.getContext())
                .load(toBeShown.getImg())
                .apply(requestOptions) // 应用圆角转换
                .into(holder.img);

        // 点击列表项的按钮进行跳转
        holder.btn.setOnClickListener(v -> {
            // 将当前选中的 Hot 对象转换为 JSON 字符串
            String toJson = toBeShown.toJson();
            String type = toBeShown.getShowStateButton().getContent();
            // 创建 Intent 并添加额外参数
            Intent intent = new Intent(holder.itemView.getContext(), ToBeShownDetailActivity.class);
            intent.putExtra("ToBeShown", toJson);
            intent.putExtra("type", type);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return toBeShownList.size();
    }

}