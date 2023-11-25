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

public class AdapterUser extends ArrayAdapter {
    Context context;
    int resource;
    List<User> data;
    public AdapterUser(@NonNull Context context, int resource, List<User> data ) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }
    public void updateData(List<User> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvUserName = convertView.findViewById(R.id.tvUserName);
        TextView tvPassWord = convertView.findViewById(R.id.tvPassWord);
        User user = data.get(position);
        tvUserName.setText(user.getUsername());
        tvPassWord.setText(user.getPassword());

        return  convertView;
    }
}

