package com.example.quanlythuvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Screen_Muon_Tra extends AppCompatActivity {
    EditText edtMaThe,
            edtSoLuong,
            edtNgayDT,
            edtNgayTT,
            edtNgayM,
            edtGiaSach,
            edtMaMS;
    ImageView ivHinh;
    Spinner spTinhTS, spTenDG, spHienTrang, spTenSach;
    Button btnThem, btnThoat;
    List<TSach> data_s = new ArrayList<>();
    List<String> data_ts = new ArrayList<>();
    List<String> data_ht = new ArrayList<>();
    List<String> data_ts1 = new ArrayList<>();
    List<String> data_ht1 = new ArrayList<>();
    ArrayAdapter adapter_s;
    ArrayAdapter adapter_ht;
    ArrayAdapter adapter_d;
    ArrayAdapter adapter_dd;
    AdapterSach adapterSach;
    DB_TraSach dbTraSach;
    DB_Sach dbSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muon_tra);
        setControl();
        setEvent();

    }

    private void setEvent() {
        KhoiTao();
        dbSach = new DB_Sach(this);
        dbTraSach = new DB_TraSach(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
        spTinhTS.setAdapter(adapter_s);
        adapter_d = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts1);
        spTenDG.setAdapter(adapter_d);
        adapter_dd = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ht1);
        spTenSach.setAdapter(adapter_dd);
        adapter_ht = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ht);
        spHienTrang.setAdapter(adapter_ht);
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
        spHienTrang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                item.setSpHienTrang(spHienTrang.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spTenSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                item.setTenSach(spTenSach.getSelectedItem().toString());
                for (Sach sach : Danh_Sach_Of_Sach.list) {
                    if (sach.getTenSach() == spTenSach.getSelectedItem().toString()) {
                        byte[] hinh = sach.getHinh();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                        ivHinh.setImageBitmap(bitmap);
                        edtGiaSach.setText(String.valueOf(sach.getGiaSach()));
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        spTenDG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                item.setTenSach(spTenDG.getSelectedItem().toString());
                for (TheTV the : DanhSachThe.data_t) {
                    if (the.getHoTen() == spTenDG.getSelectedItem().toString()) {
                        edtMaThe.setText(the.getMaThe());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtSoLuong.getText().length() <= 0) {
                    edtSoLuong.setError("Nhập số lượng !!!");
                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtNgayDT.getText().length() <= 0) {
                    edtNgayDT.setError("Nhập ngày dự trả !!!");
                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập ngày dự trả", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (edtTenDG.getText().length() <= 0) {
//                    edtTenDG.setError("Nhập tên độc giả !!!");
//                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập tên độc giả", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (edtMaThe.getText().length() <= 0) {
//                    edtMaThe.setError("Nhập mã thẻ !!!");
//                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập mã thẻ", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (edtNgayDT.getText().length() <= 0) {
//                    edtNgayDT.setError("Nhập ngày dự trả !!!");
//                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập ngày dự trả", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (edtNgayTT.getText().length() <= 0) {
//                    edtNgayTT.setError("Nhập ngày thực trả !!!");
//                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập ngày thực trả", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (edtMaSach.getText().length() <= 0) {
//                    edtMaSach.setError("Nhập mã sách!!!");
//                    Toast.makeText(Screen_Muon_Tra.this, "Chưa nhập mã sách", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                // Tạo mã tự động dựa trên thời gian thực
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                String currentDateTime = sdf.format(new Date());

                String autoGeneratedCode = "MS" + currentDateTime; // Ví dụ: BK20211203123456
                item.setMaMS(autoGeneratedCode);
                item.setTenDG(spTenDG.getSelectedItem().toString());
                item.setTenSach(spTenSach.getSelectedItem().toString());
                item.setHinh(Image_View_To_Byte(ivHinh));
                item.setMaThe(edtMaThe.getText().toString());
                item.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                item.setGiaSach(Double.parseDouble(edtGiaSach.getText().toString()));
                SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                String timeM = time.format(new Date());
                item.setNgayM(timeM);
                item.setNgayDT(edtNgayDT.getText().toString());
                item.setNgayTT(edtNgayTT.getText().toString() + "Chưa trả");
                dbTraSach.ThemDl(item);
                dbSach.SuaMuon(item.tenSach);
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.DocDL());
                Toast.makeText(Screen_Muon_Tra.this, "Mượn thành công", Toast.LENGTH_SHORT).show();
                // Làm mới sau khi mượn sách
                edtMaMS.setText("");
                spTenDG.setSelection(0);
                spTenSach.setSelection(0);
                ivHinh.setImageResource(R.drawable.baseline_add_photo_alternate_24);
                edtMaThe.setText("");
                edtSoLuong.setText("");
                edtGiaSach.setText("");
                edtNgayM.setText("");
                edtNgayDT.setText("");
                edtNgayTT.setText("");
                spTinhTS.setSelection(0);
                spHienTrang.setSelection(0);
                edtSoLuong.requestFocus();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Screen_Muon_Tra.this, Danh_Sach_Tra.class);
                startActivity(intent);
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.DocDL());
            }
        });

    }

    private void KhoiTao() {
        data_ts.add("Cũ");
        data_ts.add("Mới");
        data_ht.add("Đang mượn");
        for (Sach sach : Danh_Sach_Of_Sach.list) {
            data_ht1.add(sach.getTenSach());
        }
        for (TheTV the : DanhSachThe.data_t) {
            data_ts1.add(the.getHoTen());
        }
    }

    private void setControl() {
        edtMaMS = findViewById(R.id.edtMaMSach);
        edtMaMS.setEnabled(false);
        spTenDG = findViewById(R.id.spTenDG);
        spTenSach = findViewById(R.id.spTenSach);
        ivHinh = findViewById(R.id.ivHinhM);
        edtMaThe = findViewById(R.id.edtMaThe);
        edtMaThe.setEnabled(false);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        spTinhTS = findViewById(R.id.spTinhTS1);
        edtNgayM = findViewById(R.id.edtNgayM);
        edtNgayM.setEnabled(false);
        edtNgayDT = findViewById(R.id.edtNgayDT);
        edtNgayTT = findViewById(R.id.edtNgayTT);
        edtNgayTT.setEnabled(false);
        edtGiaSach = findViewById(R.id.edtGia);
        edtGiaSach.setEnabled(false);
        btnThem = findViewById(R.id.btnThemSach);
        btnThoat = findViewById(R.id.btnOutOfAddBookM);
        spHienTrang = findViewById(R.id.spHienTrang);


    }

    public byte[] Image_View_To_Byte(ImageView i) {
        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
