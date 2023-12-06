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

public class Adapter_TSach extends RecyclerView.Adapter<Adapter_TSach.ViewHolder> {

    private List<TSach> listSach;
    private Context context;


    public void setData(List<TSach> list, Context context) {
        this.listSach = list;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Adapter_TSach.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tra_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_TSach.ViewHolder holder, int position) {
        final TSach tsach = listSach.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Khi một mục được nhấp vào, tạo Intent để chuyển dữ liệu
                Intent intent = new Intent(context, TraSach.class);
                intent.putExtra("key", tsach); // Truyền đối tượng MyData hoặc thông tin cần thiết

                context.startActivity(intent);
            }
        });
        if (tsach == null) {
            return;
        }
        byte[] hinh = tsach.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.ivHinh.setImageBitmap(bitmap);
        holder.tvMaMuonSach.setText(tsach.getMaMS());
        holder.tvTenDocGia.setText(tsach.getTenDG());
        holder.tvTenSach.setText(tsach.getTenSach());
        holder.tvMaThe.setText(tsach.getMaThe());
        holder.tvSoLuong.setText(String.valueOf(tsach.getSoLuong()));
        holder.tvGiaSach.setText(String.valueOf(tsach.getGiaSach()));
        holder.tvNgayM.setText(tsach.getNgayM());
        holder.tvNgayDT.setText(tsach.getNgayDT());
        holder.tvNgayTT.setText(tsach.getNgayTT());
        holder.tvStatus.setText(tsach.getTinhTS());
        holder.tvHienTrang.setText(tsach.getSpHienTrang());


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
        TextView tvMaMuonSach,tvNgayM, tvTenDocGia, tvTenSach, tvMaThe, tvSoLuong, tvGiaSach, tvNgayDT, tvNgayTT, tvStatus, tvHienTrang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHinh = itemView.findViewById(R.id.imageViewItem);
            tvMaMuonSach = itemView.findViewById(R.id.tvMaMS);
            tvTenDocGia = itemView.findViewById(R.id.tvTenDG);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvMaThe = itemView.findViewById(R.id.tvMaThe);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvGiaSach = itemView.findViewById(R.id.tvGiaSach);
            tvNgayM = itemView.findViewById(R.id.tvNgayM);
            tvNgayDT = itemView.findViewById(R.id.tvNgayDT);
            tvNgayTT = itemView.findViewById(R.id.tvNgayTT);
            tvStatus = itemView.findViewById(R.id.tvTinhTS11);
            tvHienTrang = itemView.findViewById(R.id.tvHienTrang);

        }
    }
}

