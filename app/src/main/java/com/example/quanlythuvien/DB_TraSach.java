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
        String sql = "Create table tbTSach(mams text, tendg text,tensach text,hinh blob, mathe text,soluong integer,giasach integer, ngaym text, ngaydt text, ngaytt text, tinhts text, hientrang text)";
        sqLiteDatabase.execSQL(sql);

    }

    public void ThemDl(TSach tSach) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbTSach values(?,?,?,?,?,?,?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{tSach.getMaMS(), tSach.getTenDG(), tSach.getTenSach(), tSach.getHinh(), tSach.getMaThe(), tSach.getSoLuong(), tSach.getGiaSach(), tSach.getNgayM(), tSach.getNgayDT(), tSach.getNgayTT(), tSach.getTinhTS(), tSach.getSpHienTrang()});
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
                tSach.setHinh(cursor.getBlob(3));
                tSach.setMaThe(cursor.getString(4));
                tSach.setSoLuong(cursor.getInt(5));
                tSach.setGiaSach(cursor.getDouble(6));
                tSach.setNgayM(cursor.getString(7));
                tSach.setNgayDT(cursor.getString(8));
                tSach.setNgayTT(cursor.getString(9));
                tSach.setTinhTS(cursor.getString(10));
                tSach.setSpHienTrang(cursor.getString(11));
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
        String sql = "Update tbTSach set tendg=?,tensach = ?,hinh = ?, mathe=?,soluong = ?,giasach = ?, ngaym = ?, ngaydt=?, ngaytt=?,tinhts=?,hientrang = ? where mams=?  ";
        sqLiteDatabase.execSQL(sql, new Object[]{t.getTenDG(), t.getTenSach(), t.getHinh(), t.getMaThe(), t.getSoLuong(), t.getGiaSach(), t.getNgayM(), t.getNgayDT(), t.getNgayTT(), t.getTinhTS(), t.getSpHienTrang(), t.getMaMS()});
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
                tSach.setHinh(cursor.getBlob(3));
                tSach.setMaThe(cursor.getString(4));
                tSach.setSoLuong(cursor.getInt(5));
                tSach.setGiaSach(cursor.getDouble(6));
                tSach.setNgayM(cursor.getString(7));
                tSach.setNgayDT(cursor.getString(8));
                tSach.setNgayTT(cursor.getString(9));
                tSach.setTinhTS(cursor.getString(10));
                tSach.setSpHienTrang(cursor.getString(11));
                data.add(tSach);
            } while (cursor.moveToNext());
        }
        return data;
    }

//    public List<TSach> ThongKeMuonTheoThang(String ngayThangNam) {
//        List<TSach> data = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//
//        String sql = "SELECT tensach, COUNT(*) AS soluong " +
//                "FROM tbTSach " +
//                "WHERE SUBSTR(ngaym, 1, 7) = SUBSTR(?, 1, 7) " +
//                "GROUP BY tensach " +
//                "ORDER BY soluong DESC";
//
//        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{ngayThangNam});
//
//        if (cursor.moveToFirst()) {
//            do {
//                TSach tSach = new TSach();
//                tSach.setTenSach(cursor.getString(cursor.getColumnIndex("tensach")-1));
//                tSach.setSoLuong(cursor.getInt(cursor.getInt(Integer.parseInt("soluong"))));
//                data.add(tSach);
//            } while (cursor.moveToNext());
//        }
//
//        // Đóng Cursor sau khi sử dụng
//        cursor.close();
//
//        return data;
//    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}