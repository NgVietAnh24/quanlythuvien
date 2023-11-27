package com.example.quanlythuvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_Sach extends SQLiteOpenHelper {
    public DB_Sach(Context context) {
        super(context, "dbSach", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table tbSach(masach text, tensach text, sotrang text, soluong text, giasach text, tacgia text, tennxb text, tennv text, namxb text)";
        sqLiteDatabase.execSQL(sql);

    }

    public void ThemDl(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbSach values(?,?,?,?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{s.getMaSach(), s.getTenSach(), s.getSoTrang(), s.getSoLuong(), s.getGiaSach(), s.getTacGia(), s.getTenNXB(), s.getNhanVien(), s.getNamXB()});
    }

    public void XoaDl(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Delete from tbSach where masach=?";
        sqLiteDatabase.execSQL(sql, new String[]{s.getMaSach()});
    }

    public void SuaDl(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Update tbSach set tensach=?, sotrang=?, soluong=?, giasach=?, tacgia=?,tennxb=?, tennv = ?, namxb=? where masach=?  ";
        sqLiteDatabase.execSQL(sql, new String[]{s.getTenSach(), s.getSoTrang(), s.getSoLuong(), s.getGiaSach(), s.getTacGia(), s.getTenNXB(), s.getNhanVien(), s.getNamXB(), s.getMaSach()});
    }

    public List<Sach> TimDLTheoTen(Sach s) {
        List<Sach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbSach WHERE tensach LIKE ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{"%" + s.getTenSach() + "%"});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Sach sach = new Sach();
                sach.setMaSach(cursor.getString(0));
                sach.setTenSach(cursor.getString(1));
                sach.setSoTrang(cursor.getString(2));
                sach.setSoLuong(cursor.getString(3));
                sach.setGiaSach(cursor.getString(4));
                sach.setTacGia(cursor.getString(5));
                sach.setTenNXB(cursor.getString(6));
                sach.setNhanVien(cursor.getString(7));
                sach.setNamXB(cursor.getString(8));
                data.add(sach);
            } while (cursor.moveToNext());

            // Đóng Cursor sau khi sử dụng
            cursor.close();
        }

        return data;
    }

    public List<Sach> DocDL() {
        List<Sach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "Select * from tbSach";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Sach sach = new Sach();
                sach.setMaSach(cursor.getString(0));
                sach.setTenSach(cursor.getString(1));
                sach.setSoTrang(cursor.getString(2));
                sach.setSoLuong(cursor.getString(3));
                sach.setGiaSach(cursor.getString(4));
                sach.setTacGia(cursor.getString(5));
                sach.setTenNXB(cursor.getString(6));
                sach.setNhanVien(cursor.getString(7));
                sach.setNamXB(cursor.getString(8));
                data.add(sach);
            } while (cursor.moveToNext());
        }
        return data;
    }

    // Thống kê số lượng sách cũ
//    @SuppressLint("Range")
//    public int getCountSachCu() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) as total FROM tbSach WHERE namxb < '2022-04-10'", null);
//
//        int count = 0;
//        if (cursor.moveToFirst()) {
//            count = cursor.getInt(cursor.getColumnIndex("total"));
//        }
//
//        cursor.close();
//        return count;
//    }
//    public int getCountSachCu() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT COUNT(*) FROM tbSach WHERE isNew = 0";
//        Cursor cursor = db.rawQuery(query, null);
//
//        int count = 0;
//        if (cursor.moveToFirst()) {
//            count = cursor.getInt(0);
//        }
//
//        cursor.close();
//        return count;
//    }

    //    @SuppressLint("Range")
//    public int getCountSachMoi() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT COUNT(*) as total FROM tbTSach WHERE namxb > '2022-04-10'", null);
//
//        int count = 0;
//        if (cursor.moveToFirst()) {
//            count = cursor.getInt(cursor.getColumnIndex("total"));
//        }
//
//        cursor.close();
//        return count;
//    }
// Phương thức để đếm số lượng sách mới
//    public int getCountSachMoi() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT COUNT(*) FROM tbSach WHERE isNew = 1";
//        Cursor cursor = db.rawQuery(query, null);
//
//        int count = 0;
//        if (cursor.moveToFirst()) {
//            count = cursor.getInt(0);
//        }
//
//        cursor.close();
//        return count;
//    }



    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
