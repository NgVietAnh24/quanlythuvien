package com.example.quanlythuvien;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThemSach extends Activity {

    EditText edtMaSach,
            edtTenSach,

            edtSoLuong,
            edtGia,

            edtNXB;
    Button btnThem;
    ImageView ivHinh;


   Spinner spChonSach, spTinhTS;
   List<String> data_tts = new ArrayList<>();
    List<String> data_tts1 = new ArrayList<>();
    DB_Sach dbSach;
    ArrayAdapter adapter_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);

        setControl();
        setEvent();
        }

    private void setEvent() {
        KhoiTao();
        dbSach = new DB_Sach(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_tts);
        spTinhTS.setAdapter(adapter_s);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_tts1);
        spChonSach.setAdapter(adapter_s);
        Sach sachs = new Sach();
        spChonSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sachs.setTinhTS(spChonSach.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spTinhTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sachs.setTinhTS(spTinhTS.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sachs.setMaSach(edtMaSach.getText().toString());
                sachs.setTenSach(edtTenSach.getText().toString());
                sachs.setSoLuong(edtSoLuong.getText().toString());
                sachs.setGiaSach(edtGia.getText().toString());
                sachs.setNamXB(edtNXB.getText().toString());
                dbSach.ThemDl(sachs);

                Toast.makeText(ThemSach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Danh_Sach_Of_Sach.list.clear();
                Danh_Sach_Of_Sach.list.addAll(dbSach.DocDL());
                adapter_s.notifyDataSetChanged();
                //Làm mới sau khi thêm sách
                edtMaSach.setText("");
                edtTenSach.setText("");
                edtSoLuong.setText("");
                edtGia.setText("");
                spTinhTS.setSelection(0);
                edtNXB.setText("");
                edtMaSach.requestFocus();
            }
        });
    }

    public void KhoiTao(){
        data_tts.add("Cũ");
        data_tts.add("Mới");
        if(data_tts1.add("Sự im lặng của bầy cừu")) {
            ivHinh.setImageResource(R.drawable.img7);
        }
        if(data_tts1.add("Thuật thao túng")) {
            ivHinh.setImageResource(R.drawable.img8);
        }
        if(data_tts1.add("Thao túng tâm lý")) {
            ivHinh.setImageResource(R.drawable.thaotung);
        }
        if(data_tts1.add("Ghi chép pháp y")) {
            ivHinh.setImageResource(R.drawable.z4913627930680_96124e6c22cfe94fcdd113281a53d94d);
        }
        if(data_tts1.add("8 vụ án hoàn hảo")) {
            ivHinh.setImageResource(R.drawable.img1);
        }
        if(data_tts1.add("Án mạng kép ở vườn địa đàng")) {
            ivHinh.setImageResource(R.drawable.img2);
        }
        if(data_tts1.add("Harry Potter")) {
            ivHinh.setImageResource(R.drawable.img3);
        }
        if(data_tts1.add("Hồ sơ tâm lý học")) {
            ivHinh.setImageResource(R.drawable.img4);
        }
        if( data_tts1.add("Ngủ cùng người chết")) {
            ivHinh.setImageResource(R.drawable.img5);
        }
        if(data_tts1.add("Mặt nạ bươm bướm")) {
            ivHinh.setImageResource(R.drawable.img6);
        }
        adapter_s.notifyDataSetChanged();
    }

    private void setControl() {

        ivHinh = findViewById(R.id.ivHinh);
        edtMaSach = findViewById(R.id.edtMaSach);
        edtTenSach.setEnabled(false);
        edtTenSach = findViewById(R.id.edtTenSach);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        edtGia = findViewById(R.id.edtGia);
        spTinhTS = findViewById(R.id.spTinhTS1);
        edtNXB = findViewById(R.id.edtNXB);
        btnThem = findViewById(R.id.btnThemSach);

    }
}
