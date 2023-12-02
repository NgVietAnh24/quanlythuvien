package com.example.quanlythuvien;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Danh_Sach_Of_Sach extends AppCompatActivity {

    FloatingActionButton flThem;

    static RecyclerView rcvSach;
    static AdapterSach adapterSach;
    int gravity;
    static List<Sach> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_of_sach);
        Control();
        setEvent();


    }

    private void setEvent() {
        adapterSach = new AdapterSach();
        final Dialog dialog = new Dialog(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvSach.setLayoutManager(linearLayoutManager);

        adapterSach.setData(getListSach());
        rcvSach.setAdapter(adapterSach);
        flThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_them_sach);
                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAttributes = window.getAttributes();
                windowAttributes.gravity = gravity;
                window.setAttributes(windowAttributes);

                if (Gravity.CENTER == gravity) {
                    dialog.setCancelable(true);
                } else {
                    dialog.setCancelable(false);
                }

                Button btnThoat = dialog.findViewById(R.id.btnOutOfAddBook);
                btnThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        rcvSach.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    flThem.hide();
                } else {
                    flThem.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void Control() {
        flThem = findViewById(R.id.flThem);
        rcvSach = findViewById(R.id.rcvSach);
    }

    private List<Sach> getListSach() {
         list = new ArrayList<>();
        list.add(new Sach(R.drawable.img1, "MS1", "8 vụ án hoàn hảo", "2", "2000", "Mới", "2000"));
        list.add(new Sach(R.drawable.img2, "MS2", "Án mạng kép ở vườn địa đàng", "3", "2000", "Mới", "1999"));
        list.add(new Sach(R.drawable.img3, "MS3", "Harry Potter", "5", "2000", "Mới", "2000"));
        list.add(new Sach(R.drawable.img4, "MS4", "Hồ sơ tâm lý học", "4", "2000", "Mới", "2014"));
        list.add(new Sach(R.drawable.img5, "MS5", "Ngủ cùng người chết", "7", "2000", "Mới", "1019"));
        list.add(new Sach(R.drawable.img6, "MS6", "Mặt nạ bươm bướm", "4", "2000", "Cũ", "2000"));

        return list;

    }
}
