package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    edtTenSach,
            edtNgayDT,
            edtNgayTT,
            edtMaSach,
            edtMaMS;
    Spinner spTinhTS;
    Button btnThem, btnThoat, btnDanhSach;
    List<TSach> data_s = new ArrayList<>();
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter adapter_s;
    AdapterSach adapterSach;
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
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
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
                if (edtMaSach.getText().length() <= 0) {
                    edtMaSach.setError("Nhập mã sách !!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập mã sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtTenSach.getText().length() <= 0) {
                    edtTenSach.setError("Nhập tên sách !!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập tên sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtTenDG.getText().length() <= 0) {
                    edtTenDG.setError("Nhập tên độc giả !!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập tên độc giả", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtMaThe.getText().length() <= 0) {
                    edtMaThe.setError("Nhập mã thẻ !!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập mã thẻ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtNgayDT.getText().length() <= 0) {
                    edtNgayDT.setError("Nhập ngày dự trả !!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập ngày dự trả", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtNgayTT.getText().length() <= 0) {
                    edtNgayTT.setError("Nhập ngày thực trả !!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập ngày thực trả", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtMaSach.getText().length() <= 0) {
                    edtMaSach.setError("Nhập mã sách!!!");
                    Toast.makeText(MuonSach.this, "Chưa nhập mã sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                item.setMaMS(edtMaMS.getText().toString());
                item.setTenDG(edtTenDG.getText().toString());
                item.setTenSach(edtTenSach.getText().toString());
                item.setMaThe(edtMaThe.getText().toString());
                item.setNgayDT(edtNgayDT.getText().toString());
                item.setNgayTT(edtNgayTT.getText().toString());
                item.setMaSach(edtMaSach.getText().toString());
                item.setTinhTS(spTinhTS.getSelectedItem().toString());
                dbTraSach.ThemDl(item);
                Toast.makeText(MuonSach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                // Làm mới sau khi mượn sách
                edtMaMS.setText("");
                edtTenDG.setText("");
                edtTenSach.setText("");
                edtMaThe.setText("");
                edtNgayDT.setText("");
                edtNgayTT.setText("");
                edtMaSach.setText("");
                spTinhTS.setSelection(0);
                edtMaMS.requestFocus();
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
        edtTenSach = findViewById(R.id.edtTenSach);
        edtMaSach = findViewById(R.id.edtMaSach);
        spTinhTS = findViewById(R.id.spTinhTS);
        btnThem = findViewById(R.id.btnThem);
        btnThoat = findViewById(R.id.btnThoat);
        btnDanhSach = findViewById(R.id.btnDanhSach);
    }
}
