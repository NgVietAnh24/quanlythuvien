package com.example.quanlythuvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TheThuVien extends AppCompatActivity {
    EditText edtHoTen, edtMaThe,edtNgaySinh, edtDiaChi, edtEmail, edtSoDT;
    RadioGroup radgGioiTinh;
    Button btnLuu, btnDanhSach, btnXoa, btnSua;
    ImageView imgView;
    static List<String> data_t = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    RadioButton radNam, radNu;
    DB_TheThuVien db_theThuVien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_thu_vien);
        setConTrol();
        setEvent();
    }

    private void setEvent() {
        db_theThuVien = new DB_TheThuVien(this);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_t);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheTV theTV = new TheTV();
                theTV.setHinh(Image_View_To_Byte(imgView));
                theTV.setHoTen(edtHoTen.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                String currentDateTime = sdf.format(new Date());

                String autoGeneratedCode = "MT" + currentDateTime; // Ví dụ: BK20211203123456
                theTV.setMaThe(autoGeneratedCode);
                theTV.setNgaySinh(edtNgaySinh.getText().toString());
                if (radNam.isChecked()) {
                    theTV.setGioiTinh(radNam.getText().toString());
                }else {
                    theTV.setGioiTinh(radNu.getText().toString());
                }
                theTV.setDiaChi(edtDiaChi.getText().toString());
                theTV.setEmail(edtEmail.getText().toString());
                theTV.setSoDT(edtSoDT.getText().toString());
                db_theThuVien.ThemDl(theTV);
                Toast.makeText(TheThuVien.this,"Lưu thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachThe.data_t.clear();
                DanhSachThe.data_t.addAll(db_theThuVien.DocDL());
                arrayAdapter.notifyDataSetChanged();
                Intent intent = new Intent(TheThuVien.this, DanhSachThe.class);
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


    private void setConTrol() {
        edtHoTen = findViewById(R.id.TenNV);
        edtMaThe = findViewById(R.id.MaTheNV);
        edtMaThe.setEnabled(false);
        edtEmail = findViewById(R.id.EmailNV);
        edtSoDT = findViewById(R.id.SoDT);
        edtDiaChi = findViewById(R.id.DiaChiNV);
        btnLuu = findViewById(R.id.btnLuu);
        btnSua = findViewById(R.id.btnSuaET);
        btnXoa = findViewById(R.id.btnXoaET);
        btnDanhSach = findViewById(R.id.btnDanhSach);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        radgGioiTinh = findViewById(R.id.radioGroup);
        imgView = findViewById(R.id.imageView);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
