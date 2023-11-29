package com.example.quanlythuvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter_TheTV extends ArrayAdapter {
    Context context;
    int resource;
    List<TheTV> data;
    DB_Sach dbSach;

    public Adapter_TheTV(@NonNull Context context, int resource, List<TheTV> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    public void updateData(List<TheTV> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvHoTen = convertView.findViewById(R.id.tvHoTen);
        TextView tvMaThe = convertView.findViewById(R.id.tvMaThe);
        TheTV theTV = data.get(position);
//        String imagePath = theTV.getHinhNV();
//        if (imagePath != null) {
//            File imgFile = new File(imagePath);
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                ivHinh.setImageBitmap(myBitmap);
//            }
//        }
            ivHinh.setImageResource(R.drawable.placeholder_image);
            tvHoTen.setText(theTV.getHoTen());
            tvMaThe.setText(theTV.getMaThe());

            return convertView;
        }
    }


