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

public class DanhSachThe extends AppCompatActivity {
    ListView lvDanhSach;

    static List<TheTV> data_t = new ArrayList<>();

    static Adapter_TheTV adapter_theTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the);
        setControl();
        setEvent();
    }

    private void setEvent() {
        adapter_theTV = new Adapter_TheTV(this, R.layout.activity_items_thetv, data_t);
        lvDanhSach.setAdapter(adapter_theTV);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhSachThe.this, Edit_The.class);
                intent.putExtra("item", data_t.get(i));
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_t.remove(i);
                adapter_theTV.updateData(data_t);
                return false;
            }
        });
    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            Intent intent = new Intent(DanhSachThe.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
