package com.example.maoyan.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.maoyan.Bean.ReserveBean;
import com.example.maoyan.Data.ReserveHelper;
import com.example.maoyan.R;

import java.util.List;

public class ReserveAdapter extends ArrayAdapter<ReserveBean> {

    private Context mContext;
    private List<ReserveBean> mReservationList;
    private String username;
    private ReserveHelper reserveHelper;

    public ReserveAdapter(@NonNull Context context, @NonNull List<ReserveBean> reservations) {
        super(context, 0, reservations);
        mContext = context;
        mReservationList = reservations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_reserve, parent, false);
        }

        ReserveBean currentReservation = mReservationList.get(position);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");
        reserveHelper = new ReserveHelper(mContext);


        TextView movieName = listItem.findViewById(R.id.textView_movieName);
        movieName.setText(currentReservation.getMovieName());

        TextView time = listItem.findViewById(R.id.textView_time);
        time.setText(currentReservation.getTime());
        listItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // 获取当前电影名称
                String movieName = currentReservation.getMovieName();

                // 构建对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("确认删除")
                        .setMessage("是否确定删除预约电影：" + movieName + "？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 在确定按钮点击后删除电影预订
                                boolean b = reserveHelper.deleteReservation(username, movieName);
                                if (b) {
                                    Toast.makeText(getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                                    mReservationList.remove(currentReservation);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getContext(), "删除失败！", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", null) // 取消按钮不执行任何操作
                        .show();

                // 返回 true 表示消费了长按事件
                return true;
            }
        });
        return listItem;
    }
}