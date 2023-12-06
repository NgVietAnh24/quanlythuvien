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

public class TraSach extends AppCompatActivity {
    EditText edtMaThe,
            edtSoLuong,
            edtNgayDT,
            edtNgayTT,
            edtNgayM,
            edtGiaSach,
            edtMaMS;
    ImageView ivHinh;
    Spinner spTinhTS, spTenDG, spHienTrang, spTenSach;
    Button btnTraSach, btnSua, btnDanhSach, btnQuaHan, btnLichSu;
    List<String> data_ts = new ArrayList<>();
    List<String> data_ht = new ArrayList<>();
    List<String> data_ts1 = new ArrayList<>();
    List<String> data_ht1 = new ArrayList<>();
    ArrayAdapter adapter_s;
    ArrayAdapter adapter_ht;
    ArrayAdapter adapter_d;
    ArrayAdapter adapter_dd;
    DB_TraSach dbTraSach;
    DB_LichSuTS dbLichSuTS;
    DB_Sach dbSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_sach);
        setControl();
        setEvent();
    }

    private void setEvent() {
        KhoiTao();
        dbSach = new DB_Sach(this);
        dbTraSach = new DB_TraSach(this);
        dbLichSuTS = new DB_LichSuTS(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
        spTinhTS.setAdapter(adapter_s);

        TSach item = new TSach();
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
        spTinhTS.setAdapter(adapter_s);
        adapter_d = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts1);
        spTenDG.setAdapter(adapter_d);
        adapter_dd = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ht1);
        spTenSach.setAdapter(adapter_dd);
        adapter_ht = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ht);
        spHienTrang.setAdapter(adapter_ht);


        Intent intent = getIntent();
        if (intent != null) {
            TSach tsach = (TSach) intent.getSerializableExtra("key");
            ivHinh.setImageBitmap(BitmapFactory.decodeByteArray(tsach.getHinh(), 0, tsach.getHinh().length));
            edtMaMS.setText(tsach.getMaMS());
            if (tsach.getMaThe() == edtMaThe.getText().toString()) {
                tsach.getTenDG().toString();
            }
            ivHinh.setImageBitmap(BitmapFactory.decodeByteArray(tsach.getHinh(), 0, tsach.getHinh().length));
            edtNgayM.setText(tsach.getNgayM());
            edtNgayDT.setText(tsach.getNgayDT());
            edtNgayTT.setText(tsach.getNgayTT());
            edtSoLuong.setText(tsach.getSoLuong() + "");
            if (tsach.getTinhTS().equals("Cũ")) {
                spTinhTS.setSelection(0);
            }
            if (tsach.getTinhTS().equals("Mới")) {
                spTinhTS.setSelection(1);
            }
            if (tsach.getSpHienTrang().equals("Đang mượn")) {
                spHienTrang.setSelection(0);
            }
            if (tsach.getSpHienTrang().equals("Quá hạn")) {
                spHienTrang.setSelection(1);
            }
            if (tsach.getSpHienTrang().equals("Trước hạn")) {
                spHienTrang.setSelection(2);
            }

        }
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

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                item.setMaMS(edtMaMS.getText().toString());
                item.setTenDG(spTenDG.getSelectedItem().toString());
                item.setTenSach(spTenSach.getSelectedItem().toString());
                item.setHinh(Image_View_To_Byte(ivHinh));
                item.setMaThe(edtMaThe.getText().toString());
                item.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                item.setGiaSach(Double.parseDouble(edtGiaSach.getText().toString()));
                item.setNgayM(edtNgayM.getText().toString());
                item.setNgayDT(edtNgayDT.getText().toString());
                item.setNgayTT(edtNgayTT.getText().toString() + "Chưa trả");
                dbTraSach.SuaDL(item);
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.DocDL());
                Toast.makeText(TraSach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
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
        btnTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TSach tSach = new TSach();
                tSach.setMaMS(edtMaMS.getText().toString());
                tSach.setTenDG(spTenDG.getSelectedItem().toString());
                tSach.setTenSach(spTenSach.getSelectedItem().toString());
                tSach.setHinh(Image_View_To_Byte(ivHinh));
                tSach.setMaThe(edtMaThe.getText().toString());
                tSach.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                tSach.setGiaSach(Double.parseDouble(edtGiaSach.getText().toString()));
                tSach.setNgayM(edtNgayM.getText().toString());
                tSach.setNgayDT(edtNgayDT.getText().toString());
                // setTime khi thêm vào lịch sử
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                String currentDateTime = sdf.format(new Date());
                tSach.setNgayTT(currentDateTime);
                dbLichSuTS.ThemDl(tSach);
                dbSach.SuaTra(tSach.tenSach);
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.XoaDL(tSach));
                adapter_s.notifyDataSetChanged();
                Toast.makeText(TraSach.this, "Trả thành công", Toast.LENGTH_SHORT).show();
                // LÀm mới sau khi xóa
                edtMaMS.setText("");
                spTenDG.setSelection(0);
                spTenSach.setSelection(0);
                edtMaThe.setText("");
                edtSoLuong.setText("");
                edtGiaSach.setText("");
                edtNgayM.setText("");
                edtNgayDT.setText("");
                edtNgayTT.setText("");
                spTinhTS.setSelection(0);
                edtSoLuong.requestFocus();
            }
        });

        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Danh_Sach_Tra.list.clear();
                Danh_Sach_Tra.list.addAll(dbTraSach.DocDL());
                adapter_s.notifyDataSetChanged();
                Intent intent = new Intent(TraSach.this, Danh_Sach_Tra.class);
                startActivity(intent);
            }
        });
        btnLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LichSuTS.list.clear();
                LichSuTS.list.addAll(dbLichSuTS.DocDL());
                adapter_s.notifyDataSetChanged();
                // Chuyển sáng màn hình lịch sử
                Intent intent = new Intent(TraSach.this, LichSuTS.class);
                startActivity(intent);
            }
        });
    }

    private void KhoiTao() {
        data_ts.add("Cũ");
        data_ts.add("Mới");
        data_ht.add("Đang mượn");
        data_ht.add("Quá hạn");
        data_ht.add("Trước hạn");
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
        spTenDG.setEnabled(false);
        spTenSach = findViewById(R.id.spTenSachT);
        spTenSach.setEnabled(false);
        ivHinh = findViewById(R.id.ivHinhM);
        edtMaThe = findViewById(R.id.edtMaTheT);
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
        btnTraSach = findViewById(R.id.btnTraSach);
        btnSua = findViewById(R.id.btnSuaTS);
        btnLichSu = findViewById(R.id.btnLichSu);
        spHienTrang = findViewById(R.id.spHienTrang);
        btnDanhSach = findViewById(R.id.btnDanhSachMS);
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




