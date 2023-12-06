package com.example.quanlythuvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Edit_Sach extends AppCompatActivity {
    EditText edtMaSach, edtTenSach, edtSoLuong, edtGia, edtNXB;
    Button btnXoa, btnSua, btnThoat;
    ImageView ivHinh;


    Spinner spTinhTS;
    List<String> data_tts = new ArrayList<>();

    DB_Sach dbSach;
    ArrayAdapter adapter_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sach);
        setControl();
        setEvent();

    }

    private void setEvent() {
        KhoiTao();
        dbSach = new DB_Sach(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_tts);
        spTinhTS.setAdapter(adapter_s);
        adapter_s.notifyDataSetChanged();

        Intent intent = getIntent();
        if (intent != null) {
            Sach sach = (Sach) intent.getSerializableExtra("key");
            ivHinh.setImageBitmap(BitmapFactory.decodeByteArray(sach.getHinh(), 0, sach.getHinh().length));
            edtMaSach.setText(sach.getMaSach());
            edtTenSach.setText(sach.getTenSach());
            edtSoLuong.setText(String.valueOf(sach.getSoLuong()));
            edtGia.setText(sach.getGiaSach()+"");
            if (sach.getTinhTS().equals("Cũ"))
                spTinhTS.setSelection(0);
            if (sach.getTinhTS().equals("Mới"))
                spTinhTS.setSelection(1);
            edtNXB.setText(sach.getNamXB()+"");
        }
        Sach sachs = new Sach();
        spTinhTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sachs.setTinhTS(spTinhTS.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachs.setHinh(Image_View_To_Byte(ivHinh));
                sachs.setMaSach(edtMaSach.getText().toString());
                sachs.setTenSach(edtTenSach.getText().toString());
                sachs.setSoLuong(Integer.parseInt(edtSoLuong.getText().toString()));
                sachs.setGiaSach(Double.parseDouble(edtGia.getText().toString()));
                sachs.setTinhTS(spTinhTS.getSelectedItem().toString());
                sachs.setNamXB(Integer.parseInt(edtNXB.getText().toString()));
                dbSach.SuaDl(sachs);
                adapter_s.notifyDataSetChanged();
                Toast.makeText(Edit_Sach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                //Làm mới sau khi sửa sách
                ivHinh.setImageResource(R.drawable.baseline_add_photo_alternate_24);
                edtMaSach.setText("");
                edtTenSach.setText("");
                edtSoLuong.setText("");
                edtGia.setText("");
                spTinhTS.setSelection(0);
                edtNXB.setText("");
                edtTenSach.requestFocus();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach = new Sach();
                sach.setMaSach(edtMaSach.getText().toString());
                dbSach.XoaDl(sach);
                Toast.makeText(Edit_Sach.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                //Làm mới sau khi sửa sách
                ivHinh.setImageResource(R.drawable.baseline_add_photo_alternate_24);
                edtMaSach.setText("");
                edtTenSach.setText("");
                edtSoLuong.setText("");
                edtGia.setText("");
                spTinhTS.setSelection(0);
                edtNXB.setText("");
                edtTenSach.requestFocus();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Sach.this, Danh_Sach_Of_Sach.class);
                startActivity(intent);
                Danh_Sach_Of_Sach.list.clear();
                Danh_Sach_Of_Sach.list.addAll(dbSach.DocDL());
                adapter_s.notifyDataSetChanged();
            }
        });
        ivHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 8888);
            }
        });
    }

    public void KhoiTao() {

        data_tts.add("Cũ");
        data_tts.add("Mới");

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 8888 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivHinh.setImageBitmap(photo);
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

    private void setControl() {
        ivHinh = findViewById(R.id.ivHinhE);
        edtMaSach = findViewById(R.id.edtMaSachE);
        edtTenSach = findViewById(R.id.edtTenSachE);
        edtMaSach.setEnabled(false);
        edtSoLuong = findViewById(R.id.edtSoLuongE);
        edtGia = findViewById(R.id.edtGiaE);
        spTinhTS = findViewById(R.id.spTinhTS1E);
        btnThoat = findViewById(R.id.btnOutOfAddBookE);
        edtNXB = findViewById(R.id.edtNXBE);
        btnXoa = findViewById(R.id.btnXoaE);
        btnSua = findViewById(R.id.btnSuaE);

    }

}
