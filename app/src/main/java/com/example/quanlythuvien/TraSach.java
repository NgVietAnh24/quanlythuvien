package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TraSach extends AppCompatActivity {
    EditText
            edtTenDG,
            edtMaThe,
            edtNgayDT,
            edtNgayTT,
            edtMaSach;
    TextView tvMaMS;
    Spinner spTinhTS;
    Button btnSua, btnXoa, btnDanhSach;
    List<TSach> data_s = new ArrayList<>();
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter adapter_s;

    DB_TraSach dbTraSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_sach);
        setControl();
        setEvent();

    }

    private void setEvent() {
        KhoiTao();
        dbTraSach = new DB_TraSach(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_s);
        spTinhTS.setAdapter(adapter_s);


        TSach tSach = (TSach) getIntent().getSerializableExtra("item");
        tvMaMS.setText(tSach.getMaMS());
        edtTenDG.setText(tSach.getTenDG());
        edtMaThe.setText(tSach.getMaThe());
        edtNgayDT.setText(tSach.getNgayDT());
        edtNgayTT.setText(tSach.getNgayTT());
        edtMaSach.setText(tSach.getMaSach());
        if (tSach.getTinhTS().equals("Cũ"))
            spTinhTS.setSelection(0);
        if (tSach.getTinhTS().equals("Mới"))
            spTinhTS.setSelection(1);
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

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                item.setMaMS(tvMaMS.getText().toString());
                item.setTenDG(edtTenDG.getText().toString());
                item.setMaThe(edtMaThe.getText().toString());
                item.setNgayDT(edtNgayDT.getText().toString());
                item.setNgayTT(edtNgayTT.getText().toString());
                item.setMaSach(edtMaSach.getText().toString());
                item.setTinhTS(spTinhTS.getSelectedItem().toString());
                dbTraSach.SuaDl(item);
                Toast.makeText(TraSach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();


            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (TSach item : DanhSachTra.data_ts) {
//                    if (item.getMaMS().equals(tSach.getMaMS())) {
//                        DanhSachTra.data_ts.remove(item);
                TSach tSach = new TSach();
                tSach.setMaMS(tvMaMS.getText().toString());
                dbTraSach.XoaDl(tSach);
                Toast.makeText(TraSach.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                DanhSachTra.adapter_Sach.notifyDataSetChanged();
//                onBackPressed();
            }
        });


        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Danh_Sach_Tra.data_ts.clear();
                Danh_Sach_Tra.data_ts.addAll(dbTraSach.DocDL());
                adapter_s.notifyDataSetChanged();
                onBackPressed();
            }
        });
    }

    private void KhoiTao() {
        data_ts.add("Cũ");
        data_ts.add("Mới");
    }

    private void setControl() {
        tvMaMS = findViewById(R.id.tvMaMS);
        edtTenDG = findViewById(R.id.edtTenDG);
        edtMaThe = findViewById(R.id.edtMaThe);
        edtNgayDT = findViewById(R.id.edtNgayDT);
        edtNgayTT = findViewById(R.id.edtNgayTT);
        edtMaSach = findViewById(R.id.edtMaSach);
        spTinhTS = findViewById(R.id.spTinhTS);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnDanhSach = findViewById(R.id.btnDanhSach);
    }
}




