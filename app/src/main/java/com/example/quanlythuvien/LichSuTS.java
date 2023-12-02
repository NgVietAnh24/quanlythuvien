package com.example.quanlythuvien;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LichSuTS extends AppCompatActivity {
    ListView lvLichSu;

    static List<TSach> data_ls = new ArrayList<>();

    static AdapterSach adapter_ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_ts);
        setControl();
        setEvent();
    }

    private void setEvent() {

//        adapter_ls = new AdapterSach(this, R.layout.activity_sach_item, data_ls);
//        lvLichSu.setAdapter(adapter_ls);

//        lvLichSu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Intent intent = new Intent(LichSuTS.this,Edit_ND.class);
////                intent.putExtra("item",data_u.get(i));
////                startActivity(intent);
//            }
//        });

        lvLichSu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_ls.remove(i);
//                adapter_ls.updateData(data_ls);
                return false;
            }
        });
    }


    private void setControl() {
        lvLichSu = findViewById(R.id.lvLichSu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_manu,menu);
        return super.onCreateOptionsMenu(menu);
    }


}

