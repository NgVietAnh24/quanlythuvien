package com.example.quanlythuvien;

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

public class Edit_ND extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnSua, btnXoa, btnDanhSach;
    List<User> data_u = new ArrayList<>();
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter adapter_u;

    DB_Users db_users;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nd);
        setControl();
        setEvent();

    }

    private void setEvent() {
        db_users = new DB_Users(this);
        adapter_u = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data_ts);

        User user = (User) getIntent().getSerializableExtra("item");
        edtUsername.setText(user.getUsername());
        edtPassword.setText(user.getPassword());




        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               User user = new User();
                user.setUsername(edtUsername.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                db_users.SuaDl(user);
                Toast.makeText(Edit_ND.this, "Sửa thành công", Toast.LENGTH_SHORT).show();


            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUsername(edtUsername.getText().toString());
                NguoiDung.data_u.clear();
                NguoiDung.data_u.addAll(db_users.XoaDL(user));
                adapter_u.notifyDataSetChanged();
                Toast.makeText(Edit_ND.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                DanhSachTra.adapter_Sach.notifyDataSetChanged();
//                onBackPressed();
            }
        });


        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguoiDung.data_u.clear();
                NguoiDung.data_u.addAll(db_users.DocDL());
                adapter_u.notifyDataSetChanged();
                onBackPressed();
            }
        });
    }


    private void setControl() {

        edtUsername = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassWord);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnDanhSach = findViewById(R.id.btnDanhSach);
    }
}
