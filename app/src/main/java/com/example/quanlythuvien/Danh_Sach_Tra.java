package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Danh_Sach_Tra extends AppCompatActivity {

    ListView lvDanhSach;

    static List<TSach> data_ts = new ArrayList<>();

    static AdapterSach adapter_Sach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_tra);
        setControl();
        setEvent();
    }

    private void setEvent() {
//        KhoiTao();
//        adapter_Sach = new AdapterSach(this, R.layout.activity_sach_item, data_ts);
//        lvDanhSach.setAdapter(adapter_Sach);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Danh_Sach_Tra.this, TraSach.class);
                intent.putExtra("item", data_ts.get(i));
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_ts.remove(i);
//                adapter_Sach.updateData(data_ts);
                return false;
            }
        });
    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
