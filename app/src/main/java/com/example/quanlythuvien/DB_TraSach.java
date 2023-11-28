package com.example.quanlythuvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_TraSach extends SQLiteOpenHelper {
    public DB_TraSach(Context context) {
        super(context, "dbTSach", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table tbTSach(mams text, tendg text,tensach text, mathe text, ngaydt text, ngaytt text, masach text, tinhts text)";
        sqLiteDatabase.execSQL(sql);

    }

    public void ThemDl(TSach tSach) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbTSach values(?,?,?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{tSach.getMaMS(), tSach.getTenDG(), tSach.getTenSach(), tSach.getMaThe(), tSach.getNgayDT(), tSach.getNgayTT(), tSach.getMaSach(), tSach.getTinhTS()});
    }
    public List<TSach> XoaDL(TSach t) {
        List<TSach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Delete from tbTSach where mams=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{t.getMaMS()});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TSach tSach = new TSach();
                tSach.setMaMS(cursor.getString(0));
                tSach.setTenDG(cursor.getString(1));
                tSach.setTenSach(cursor.getString(2));
                tSach.setMaThe(cursor.getString(3));
                tSach.setNgayDT(cursor.getString(4));
                tSach.setNgayTT(cursor.getString(5));
                tSach.setMaSach(cursor.getString(6));
                tSach.setTinhTS(cursor.getString(7));

                data.remove(tSach);
            } while (cursor.moveToNext());

            // Đóng Cursor sau khi sử dụng
            cursor.close();
        }

        return data;
    }

    public List<TSach> SuaDL(TSach t) {
        List<TSach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Update tbTSach set tendg=?, mathe=?, ngaydt=?, ngaytt=?, masach=?,tinhts=? where mams=?  ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{t.getTenDG(), t.getMaThe(), t.getNgayDT(), t.getNgayTT(), t.getMaSach(), t.getTinhTS(), t.getMaMS()});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TSach tSach = new TSach();
                tSach.setMaMS(cursor.getString(0));
                tSach.setTenDG(cursor.getString(1));
                tSach.setTenSach(cursor.getString(2));
                tSach.setMaThe(cursor.getString(3));
                tSach.setNgayDT(cursor.getString(4));
                tSach.setNgayTT(cursor.getString(5));
                tSach.setMaSach(cursor.getString(6));
                tSach.setTinhTS(cursor.getString(7));

                data.add(tSach);
            } while (cursor.moveToNext());

            // Đóng Cursor sau khi sử dụng
            cursor.close();
        }

        return data;
    }


    public List<TSach> DocDL() {
        List<TSach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Select * from tbTSach";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TSach tSach = new TSach();
                tSach.setMaMS(cursor.getString(0));
                tSach.setTenDG(cursor.getString(1));
                tSach.setTenSach(cursor.getString(2));
                tSach.setMaThe(cursor.getString(3));
                tSach.setNgayDT(cursor.getString(4));
                tSach.setNgayTT(cursor.getString(5));
                tSach.setMaSach(cursor.getString(6));
                tSach.setTinhTS(cursor.getString(7));

                data.add(tSach);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}