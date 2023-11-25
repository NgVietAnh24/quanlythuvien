package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MuonSach extends AppCompatActivity {
    EditText
            edtTenDG,
            edtMaThe,
            edtNgayDT,
            edtNgayTT,
            edtMaSach,
            edtMaMS;
    Spinner spTinhTS;
    Button btnThem, btnThoat, btnDanhSach;
    List<TSach> data_s = new ArrayList<>();
    List<String> data_ts = new ArrayList<>();
    AdapterSach adapter_s;

    DB_TraSach dbTraSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muon_sach);
        setControl();
        setEvent();

    }

    private void setEvent() {
        KhoiTao();
        dbTraSach = new DB_TraSach(this);
        adapter_s = new AdapterSach(this, android.R.layout.simple_list_item_1, data_s);
        spTinhTS.setAdapter(adapter_s);
        TSach item = new TSach();
        spTinhTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                item.setTinhTS(spTinhTS.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

//        TSach tSach = (TSach) getIntent().getSerializableExtra("item");
//        edtMaMS.setText(tSach.getMaMS());
//        edtTenDG.setText(tSach.getTenDG());
//        edtMaThe.setText(tSach.getMaThe());
//        edtNgayDT.setText(tSach.getNgayDT());
//        edtNgayTT.setText(tSach.getNgayTT());
//        edtMaSach.setText(tSach.getMaSach());
//        if (tSach.getTinhTS().equals("Cũ"))
//            spTinhTS.setSelection(0);
//        if (tSach.getTinhTS().equals("Mới"))
//            spTinhTS.setSelection(1);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (TSach item : MuonSach.data_ts) {
//                    if (item.getMaMS().equals(item.getMaMS())) {
                item.setMaMS(edtMaMS.getText().toString());
                item.setTenDG(edtTenDG.getText().toString());
                item.setMaThe(edtMaThe.getText().toString());
                item.setNgayDT(edtNgayDT.getText().toString());
                item.setNgayTT(edtNgayTT.getText().toString());
                item.setMaSach(edtMaSach.getText().toString());
                item.setTinhTS(spTinhTS.getSelectedItem().toString());
                dbTraSach.ThemDl(item);
                Toast.makeText(MuonSach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MuonSach.this, Danh_Sach_Tra.class);
//                intent.putExtra("item", data_s.get(i));

//                DanhSachTra.adapter_Sach.notifyDataSetChanged();
//                onBackPressed();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Danh_Sach_Tra.data_ts.clear();
                Danh_Sach_Tra.data_ts.addAll(dbTraSach.DocDL());
                adapter_s.notifyDataSetChanged();
                // Chuyển sang màn hình danh sách
                Intent intent = new Intent(MuonSach.this, Danh_Sach_Tra.class);
                startActivity(intent);
            }
        });
    }

    private void KhoiTao() {
        data_ts.add("Cũ");
        data_ts.add("Mới");
    }

    private void setControl() {
        edtMaMS = findViewById(R.id.edtMaMS);
        edtTenDG = findViewById(R.id.edtTenDG);
        edtMaThe = findViewById(R.id.edtMaThe);
        edtNgayDT = findViewById(R.id.edtNgayDT);
        edtNgayTT = findViewById(R.id.edtNgayTT);
        edtMaSach = findViewById(R.id.edtMaSach);
        spTinhTS = findViewById(R.id.spTinhTS);
        btnThem = findViewById(R.id.btnThem);
        btnThoat = findViewById(R.id.btnThoat);
        btnDanhSach = findViewById(R.id.btnDanhSach);
    }
}
