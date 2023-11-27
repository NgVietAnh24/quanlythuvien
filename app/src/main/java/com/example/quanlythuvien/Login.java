package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText edtUserName, edtPassWord;
    CheckBox checkBoxShowPassword;
    Button btnLogin, btnSignUp;
    DB_Users db_users;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setEvent();
    }

    private void setEvent() {
        db_users = new DB_Users(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString();
                String password = edtPassWord.getText().toString();
                if (db_users.isUserExists(username, password)) {
                    // Đăng nhập thành công
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển sang màn hình chính
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(Login.this, "Tên người dùng hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình đăng ký
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
        checkBoxShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Nếu CheckBox được chọn, hiển thị mật khẩu; ngược lại, ẩn mật khẩu
                if (isChecked) {
                    edtPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    edtPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    // ánh xạ
    private void setControl() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        checkBoxShowPassword = findViewById(R.id.checkBoxShowPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}

