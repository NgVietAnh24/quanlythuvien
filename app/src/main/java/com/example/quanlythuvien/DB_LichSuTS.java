package com.example.quanlythuvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_LichSuTS extends SQLiteOpenHelper {
    public DB_LichSuTS(Context context) {
        super(context, "dbLichSu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table tbLichSu(mams text, tendg text, mathe text, ngaydt text, ngaytt text, masach text, tinhts text)";
        sqLiteDatabase.execSQL(sql);

    }

    public void ThemDl(TSach tSach) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbLichSu values(?,?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{tSach.getMaMS(), tSach.getTenDG(), tSach.getMaThe(), tSach.getNgayDT(), tSach.getNgayTT(), tSach.getMaSach(), tSach.getTinhTS()});
    }
    public List<TSach> XoaDL(TSach t) {
        List<TSach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Delete from tbLichSu where mams=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{t.getMaMS()});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TSach tSach = new TSach();
                tSach.setMaMS(cursor.getString(0));
                tSach.setTenDG(cursor.getString(1));
                tSach.setMaThe(cursor.getString(2));
                tSach.setNgayDT(cursor.getString(3));
                tSach.setNgayTT(cursor.getString(4));
                tSach.setMaSach(cursor.getString(5));
                tSach.setTinhTS(cursor.getString(6));

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
        String sql = "Update tbLichSu set tendg=?, mathe=?, ngaydt=?, ngaytt=?, masach=?,tinhts=? where mams=?  ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{t.getTenDG(), t.getMaThe(), t.getNgayDT(), t.getNgayTT(), t.getMaSach(), t.getTinhTS(), t.getMaMS()});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                TSach tSach = new TSach();
                tSach.setMaMS(cursor.getString(0));
                tSach.setTenDG(cursor.getString(1));
                tSach.setMaThe(cursor.getString(2));
                tSach.setNgayDT(cursor.getString(3));
                tSach.setNgayTT(cursor.getString(4));
                tSach.setMaSach(cursor.getString(5));
                tSach.setTinhTS(cursor.getString(6));

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
        String sql = "Select * from tbLichSu";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                TSach tSach = new TSach();
                tSach.setMaMS(cursor.getString(0));
                tSach.setTenDG(cursor.getString(1));
                tSach.setMaThe(cursor.getString(2));
                tSach.setNgayDT(cursor.getString(3));
                tSach.setNgayTT(cursor.getString(4));
                tSach.setMaSach(cursor.getString(5));
                tSach.setTinhTS(cursor.getString(6));

                data.add(tSach);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}