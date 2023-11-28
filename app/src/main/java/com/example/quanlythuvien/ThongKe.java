package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThongKe extends AppCompatActivity {
    Spinner spThongKe;
    //    ListView lvThongKe;
    TextView txtThongKe;
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    List<String> data_t = new ArrayList<>();
    ArrayAdapter arrayAdapter_t;
    DB_TraSach db_traSach;
    DB_Sach db_sach;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setControl();
        setEvent();
    }

    private void setEvent() {
        db_sach = new DB_Sach(this);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
//        lvThongKe.setAdapter(arrayAdapter);
        KhoiTao();
        arrayAdapter_t = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_t);
        spThongKe.setAdapter(arrayAdapter_t);
        spThongKe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectItem = data_t.get(i);
                if (selectItem.equals("Sách cũ")) {
                    int countCu = db_sach.getCountSachCu();
                    txtThongKe.setText("Số lượng sách cũ: " + countCu);
                    arrayAdapter.notifyDataSetChanged();
                } else if (selectItem.equals("Sách mới")) {
                    int countMoi = db_sach.getCountSachMoi();
                    txtThongKe.setText("Số lượng sách mới: " + countMoi);
                    arrayAdapter.notifyDataSetChanged();
                } else if (selectItem.equals("Số lượng tài khoản")) {
                    // Kiểm tra xem Adapter có tồn tại không
                    if (NguoiDung.adapter_u != null) {
                        // Lấy số lượng item trong Adapter (tức là số lượng item trong ListView)
                        int itemCount = NguoiDung.adapter_u.getCount();
                        txtThongKe.setText("Số lượng tài khoản: " + itemCount);
                        arrayAdapter.notifyDataSetChanged();
                    }
                } else if (selectItem.equals("Sách đã trả")) {
                    // Kiểm tra xem Adapter có tồn tại không
                    if (LichSuTS.adapter_ls != null) {
                        // Lấy số lượng item trong Adapter (tức là số lượng item trong ListView)
                        int itemCount = LichSuTS.adapter_ls.getCount();
                        txtThongKe.setText("Số sách đã trả: " + itemCount);
                        arrayAdapter.notifyDataSetChanged();
                    }
                } else if (selectItem.equals("Số sách đang mượn")) {
                    // Kiểm tra xem Adapter có tồn tại không
                    if (Danh_Sach_Tra.adapter_Sach != null) {
                        // Lấy số lượng item trong Adapter (tức là số lượng item trong ListView)
                        int itemCount = Danh_Sach_Tra.adapter_Sach.getCount();
                        txtThongKe.setText("Số sách đang mượn: " + itemCount);
                        arrayAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void KhoiTao() {
        data_t.add("Sách cũ");
        data_t.add("Sách mới");
        data_t.add("Sách đã trả");
        data_t.add("Số sách đang mượn");
        data_t.add("Số lượng tài khoản");
    }

    private void setControl() {
        spThongKe = findViewById(R.id.spThongKe);
//        lvThongKe = findViewById(R.id.lvThongKe);
        txtThongKe = findViewById(R.id.txtThongKe);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
