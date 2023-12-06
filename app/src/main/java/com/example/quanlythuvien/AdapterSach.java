package com.example.quanlythuvien;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Context context;


    public void setData(List<Sach> list, Context context) {
        this.listSach = list;
        this.context = context;
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
        if (holder.getAdapterPosition() == RecyclerView.NO_POSITION) {
            return;
        }
        final Sach sach = listSach.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Khi một mục được nhấp vào, tạo Intent để chuyển dữ liệu
                Intent intent = new Intent(context, Edit_Sach.class);
                intent.putExtra("key", sach); // Truyền đối tượng MyData hoặc thông tin cần thiết

                context.startActivity(intent);
            }
        });
        if (sach == null) {
            return;
        }
        byte[] hinh = sach.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.ivHinh.setImageBitmap(bitmap);
        holder.txtMaSach.setText(String.valueOf(sach.getMaSach()));
        holder.txtTenSach.setText(sach.getTenSach());
        holder.txtSoLuong.setText(String.valueOf(sach.getSoLuong()));
        holder.txtGiaSach.setText(String.valueOf(sach.getGiaSach()));
        holder.txtStatus.setText(sach.getTinhTS());
        holder.txtNamXB.setText(String.valueOf(sach.getNamXB()));


    }

    @Override
    public int getItemCount() {
        if (listSach != null) {
            return listSach.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinh;
        TextView txtMaSach, txtTenSach, txtSoLuong, txtGiaSach, txtStatus, txtNamXB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinh = itemView.findViewById(R.id.imageViewItem);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtGiaSach = itemView.findViewById(R.id.txtGiaSach);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtNamXB = itemView.findViewById(R.id.txtNamXB);
        }
    }
}

