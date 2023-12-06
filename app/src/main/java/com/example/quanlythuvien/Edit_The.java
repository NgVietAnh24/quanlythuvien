package com.example.quanlythuvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Edit_The extends AppCompatActivity {
    EditText edtHoTen, edtMaThe,edtNgaySinh, edtDiaChi, edtEmail, edtSoDT;
    RadioGroup radgGioiTinh;
    Button btnDanhSach, btnXoa, btnSua;
    ImageView imgView;
    static List<String> data_t = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    RadioButton radNam, radNu;
    DB_TheThuVien db_theThuVien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_the);
        setConTrol();
        setEvent();
    }

    private void setEvent() {
        db_theThuVien = new DB_TheThuVien(this);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_t);

        TheTV theTV = (TheTV) getIntent().getSerializableExtra("item");
        imgView.setImageBitmap(BitmapFactory.decodeByteArray(theTV.getHinh(), 0, theTV.getHinh().length));
        edtHoTen.setText(theTV.getHoTen());
        edtMaThe.setText(theTV.getMaThe());
        edtNgaySinh.setText(theTV.getNgaySinh());
        if (theTV.getGioiTinh().toString() == "Nam") {
            radNam.setChecked(true);
        }else {
            radNu.setChecked(true);
        }
        edtDiaChi.setText(theTV.getDiaChi());
        edtEmail.setText(theTV.getEmail());
        edtSoDT.setText(theTV.getSoDT());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheTV theTV = new TheTV();
                theTV.setHinh(Image_View_To_Byte(imgView));
                theTV.setHoTen(edtHoTen.getText().toString());
                theTV.setMaThe(edtMaThe.getText().toString());
                theTV.setNgaySinh(edtNgaySinh.getText().toString());
                if (radNam.isChecked()) {
                    theTV.setGioiTinh(radNam.getText().toString());
                }else {
                    theTV.setGioiTinh(radNu.getText().toString());
                }
                theTV.setDiaChi(edtDiaChi.getText().toString());
                theTV.setEmail(edtEmail.getText().toString());
                theTV.setSoDT(edtSoDT.getText().toString());
                db_theThuVien.SuaDL(theTV);
                Toast.makeText(Edit_The.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheTV t = new TheTV();
                edtMaThe.setText(t.getMaThe().toString());
                data_t.clear();
                db_theThuVien.XoaDL(t);
                arrayAdapter.notifyDataSetChanged();

            }
        });

        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachThe.data_t.clear();
                DanhSachThe.data_t.addAll(db_theThuVien.DocDL());
                arrayAdapter.notifyDataSetChanged();
                Intent intent = new Intent(Edit_The.this, DanhSachThe.class);
                startActivity(intent);
            }
        });

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                startActivityForResult(intent, 8888);
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 8888 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgView.setImageBitmap(photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    // Chuyển dữ kiểu dữ liệu image sang byte
    public byte[] Image_View_To_Byte(ImageView i) {
        BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        // Handle the checked radio button change
//        RadioButton radioButton = findViewById(checkedId);
//        if (radioButton != null) {
//            String selectedOption = radioButton.getText().toString();
//        }
//    }
//

    private void setConTrol() {
        edtHoTen = findViewById(R.id.TenNV);
        edtMaThe = findViewById(R.id.MaTheNV);
        edtMaThe.setEnabled(false);
        edtEmail = findViewById(R.id.EmailNV);
        edtSoDT = findViewById(R.id.SoDT);
        edtDiaChi = findViewById(R.id.DiaChiNV);
        btnSua = findViewById(R.id.btnSuaET);
        btnXoa = findViewById(R.id.btnXoaET);
        btnDanhSach = findViewById(R.id.btnDanhSachE);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        radgGioiTinh = findViewById(R.id.radioGroup);
        imgView = findViewById(R.id.imageView);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
    }
}

