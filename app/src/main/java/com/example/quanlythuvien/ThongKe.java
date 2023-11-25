package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThongKe extends AppCompatActivity {
    Spinner spThongKe;
    ListView lvThongKe;
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    List<String> data_t = new ArrayList<>();
    ArrayAdapter arrayAdapter_t;
    DB_TraSach db_traSach;
    DB_Sach db_sach;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setControl();
        setEvent();
    }

    private void setEvent() {

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
        lvThongKe.setAdapter(arrayAdapter);
        KhoiTao();
        arrayAdapter_t = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_t);
        spThongKe.setAdapter(arrayAdapter_t);
        spThongKe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectItem = data_t.get(i);
                if(selectItem.equals("Sách cũ")){
                    db_sach.getCountSachCu();
                    arrayAdapter.notifyDataSetChanged();
                }else if(selectItem.equals("Sách mới")){
                    db_sach.getCountSachMoi();
                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void KhoiTao(){
        data_t.add("Sách cũ");
        data_t.add("Sách mới");
        data_t.add("Sách đã trả");
        data_t.add("Số sách đang mượn");
        data_t.add("Số lượng tài khoản");
    }
    private void setControl() {
        spThongKe = findViewById(R.id.spThongKe);
        lvThongKe = findViewById(R.id.lvThongKe);
    }
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