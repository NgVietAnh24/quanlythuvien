package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TimSach extends AppCompatActivity {
    EditText edtTenSach;

    RecyclerView rcvThongTin;
    Button btnTim;
    DB_Sach dbSach;
    List<Sach> list = new ArrayList<>();
    AdapterSach adapterSach;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_sach);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbSach = new DB_Sach(this);
        adapterSach = new AdapterSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvThongTin.setLayoutManager(linearLayoutManager);

        adapterSach.setData(list,TimSach.this);
        rcvThongTin.setAdapter(adapterSach);
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Danh_Sach_Of_Sach.list != null) {
                    Sach sach = new Sach();
                    sach.setTenSach(edtTenSach.getText().toString());
                    list.clear();
                    list.addAll(dbSach.TimDLTheoTen(sach));
                    adapterSach.notifyDataSetChanged();
                    Toast.makeText(TimSach.this, "Tìm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(TimSach.this, "Không tìm thấy", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setControl() {
        edtTenSach = findViewById(R.id.edtTenSach);
        rcvThongTin = findViewById(R.id.rcvThongTin);
        btnTim = findViewById(R.id.btnTim);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnBack)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
