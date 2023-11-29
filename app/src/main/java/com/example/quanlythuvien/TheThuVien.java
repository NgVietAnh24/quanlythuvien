package com.example.quanlythuvien;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TheThuVien extends AppCompatActivity {
    EditText edtHoTen, edtMaThe,edtNgaySinh, edtDiaChi, edtEmail, edtSoDT;
    RadioGroup radgGioiTinh;
    Button btnLuu, btnDanhSach, btnUpLoad, btnXoa, btnSua;
//    static final int PICK_IMAGE_REQUEST = 1;
//    ImageView imgView;
//    Uri imageUri;
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

        TheTV theTV = (TheTV) getIntent().getSerializableExtra("item");
            edtHoTen.setText(theTV.getHoTen());
            edtMaThe.setText(theTV.getMaThe());
            edtNgaySinh.setText(theTV.getNgaySinh());
            if (radgGioiTinh.getCheckedRadioButtonId() == R.id.radNam) {
                theTV.setGioiTinh("Nam");
            }else if(radgGioiTinh.getCheckedRadioButtonId() == R.id.radNu){
                theTV.setGioiTinh("Nữ");
            }
            edtDiaChi.setText(theTV.getDiaChi());
            edtEmail.setText(theTV.getEmail());
            edtSoDT.setText(theTV.getSoDT());

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TheTV theTV = new TheTV();
                theTV.setHoTen(edtHoTen.getText().toString());
                theTV.setMaThe(edtMaThe.getText().toString());
                theTV.setNgaySinh(edtNgaySinh.getText().toString());
                if (radgGioiTinh.getCheckedRadioButtonId() == R.id.radNam) {
                    theTV.setGioiTinh("Nam");
                }else if(radgGioiTinh.getCheckedRadioButtonId() == R.id.radNu){
                    theTV.setGioiTinh("Nữ");
                }
                theTV.setDiaChi(edtDiaChi.getText().toString());
                theTV.setEmail(edtEmail.getText().toString());
                theTV.setSoDT(edtSoDT.getText().toString());
                db_theThuVien.ThemDl(theTV);
                Log.d("AAA", "lỗi:" + theTV);
                Toast.makeText(TheThuVien.this,"Lưu thành công", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(TheThuVien.this, DanhSachThe.class);
                startActivity(intent);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        btnUpLoad.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View v) {
////                openGallery();
////            }
//        });
    }
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        // Handle the checked radio button change
//        RadioButton radioButton = findViewById(checkedId);
//        if (radioButton != null) {
//            String selectedOption = radioButton.getText().toString();
//        }
//    }
//    private void openGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            imageUri = data.getData();
//
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                imgView.setImageBitmap(bitmap);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    private void setConTrol() {
        edtHoTen = findViewById(R.id.TenNV);
        edtMaThe = findViewById(R.id.MaTheNV);
        edtEmail = findViewById(R.id.EmailNV);
        edtSoDT = findViewById(R.id.SoDT);
        edtDiaChi = findViewById(R.id.DiaChiNV);
        btnLuu = findViewById(R.id.btnLuu);
        btnSua = findViewById(R.id.btnSua);
        btnXoa = findViewById(R.id.btnXoa);
        btnDanhSach = findViewById(R.id.btnDanhSach);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        radgGioiTinh = findViewById(R.id.radioGroup);
//        imgView = findViewById(R.id.imageView);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
    }
}
