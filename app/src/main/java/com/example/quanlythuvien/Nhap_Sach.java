package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Nhap_Sach extends AppCompatActivity {
    EditText edtMaSach,
            edtTenSach,
            edtSoTrang,
            edtSoLuong,
            edtGia,
            edtTacGia,
            edtNXB;
    Spinner spTenNXB, spTinhTS,
            spNV;
    Button btnThem, btnXoa, btnSua, btnDanhSach;
    List<String> data_t = new ArrayList<>();
    List<String> data_nv = new ArrayList<>();
    List<String> data_ts = new ArrayList<>();
    ArrayAdapter adapter_t;
    ArrayAdapter adapter_nv;
    ArrayAdapter adapter_ts;
    ListView lvDanhSach;
    List<Sach> data_s = new ArrayList<>();
    static ArrayAdapter adapter_s;

    DB_Sach dbSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_sach);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbSach = new DB_Sach(this);
        adapter_s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_s);
        lvDanhSach.setAdapter(adapter_s);

        KhoiTao();
        adapter_t = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_t);
        spTenNXB.setAdapter(adapter_t);
        adapter_nv = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_nv);
        spNV.setAdapter(adapter_nv);
        adapter_ts = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data_ts);
        spTinhTS.setAdapter(adapter_ts);

        Sach sachs = new Sach();
        spTinhTS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sachs.setTinhTS(spTinhTS.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spTenNXB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sachs.setTenNXB(spTenNXB.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spNV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sachs.setNhanVien(spNV.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtMaSach.getText().length() <= 0) {
                    edtMaSach.setError("Nhập mã sách !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập mã sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtTenSach.getText().length() <= 0) {
                    edtTenSach.setError("Nhập tên sách !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập tên sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtSoTrang.getText().length() <= 0) {
                    edtSoTrang.setError("Nhập số trang !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập số trang", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtSoLuong.getText().length() <= 0) {
                    edtSoLuong.setError("Nhập số lượng !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtGia.getText().length() <= 0) {
                    edtGia.setError("Nhập giá sách !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập giá sách", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtTacGia.getText().length() <= 0) {
                    edtTacGia.setError("Nhập tác giả !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập tác giả", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtNXB.getText().length() <= 0) {
                    edtNXB.setError("Nhập năm xuất bản !!!");
                    Toast.makeText(Nhap_Sach.this, "Chưa nhập năm xuất bản", Toast.LENGTH_SHORT).show();
                    return;
                }

                sachs.setMaSach(edtMaSach.getText().toString());
                sachs.setTenSach(edtTenSach.getText().toString());
                sachs.setSoTrang(edtSoTrang.getText().toString());
                sachs.setSoLuong(edtSoLuong.getText().toString());
                sachs.setGiaSach(edtGia.getText().toString());
                sachs.setTacGia(edtTacGia.getText().toString());
                sachs.setNamXB(edtNXB.getText().toString());
                dbSach.ThemDl(sachs);
                Toast.makeText(Nhap_Sach.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                //Làm mới sau khi thêm sách
                edtMaSach.setText("");
                edtTenSach.setText("");
                edtSoTrang.setText("");
                edtSoLuong.setText("");
                edtGia.setText("");
                edtTacGia.setText("");
                spTinhTS.setSelection(0);
                spTenNXB.setSelection(0);
                spNV.setSelection(0);
                edtNXB.setText("");
                edtMaSach.requestFocus();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach = new Sach();
                sach.setMaSach(edtMaSach.getText().toString());
                dbSach.XoaDl(sach);
                Toast.makeText(Nhap_Sach.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sach sach = new Sach();
                sach.setMaSach(edtMaSach.getText().toString());
                sach.setTenSach(edtTenSach.getText().toString());
                sach.setSoTrang(edtSoTrang.getText().toString());
                sach.setSoLuong(edtSoLuong.getText().toString());
                sach.setGiaSach(edtGia.getText().toString());
                sach.setTacGia(edtTacGia.getText().toString());
                sach.setTinhTS(spTinhTS.getSelectedItem().toString());
                sach.setTenNXB(spTenNXB.getSelectedItem().toString());
                sach.setNhanVien(spNV.getSelectedItem().toString());
                sach.setNamXB(edtNXB.getText().toString());

                dbSach.SuaDl(sach);
                Toast.makeText(Nhap_Sach.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Sach sach = data_s.get(i);
                edtMaSach.setText(sach.getMaSach());
                edtTenSach.setText(sach.getTenSach());
                edtSoTrang.setText(sach.getSoTrang());
                edtSoLuong.setText(sach.getSoLuong());
                edtGia.setText(sach.getGiaSach());
                edtTacGia.setText(sach.getTacGia());
                edtNXB.setText(sach.getNamXB());
            }
        });
        btnDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_s.clear();
                data_s.addAll(dbSach.DocDL());
                adapter_s.notifyDataSetChanged();
            }
        });
    }

    private void KhoiTao() {
        data_t.add("Nguyễn Thành Chung");
        data_t.add("Phạm Hữu Khải");
        data_t.add("Trần Kim Xuyến");
        data_nv.add("Nguyễn Văn Anh");
        data_nv.add("Phạm Văn Nhật");
        data_nv.add("Ngô Như Tuyết");
        data_ts.add("Cũ");
        data_ts.add("Mới");

    }

    private void setControl() {
        edtMaSach = findViewById(R.id.edtMaSach);
        edtTenSach = findViewById(R.id.edtTenSach);
        edtSoTrang = findViewById(R.id.edtSoTrang);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        edtGia = findViewById(R.id.edtGia);
        edtTacGia = findViewById(R.id.edtTacGia);
        edtNXB = findViewById(R.id.edtNXB);
        spTenNXB = findViewById(R.id.spTenNXB);
        spNV = findViewById(R.id.spNV);
        spTinhTS = findViewById(R.id.spTinhTS);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnDanhSach = findViewById(R.id.btnDanhSach);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnBack) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
