package com.example.quanlythuvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterSach extends ArrayAdapter {
    Context context;
    int resource;
    List<TSach> data;
    DB_Sach dbSach;
    public AdapterSach(@NonNull Context context, int resource, List<TSach> data ) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }
    public void updateData(List<TSach> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvMaMS = convertView.findViewById(R.id.tvMaMS);
        TextView tvTenDG = convertView.findViewById(R.id.tvTenDG);
        TSach tSach = data.get(position);
        tvMaMS.setText(tSach.getMaMS());
        tvTenDG.setText(tSach.getTenDG());

        return  convertView;
    }
}
