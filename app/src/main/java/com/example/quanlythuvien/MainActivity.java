package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnUser, ibtnMuonTra, ibtnSach, ibtnThe, ibtnThongKe, ibtnTimSach;
    DB_Users db_users;
    List<User> data = new ArrayList<>();
    AdapterUser adapterUser;
    DB_Sach dbSach;
    DB_TraSach dbTraSach;
    DB_TheThuVien db_theThuVien;
    DB_LichSuTS dbLichSuTS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbLichSuTS = new DB_LichSuTS(this);
        db_theThuVien = new DB_TheThuVien(this);
        dbSach = new DB_Sach(this);
        db_users = new DB_Users(this);
        dbTraSach = new DB_TraSach(this);
        adapterUser = new AdapterUser(this, R.layout.activity_item_users, data);
        ibtnMuonTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đọc dữ liệu của danh sách mượn trả
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.DocDL());
                // Đọc dữ liệu của sách
                Danh_Sach_Of_Sach.list.clear();
                Danh_Sach_Of_Sach.list.addAll(dbSach.DocDL());
                // Đọc dữ liệu của người dùng
                NguoiDung.data_u.clear();
                NguoiDung.data_u.addAll(db_users.DocDL());
                // Đọc dữ liệu của thẻ thư viện
                DanhSachThe.data_t.clear();
                DanhSachThe.data_t.addAll(db_theThuVien.DocDL());
                // Chuyển sang màn hình mượn sách - trả sách
                Intent intent = new Intent(MainActivity.this, Danh_Sach_Tra.class);
                startActivity(intent);


            }
        });
        ibtnSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đọc dữ liệu của sách
                Danh_Sach_Of_Sach.list.clear();
                Danh_Sach_Of_Sach.list.addAll(dbSach.DocDL());
                // Chuyển sang màn hình nhập sách
                Intent intent = new Intent(MainActivity.this, Danh_Sach_Of_Sach.class);
                startActivity(intent);
            }
        });
        ibtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang màn hình người dùng
                Intent intent = new Intent(MainActivity.this, NguoiDung.class);
                startActivity(intent);
                // Đọc dữ liệu của người dùng
                NguoiDung.data_u.clear();
                NguoiDung.data_u.addAll(db_users.DocDL());
                adapterUser.notifyDataSetChanged();
            }
        });
        ibtnTimSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang màn hình tìm sách
                Intent intent = new Intent(MainActivity.this, TimSach.class);
                startActivity(intent);
                // Đọc dữ liệu của sách
                Danh_Sach_Of_Sach.list.clear();
                Danh_Sach_Of_Sach.list.addAll(dbSach.DocDL());
            }
        });
        ibtnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Đọc dữ liệu lịch sử trả sách
                LichSuTS.list.clear();
                LichSuTS.list.addAll(dbLichSuTS.DocDL());
                // Đọc dữ liệu của danh sách mượn trả
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.DocDL());
                // Đọc dữ liệu của sách
                Danh_Sach_Of_Sach.list.clear();
                Danh_Sach_Of_Sach.list.addAll(dbSach.DocDL());
                // Đọc dữ liệu của người dùng
                NguoiDung.data_u.clear();
                NguoiDung.data_u.addAll(db_users.DocDL());
                // Đọc dữ liệu của thẻ thư viện
                DanhSachThe.data_t.clear();
                DanhSachThe.data_t.addAll(db_theThuVien.DocDL());
                // Chuyển sang màn hình thống kê
                Intent intent = new Intent(MainActivity.this, ThongKe.class);
                startActivity(intent);
            }
        });
        ibtnThe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Chuyển màn hình thẻ thu viện
                Intent intent = new Intent(MainActivity.this, TheThuVien.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        ibtnUser = findViewById(R.id.ibtnUser);
        ibtnMuonTra = findViewById(R.id.ibtnMuonTra);
        ibtnSach = findViewById(R.id.ibtnSach);
        ibtnThe = findViewById(R.id.ibtnThe);
        ibtnThongKe = findViewById(R.id.ibtnThongKe);
        ibtnTimSach = findViewById(R.id.ibtnTimSach);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnDangXuat)
        {

            Notify_Exiyt.showLogoutConfirmationDialog(this, new Notify_Exiyt.OnLogoutConfirmedListener() {
                @Override
                public void onLogoutConfirmed() {
                    // Người dùng chọn "Có", thực hiện đăng xuất ở đây
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
