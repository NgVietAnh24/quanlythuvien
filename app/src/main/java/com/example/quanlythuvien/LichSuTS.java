package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LichSuTS extends AppCompatActivity {
    RecyclerView rcvLichSu;
    Adapter_TSach adapter_tSach;
    static List<TSach> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_ts);
        setControl();
        setEvent();
    }

    private void setEvent() {
        adapter_tSach = new Adapter_TSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvLichSu.setLayoutManager(linearLayoutManager);

        adapter_tSach.setData(list,LichSuTS.this);
        rcvLichSu.setAdapter(adapter_tSach);
    }


    private void setControl() {
        rcvLichSu = findViewById(R.id.rcvLichSu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnBack)
        {
            Intent intent = new Intent(LichSuTS.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}




