package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TimSach extends AppCompatActivity {
    EditText edtTenSach;
    ListView lvThongTin;
    Button btnTim;
    DB_Sach dbSach;
    List<Sach> data_s = new ArrayList<>();
    ArrayAdapter adapter_s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_sach);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbSach = new DB_Sach(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1,data_s);
        lvThongTin.setAdapter(adapter_s);

        lvThongTin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sach sach = data_s.get(i);
                edtTenSach.setText(sach.getTenSach());
            }
        });
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach = new Sach();
                sach.setTenSach(edtTenSach.getText().toString());
                data_s.clear();
                data_s.addAll(dbSach.TimDLTheoTen(sach));
                adapter_s.notifyDataSetChanged();
                Toast.makeText(TimSach.this, "Tìm thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl() {
        edtTenSach = findViewById(R.id.edtTenSach);
        lvThongTin = findViewById(R.id.lvThongTin);
        btnTim = findViewById(R.id.btnTim);
    }
}
