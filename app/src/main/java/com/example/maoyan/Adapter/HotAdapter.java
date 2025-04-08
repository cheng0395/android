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
import com.example.maoyan.Activity.HotDetailActivity;
import com.example.maoyan.Bean.Hot;
import com.example.maoyan.R;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private static List<Hot> hotList;

    public HotAdapter(List<Hot> hotList) {
        this.hotList = hotList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView img;
        private Button content;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.nm);
            img = view.findViewById(R.id.img);
            content = view.findViewById(R.id.button);
        }

    }

    @NonNull
    @Override
    public HotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item, parent, false);
        return new HotAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Hot hot = hotList.get(position);
        holder.title.setText(hot.getNm());
        holder.content.setText(hot.getShowStateButton().getContent());
        RequestOptions requestOptions = new RequestOptions()
                .transform(new RoundedCorners(10)); // 指定圆角半径
        Glide.with(holder.img.getContext())
                .load(hot.getImg())
                .apply(requestOptions) // 应用圆角转换
                .into(holder.img);

        // 点击列表项的按钮进行跳转
        holder.content.setOnClickListener(v -> {
            // 将当前选中的 Hot 对象转换为 JSON 字符串
            String hotJson = hot.toJson();
            String type = hot.getShowStateButton().getContent();
            // 创建 Intent 并添加额外参数
            Intent intent = new Intent(holder.itemView.getContext(), HotDetailActivity.class);
            intent.putExtra("Hot", hotJson);
            intent.putExtra("type", type);
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return hotList.size();
    }
}