package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Danh_Sach_Tra extends AppCompatActivity {

    FloatingActionButton flThem;

    RecyclerView rcvSach;
    Adapter_TSach adapter_tSach;
    static List<TSach> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_tra);
        Control();
        setEvent();
    }

    private void setEvent() {
        adapter_tSach = new Adapter_TSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSach.setLayoutManager(linearLayoutManager);

        adapter_tSach.setData(getListSach(), Danh_Sach_Tra.this);
        rcvSach.setAdapter(adapter_tSach);
        flThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Danh_Sach_Tra.this, Screen_Muon_Tra.class);
                startActivity(intent);


            }
        });
        rcvSach.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    flThem.hide();
                } else {
                    flThem.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    private List<TSach> getListSach() {return list;}
    private void Control() {
        flThem = findViewById(R.id.flThem_dsTra);
        rcvSach = findViewById(R.id.rcvDSTra);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            Intent intent = new Intent(Danh_Sach_Tra.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
