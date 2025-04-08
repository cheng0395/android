package com.example.maoyan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maoyan.R;
import com.example.maoyan.Data.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton ;
    private TextView rEgisterTv;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化控件
        mUserNameEditText = findViewById(R.id.username_edittext);
        mPasswordEditText = findViewById(R.id.password_edittext);
        mLoginButton = findViewById(R.id.login_button);
        rEgisterTv = findViewById(R.id.rEgisterTv);

        // 实例化数据库帮助类
        mDatabaseHelper = new DatabaseHelper(this);

        // 注册点击事件，跳转到注册页面
        rEgisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 登录按钮点击事件
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入的用户名和密码
                String username = mUserNameEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();

                // 判断用户名和密码是否为空
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入账号或密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 检查用户输入的用户名和密码是否正确
                boolean result = mDatabaseHelper.checkUser(username, password);
                if (result) {
                    // 登录成功
                    Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // 账号或密码错误
                    Toast.makeText(getApplicationContext(), "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}