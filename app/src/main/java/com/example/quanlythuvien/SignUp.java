package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {
    EditText edtUserName, edtConfirmPassword, edtPassWord;
    Button btnCancel, btnSignUp;
    List<String> data = new ArrayList<>();
    ArrayAdapter adapter;
    DB_Users db_users;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signip);
        setControl();
        setEvent();
    }

    private void setEvent() {
        db_users = new DB_Users(this);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra điều kiện khi nhập thông tin
                if (edtUserName.getText().length() <= 0) {
                    edtUserName.setError("Nhập username !!!");
                    Toast.makeText(SignUp.this, "Chưa nhập username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtPassWord.getText().length() <= 0) {
                    edtPassWord.setError("Nhập password !!!");
                    Toast.makeText(SignUp.this, "Chưa nhập password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtConfirmPassword.getText().length() <= 0) {
                    edtConfirmPassword.setError("Xác nhận password !!!");
                    Toast.makeText(SignUp.this, "Chưa xác nhận password", Toast.LENGTH_SHORT).show();
                    return;
                }
                String confirmPassword = edtConfirmPassword.getText().toString();
                String passWord = edtPassWord.getText().toString();
                // Mật khẩu và xác nhận mật khẩu khớp nhau
                if (passWord.equals(confirmPassword)) {
                    // Kiểm tra tên người dùng và mật khẩu
                    User user = new User();
                    user.setUsername(edtUserName.getText().toString());
                    user.setPassword(edtPassWord.getText().toString());
                    // Lưu thông tin người dùng
                    db_users.ThemDl(user);
                    NguoiDung.data_u.clear();
                    NguoiDung.data_u.addAll(db_users.DocDL());
                    adapter.notifyDataSetChanged();
                    // Đăng ký thành công
                    Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                } else {
                    // Mật khẩu và xác nhận mật khẩu không khớp
                    Toast.makeText(SignUp.this, "Mật khẩu không khớp. Vui lòng thử lại", Toast.LENGTH_SHORT).show();
                }

//                 Kiểm tra thông tin người dùng có tồn tại không
//                    if (!edtUserName.equals(edtConfirmPassword)) {
//                        Toast.makeText(SignUp.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
//                    } else {
//
//
//
//                    }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay lại màn hình đăng nhập
//                Intent intent = new Intent(SignUp.this , Login.class);
//                startActivity(intent);
                onBackPressed();
            }
        });
    }



    private void setControl() {
        edtUserName = findViewById(R.id.edtUserName);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtPassWord = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnCancel = findViewById(R.id.btnCancel);
    }
}
