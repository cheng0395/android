package com.example.maoyan.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.maoyan.Activity.LoginActivity;
import com.example.maoyan.Activity.MainActivity;
import com.example.maoyan.Activity.ReserveActivity;
import com.example.maoyan.BuildConfig;
import com.example.maoyan.Data.DatabaseHelper;
import com.example.maoyan.R;

public class MineFragment extends Fragment {

    private LinearLayout llPassword;
    private LinearLayout llOrder;
    private LinearLayout llReserve;
    private LinearLayout llVersions;
    private LinearLayout llQuit;
    private String username;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(v);
        ll();
        return v;
    }

    private void ll() {
        // 设置点击监听器，处理密码修改逻辑
        llPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // 加载dialog_password.xml布局文件，并设置为对话框的视图
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_password, null);
                builder.setView(dialogView);

                // 在对话框视图中获取控件
                TextView usernameTextView = dialogView.findViewById(R.id.usernameTextView);
                EditText oldPasswordEditText = dialogView.findViewById(R.id.oldPasswordEditText);
                EditText newPasswordEditText = dialogView.findViewById(R.id.newPasswordEditText);

                // 设置用户名
                usernameTextView.setText("用户名：" + username);

                // 设置确定按钮的点击事件
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取输入的旧密码和新密码
                        String oldPassword = oldPasswordEditText.getText().toString();
                        String newPassword = newPasswordEditText.getText().toString();
                        // 检查旧密码是否正确
                        boolean isOldPasswordCorrect = databaseHelper.checkUser(username, oldPassword);
                        if (isOldPasswordCorrect) {
                            // 更新密码
                            boolean isUpdateSuccessful = databaseHelper.updatePasswordByUsername(username, newPassword);
                            if (isUpdateSuccessful) {
                                Toast.makeText(getActivity(), "修改成功！", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                // 跳转到登录页面
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                                getActivity().finish(); // 结束当前Activity
                            } else {
                                Toast.makeText(getActivity(), "修改失败！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "旧密码错误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // 设置取消按钮的点击事件
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 设置点击监听器，处理订单页面跳转逻辑
        llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到MainActivity，并传递参数"order"
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("Main", "order");
                startActivity(intent);
                getActivity().finish();
            }
        });

        // 设置点击监听器，处理预定页面跳转逻辑
        llReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到ReserveActivity
                Intent intent = new Intent(getActivity(), ReserveActivity.class);
                startActivity(intent);
            }
        });

        // 设置点击监听器，显示当前软件版本
        llVersions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取应用版本号并显示
                String appVersion = BuildConfig.VERSION_NAME;
                Toast.makeText(getActivity(), "当前软件版本：" + appVersion, Toast.LENGTH_SHORT).show();
            }
        });

        // 设置点击监听器，处理退出应用逻辑
        llQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建确认退出的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("确认退出");
                builder.setMessage("您确定要退出应用吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 处理点击确定后的操作，例如返回到LoginActivity
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish(); // 结束当前Activity
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消时不执行任何操作，只是关闭弹窗
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    private void initView(View v) {
        llPassword = v.findViewById(R.id.ll_password);
        llOrder = v.findViewById(R.id.ll_order);
        llReserve = v.findViewById(R.id.ll_reserve);
        llVersions = v.findViewById(R.id.ll_versions);
        llQuit = v.findViewById(R.id.ll_quit);
        databaseHelper = new DatabaseHelper(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

    }
}