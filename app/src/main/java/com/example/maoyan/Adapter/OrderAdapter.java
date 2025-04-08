package com.example.maoyan.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.maoyan.Bean.SeatBean;
import com.example.maoyan.Data.DatabaseHelper;
import com.example.maoyan.Data.SeatHelper;
import com.example.maoyan.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<SeatBean> {

    private Context mContext; // 上下文对象
    private List<SeatBean> mSeatBeanList; // 座位列表
    private Handler mHandler; // 处理程序对象
    private final SeatHelper seatHelper; // 座位助手对象
    private String seat; // 座位信息
    private final DatabaseHelper databaseHelper; // 数据库助手对象

    public OrderAdapter(Context context, List<SeatBean> seatList) {
        super(context, 0, seatList);
        mContext = context;
        mSeatBeanList = seatList;
        seatHelper = new SeatHelper(context);
        databaseHelper = new DatabaseHelper(context);
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView; // 获取列表项视图
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_order, parent, false); // 加载订单项布局
        }

        final SeatBean currentSeatBean = mSeatBeanList.get(position); // 获取当前座位信息
        final ImageView imageView = listItem.findViewById(R.id.img); // 获取ImageView控件用于显示电影图片

        // 设置电影图片
        Glide.with(mContext)
                .load(currentSeatBean.getImg())
                .into(imageView); // 使用Glide加载并显示电影图片

        TextView nameTextView = listItem.findViewById(R.id.tv_movieName); // 电影名称TextView
        nameTextView.setText(currentSeatBean.getMovieName()); // 设置电影名称

        TextView movieReleaseTimeTextView = listItem.findViewById(R.id.tv_seat); // 座位信息TextView
        seat = String.valueOf(currentSeatBean.getSeatNumber() + 1); // 获取座位号并加1（从0开始）
        movieReleaseTimeTextView.setText(seat); // 设置座位信息

        TextView priceTextView = listItem.findViewById(R.id.tv_price); // 价格TextView
        priceTextView.setText(currentSeatBean.getTotalPrice() + " ￥"); // 设置总价

        Button btnQuit = listItem.findViewById(R.id.btn_quit); // 退票按钮

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理退票按钮点击事件
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext); // 创建对话框
                builder.setTitle("确认退票"); // 设置标题
                builder.setMessage("确定要退掉当前座位吗？"); // 设置消息
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 用户确认退票
                        boolean success = seatHelper.deleteDataByUsernameAndSeat(currentSeatBean.getUsername(), currentSeatBean.getSeatNumber());
                        if (success) {
                            Toast.makeText(mContext, "退票成功", Toast.LENGTH_SHORT).show(); // 显示退票成功提示
                            mSeatBeanList.remove(currentSeatBean); // 从列表中移除当前座位信息
                            notifyDataSetChanged(); // 刷新列表
                        } else {
                            Toast.makeText(mContext, "退票失败", Toast.LENGTH_SHORT).show(); // 显示退票失败提示
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 用户取消操作，不执行任何操作
                    }
                });
                AlertDialog dialog = builder.create(); // 创建对话框
                dialog.show(); // 显示对话框
            }
        });

        Button btnTransfer = listItem.findViewById(R.id.btn_transfer); // 转让按钮

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理转让按钮点击事件
                LayoutInflater inflater = LayoutInflater.from(mContext); // 获取布局加载器
                final View dialogView = inflater.inflate(R.layout.dialog_zhuanrang, null); // 加载转让对话框布局

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext); // 创建对话框
                builder.setView(dialogView); // 设置对话框视图
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText etUsername = dialogView.findViewById(R.id.et_username); // 获取输入的转让用户名
                        final String transferUsername = etUsername.getText().toString(); // 转让用户名
                        boolean userExists = databaseHelper.checkUserByUsername(transferUsername); // 检查用户是否存在
                        if (!userExists) {
                            Toast.makeText(mContext, "查无此人，请确认转让人！", Toast.LENGTH_SHORT).show(); // 提示用户不存在
                            return;
                        }
                        if (!transferUsername.isEmpty()) {
                            if (!transferUsername.equals(currentSeatBean.getUsername())) {
                                boolean updateSuccess = seatHelper.updateUsernameBySeat(transferUsername, currentSeatBean.getSeatNumber());
                                if (updateSuccess) {
                                    Toast.makeText(mContext, "转让成功！", Toast.LENGTH_SHORT).show(); // 提示转让成功
                                    mSeatBeanList.remove(currentSeatBean); // 从列表中移除当前座位信息
                                    notifyDataSetChanged(); // 刷新列表
                                } else {
                                    Toast.makeText(mContext, "转让失败！", Toast.LENGTH_SHORT).show(); // 提示转让失败
                                }
                            } else {
                                Toast.makeText(mContext, "请选择其他用户进行转让", Toast.LENGTH_SHORT).show(); // 提示选择其他用户
                            }
                        } else {
                            Toast.makeText(mContext, "请输入有效的转让人信息", Toast.LENGTH_SHORT).show(); // 提示输入有效信息
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 用户取消操作，不执行任何操作
                    }
                });

                AlertDialog dialog = builder.create(); // 创建对话框
                dialog.show(); // 显示对话框
            }
        });

        return listItem; // 返回列表项视图
    }
}
