//package com.example.quanlythuvien;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChiTietSach extends AppCompatActivity {
//    EditText
//            edtTenDG,
//            edtMaThe,
//            edtNgayDT,
//            edtNgayTT,
//            edtMaSach;
//    Spinner spTinhTS;
//
//    TextView tvMaMS;
//    List<String> data_ls = new ArrayList<>();
//    ArrayAdapter adapter_ls;
//    Button btnSua,btnXoa,btnQuayLai;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chitiet_sach);
//        setControl();
//        setEvent();
//    }
//
//    @SuppressLint("SuspiciousIndentation")
//    private void setEvent() {
//        Sach_TS sach_ts = (Sach_TS) getIntent().getSerializableExtra("item");
//        tvMaMS.setText(sach_ts.getMaMS());
//        edtTenDG.setText(sach_ts.getTenDG());
//        edtMaThe.setText(sach_ts.getMaThe());
//        edtNgayDT.setText(sach_ts.getNgayDT());
//        edtNgayTT.setText(sach_ts.getNgayTT());
//        edtMaSach.setText(sach_ts.getMaSach());
//        if (sach_ts.getTinhTS().equals("Cũ"));
//            spTinhTS.setSelection(0);
//        if (sach_ts.getTinhTS().equals("Mới"));
//            spTinhTS.setSelection(1);
//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (Sach_TS item : Danh_Sach_Tra.data_s){
//                    if(item.getMaMS().equals(sach_ts.getMaMS())){
//                        item.setTenDG(edtTenDG.getText().toString());
//                        item.setMaThe(edtMaThe.getText().toString());
//                        item.setNgayDT(edtNgayDT.getText().toString());
//                        item.setNgayTT(edtNgayTT.getText().toString());
//                        item.setMaSach(edtMaSach.getText().toString());
//                        item.setTinhTS(spTinhTS.getSelectedItem().toString());
//                        Toast.makeText(ChiTietSach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                Danh_Sach_Tra.adapter_sach.notifyDataSetChanged();
//                onBackPressed();
//            }
//        });
//        btnXoa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for (Sach_TS item : Danh_Sach_Tra.data_s){
//                    if(item.getMaMS().equals(sach_ts.getMaMS())){
//                        Danh_Sach_Tra.data_s.remove(item);
//                        Toast.makeText(ChiTietSach.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
//                Danh_Sach_Tra.adapter_sach.notifyDataSetChanged();
//                onBackPressed();
//            }
//        });
//    }
//
//    private void setControl() {
//        tvMaMS = findViewById(R.id.tvMaMS);
//        edtTenDG = findViewById(R.id.edtTenDG);
//        edtMaThe = findViewById(R.id.edtMaThe);
//        edtNgayDT = findViewById(R.id.edtNgayDT);
//        edtNgayTT = findViewById(R.id.edtNgayTT);
//        edtMaSach = findViewById(R.id.edtMaSach);
//        spTinhTS = findViewById(R.id.spTinhTS);
//        btnSua = findViewById(R.id.btnSua);
//        btnQuayLai = findViewById(R.id.btnQuayLai);
//        btnXoa = findViewById(R.id.btnXoa);
//    }
//}
