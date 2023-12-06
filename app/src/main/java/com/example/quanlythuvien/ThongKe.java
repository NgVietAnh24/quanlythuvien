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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKe extends AppCompatActivity {
    Spinner spThongKe;

    TextView txtThongKe;
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    List<String> data_t = new ArrayList<>();
    ArrayAdapter arrayAdapter_t;
    DB_Sach db_sach;
    DB_TraSach dbTraSach;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        setControl();
        setEvent();
    }

    private void setEvent() {
        db_sach = new DB_Sach(this);
        dbTraSach = new DB_TraSach(this);
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
                    if (LichSuTS.list != null) {
                        // Lấy số lượng item trong Adapter (tức là số lượng item trong ListView)
                        int total = LichSuTS.list.size();
                        txtThongKe.setText("Số sách đã trả: " + total);
                        arrayAdapter.notifyDataSetChanged();
                    }
                } else if (selectItem.equals("Số sách đang mượn")) {
                    // Kiểm tra xem Adapter có tồn tại không
                    if (Danh_Sach_Tra.list != null) {
                        // Lấy số lượng item trong Adapter (tức là số lượng item trong ListView)
                        int itemCount = Danh_Sach_Tra.list.size();
                        txtThongKe.setText("Số sách đang mượn: " + itemCount);
                        arrayAdapter.notifyDataSetChanged();
                    }

                } else if (selectItem.equals("số lượng sách mượn nhiều nhất trong tháng")) {
                    // Kiểm tra xem Adapter có tồn tại không
                    if (Danh_Sach_Tra.list != null) {
                        TSach tSach = new TSach();

                        // Assuming that ngayM is a Date object, you may want to format it before using it
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
                        String formattedDate = dateFormat.format(tSach.ngayM);

                        // Get the item count for books borrowed in the given month
                        String itemCount = dbTraSach.ThongKeMuonTheoThang(formattedDate);

                        // Display the book borrowing statistics in a user-friendly message
                        txtThongKe.setText("Số lượng sách mượn nhiều nhất trong tháng " + formattedDate + ": " + itemCount);

                        // Assuming arrayAdapter is associated with a ListView, notify it of the data change
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
        data_t.add("số lượng sách mượn nhiều nhất trong tháng");
    }

    private void setControl() {
        spThongKe = findViewById(R.id.spThongKe);
        txtThongKe = findViewById(R.id.txtThongKe);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
