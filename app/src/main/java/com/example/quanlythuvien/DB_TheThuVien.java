package com.example.quanlythuvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_TheThuVien extends SQLiteOpenHelper {
    public DB_TheThuVien(Context context) {
        super(context, "dbTheTV", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table tbTheTV(hinh blob, hoten text, mathe text, ngaysinh text,gioitinh text, diachi text, email text, sodt text)";
        sqLiteDatabase.execSQL(sql);

    }

    public void ThemDl(TheTV theTV) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbTheTV values(?,?,?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{theTV.getHinh(),theTV.getHoTen(), theTV.getMaThe(), theTV.getNgaySinh(),theTV.getGioiTinh(), theTV.getDiaChi(), theTV.getEmail(), theTV.getSoDT()});
    }
    public List<TheTV> XoaDL(TheTV theTV) {
        List<TheTV> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Delete from tbTheTV where mathe=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{theTV.getMaThe()});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TheTV t = new TheTV();
                t.setHinh(cursor.getBlob(0));
                t.setHoTen(cursor.getString(1));
                t.setMaThe(cursor.getString(2));
                t.setNgaySinh(cursor.getString(3));
                t.setGioiTinh(cursor.getString(4));
                t.setDiaChi(cursor.getString(5));
                t.setEmail(cursor.getString(6));
                t.setSoDT(cursor.getString(7));
                data.remove(t);
            } while (cursor.moveToNext());

            // Đóng Cursor sau khi sử dụng
            cursor.close();
        }

        return data;
    }

    public List<TheTV> SuaDL(TheTV theTV) {
        List<TheTV> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Update tbTheTV set hinh = ?, hoten = ?, ngaysinh = ?,gioitinh = ?, diachi = ?, email = ?, sodt = ? where mathe = ?";
        sqLiteDatabase.execSQL(sql, new Object[]{theTV.getHinh(),theTV.getHoTen(), theTV.getNgaySinh(),theTV.getGioiTinh(), theTV.getDiaChi(), theTV.getEmail(), theTV.getSoDT(), theTV.getMaThe()});



        return data;
    }


    public List<TheTV> DocDL() {
        List<TheTV> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Select * from tbTheTV";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TheTV t = new TheTV();
                t.setHinh(cursor.getBlob(0));
                t.setHoTen(cursor.getString(1));
                t.setMaThe(cursor.getString(2));
                t.setNgaySinh(cursor.getString(3));
                t.setGioiTinh(cursor.getString(4));
                t.setDiaChi(cursor.getString(5));
                t.setEmail(cursor.getString(6));
                t.setSoDT(cursor.getString(7));
                data.add(t);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
