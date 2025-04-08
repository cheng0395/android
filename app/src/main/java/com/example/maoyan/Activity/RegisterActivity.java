package com.example.maoyan.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maoyan.Data.DatabaseHelper;
import com.example.maoyan.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private DatabaseHelper mDatabaseHelper;
    private EditText repeat;
    private TextView tvLogin;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        button();
        login();
    }

    // 返回到登陆页面
    private void login() {
        tvLogin.setOnClickListener(v -> {
            finish();
        });
    }

    private void button() {
        // 点击注册按钮进行验证
        registerButton.setOnClickListener(v -> {
            // 获取三个输入框的内容
            String username = mUserNameEditText.getText().toString().trim();
            String password = mPasswordEditText.getText().toString().trim();
            String passwordrepeat = repeat.getText().toString().trim();
            // 判断是否输入内容
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "请输入账号或密码", Toast.LENGTH_SHORT).show();
                return;
            }
            // 判断两次密码是否一致
            if (passwordrepeat.equals(password) && password.equals(passwordrepeat)) {
                boolean result = mDatabaseHelper.insertData(username, password);
                if (result) {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "两次密码不同,请检查！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mUserNameEditText = findViewById(R.id.username_edittext);
        mPasswordEditText = findViewById(R.id.password_edittext);
        mDatabaseHelper = new DatabaseHelper(this);
        repeat = (EditText) findViewById(R.id.repeat);
        tvLogin = (TextView) findViewById(R.id.tv_login);
        registerButton = findViewById(R.id.rEgisterTv);
    }
}