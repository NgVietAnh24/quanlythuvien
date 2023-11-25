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

public class MainActivity extends AppCompatActivity {

    ImageButton ibtnUser, ibtnMuonTra, ibtnSach, ibtnThe, ibtnThongKe, ibtnTimSach;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ibtnMuonTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình mượn sách - trả sách
                Intent intent = new Intent(MainActivity.this, Screen_Muon_Tra.class);
                startActivity(intent);
            }
        });
        ibtnSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình nhập sách
                Intent intent = new Intent(MainActivity.this, Nhap_Sach.class);
                startActivity(intent);
            }
        });
        ibtnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang màn hình người dùng
                Intent intent = new Intent(MainActivity.this, NguoiDung.class);
                startActivity(intent);
            }
        });
        ibtnTimSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang màn hình tìm sách
                Intent intent = new Intent(MainActivity.this, TimSach.class);
                startActivity(intent);
            }
        });
        ibtnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang màn hình thống kê
                Intent intent = new Intent(MainActivity.this, ThongKe.class);
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
        if(item.getItemId() == R.id.mnBack)
        {
            onBackPressed();
        }
        if(item.getItemId() == R.id.mnThoat)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
