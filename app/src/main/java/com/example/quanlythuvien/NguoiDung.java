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

public class NguoiDung extends AppCompatActivity {
    ListView lvDanhSach;

    static List<User> data_u = new ArrayList<>();

    static AdapterUser adapter_u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setControl();
        setEvent();
    }

    private void setEvent() {

        adapter_u = new AdapterUser(this, R.layout.activity_item_users, data_u);
        lvDanhSach.setAdapter(adapter_u);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(NguoiDung.this,Edit_ND.class);
                intent.putExtra("item",data_u.get(i));
                startActivity(intent);
            }
        });

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_u.remove(i);
                adapter_u.updateData(data_u);
                return false;
            }
        });
    }


    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSach);
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

        return super.onOptionsItemSelected(item);
    }
}

