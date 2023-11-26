package com.example.quanlythuvien;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText edtUserName, edtPassWord;
    CheckBox ckRemember;
    Button btnLogin, btnSignUp;
    SQLiteDatabase sqLiteDatabase;
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

    }
//    private boolean validateInput(String username, String password) {
//        // Kiểm tra xem các trường nhập liệu có rỗng không
//        if (username.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Vui lòng nhập tên người dùng và mật khẩu", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

//    private boolean checkUser(String username, String password) {
//        // Kiểm tra xem người dùng có tồn tại trong cơ sở dữ liệu không
//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
//        int count = cursor.getCount();
//        cursor.close();
//
//        return count > 0;
//    }
    // ánh xạ
    private void setControl() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassWord = findViewById(R.id.edtPassWord);
        ckRemember = findViewById(R.id.ckRemember);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}

