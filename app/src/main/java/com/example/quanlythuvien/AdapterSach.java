package com.example.quanlythuvien;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.ViewHolder> {

    private List<Sach> listSach;

    public void setData(List<Sach> list) {
        this.listSach = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach = listSach.get(position);
        if (sach == null) {
            return;
        }
        holder.imageView.setImageResource(sach.getImgPath());
        holder.txtMaSach.setText(sach.getMaSach());
        holder.txtTenSach.setText(sach.getTenSach());
        holder.txtSoLuong.setText(sach.getSoLuong());
        holder.txtGiaSach.setText(sach.getGiaSach());
        holder.txtStatus.setText(sach.getTinhTS());
        holder.txtNamXB.setText(sach.getNamXB());


    }

    @Override
    public int getItemCount() {
        if (listSach != null) {
            return listSach.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtMaSach, txtTenSach, txtSoLuong, txtGiaSach, txtStatus, txtNamXB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewItem);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtGiaSach = itemView.findViewById(R.id.txtGiaSach);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtNamXB = itemView.findViewById(R.id.txtNamXB);
        }
    }
}

