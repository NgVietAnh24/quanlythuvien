package com.example.quanlythuvien;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Danh_Sach_Of_Sach extends AppCompatActivity {

    FloatingActionButton flThem;

    RecyclerView rcvSach;
    AdapterSach adapterSach;
    static List<Sach> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_of_sach);
        Control();
        setEvent();
    }

    private void setEvent() {
        adapterSach = new AdapterSach();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSach.setLayoutManager(linearLayoutManager);

        adapterSach.setData(getListSach(),Danh_Sach_Of_Sach.this);
        rcvSach.setAdapter(adapterSach);
        flThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Danh_Sach_Of_Sach.this, ThemSach.class);
                startActivity(intent);


            }
        });
        rcvSach.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){
                    flThem.hide();
                }else {
                    flThem.show();
                }
                super.onScrolled(recyclerView,dx,dy);
            }
        });
    }

    private void Control() {
        flThem = findViewById(R.id.flThem);
        rcvSach = findViewById(R.id.rcvSach);
    }
    private byte[] convertDrawableToByteArray(int drawableResId) {
        // Lấy dữ liệu hình ảnh từ tài nguyên drawable
        Drawable drawable = getResources().getDrawable(drawableResId);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

        // Chuyển đổi bitmap thành mảng byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
    private List<Sach> getListSach() {

        byte[] img1 = convertDrawableToByteArray(R.drawable.img1);
        byte[] img2 = convertDrawableToByteArray(R.drawable.img2);
        byte[] img3 = convertDrawableToByteArray(R.drawable.img3);
        byte[] img4 = convertDrawableToByteArray(R.drawable.img4);
        byte[] img5 = convertDrawableToByteArray(R.drawable.img5);
        byte[] img6 = convertDrawableToByteArray(R.drawable.img6);

        list.add(new Sach(img1,"MK12345612", "8 vụ án hoàn hảo", 2,110000,"Mới", 2000));
        list.add(new Sach(img2,"MK12343412", "Án mạng kép ở vườn địa đàng", 3,180000,"Mới", 1999));
        list.add(new Sach(img3,"MK12313223", "Harry Potter", 5,130000,"Mới", 2000));
        list.add(new Sach(img4,"MK12323132", "Hồ sơ tâm lý học", 4,130000,"Mới", 2014));
        list.add(new Sach(img5,"MK18732733", "Ngủ cùng người chết", 9,140000,"Mới", 1019));
        list.add(new Sach(img6,"MK32763269", "Mặt nạ bươm bướm", 4,95000,"Cũ", 2000));
        return list;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnBack)
        {
            Intent intent = new Intent(Danh_Sach_Of_Sach.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
