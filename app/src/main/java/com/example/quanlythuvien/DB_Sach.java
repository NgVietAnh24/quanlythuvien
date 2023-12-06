package com.example.quanlythuvien;

import android.annotation.SuppressLint;
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
        String sql = "Create table tbSach(hinh blob, masach text, tensach text,  soluong integer, giasach integer, tinhts text, namxb integer)";
        sqLiteDatabase.execSQL(sql);

    }

    public void ThemDl(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbSach values(?,?,?,?,?,?,?)";
        sqLiteDatabase.execSQL(sql, new Object[]{s.getHinh(), s.getMaSach(), s.getTenSach(), s.getSoLuong(), s.getGiaSach(), s.getTinhTS(), s.getNamXB()});
    }

    public void XoaDl(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Delete from tbSach where masach=?";
        sqLiteDatabase.execSQL(sql, new Object[]{s.getMaSach()});
    }

    public void SuaDl(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Update tbSach set hinh = ?, tensach=?,  soluong=?, giasach=?,tinhts = ? , namxb=? where masach=?  ";
        sqLiteDatabase.execSQL(sql, new Object[]{s.getHinh(), s.getTenSach(), s.getSoLuong(), s.getGiaSach(), s.getTinhTS(), s.getNamXB(), s.getMaSach()});
    }

    public void SuaTra(Sach s) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "Update tbSach set soluong = soluong +1 where masach=?  ";
        sqLiteDatabase.execSQL(sql, new Object[]{s.getSoLuong(), s.getMaSach()});
    }

    public List<Sach> TimDLTheoTen(Sach s) {
        List<Sach> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM tbSach WHERE tensach LIKE ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{"%" + s.getTenSach() + "%"});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Sach sach = new Sach();
                sach.setHinh(cursor.getBlob(0));
                sach.setMaSach(cursor.getString(1));
                sach.setTenSach(cursor.getString(2));
                sach.setSoLuong(cursor.getInt(3));
                sach.setGiaSach(cursor.getDouble(4));
                sach.setTinhTS(cursor.getString(5));
                sach.setNamXB(cursor.getInt(6));
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
                sach.setHinh(cursor.getBlob(0));
                sach.setMaSach(cursor.getString(1));
                sach.setTenSach(cursor.getString(2));
                sach.setSoLuong(cursor.getInt(3));
                sach.setGiaSach(cursor.getDouble(4));
                sach.setTinhTS(cursor.getString(5));
                sach.setNamXB(cursor.getInt(6));
                data.add(sach);
            } while (cursor.moveToNext());
        }
        return data;
    }

    //     Thống kê số lượng sách cũ
    @SuppressLint("Range")
    public int getCountSachCu() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) as total FROM tbSach WHERE tinhts == 'Cũ'", null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndex("total"));
        }

        cursor.close();
        return count;
    }
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

    @SuppressLint("Range")
    public int getCountSachMoi() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) as total FROM tbSach WHERE tinhts == 'Mới'", null);

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(cursor.getColumnIndex("total"));
        }

        cursor.close();
        return count;
    }

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
    public void SuaMuon(String tenSach) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "UPDATE tbSach SET soluong = CASE WHEN soluong > 0 THEN soluong - 1 ELSE 0 END WHERE tensach = ?";
        sqLiteDatabase.execSQL(sql, new String[]{tenSach});
        sqLiteDatabase.close();
    }

    public void SuaTra(String tenSach) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "UPDATE tbSach SET soluong = soluong + 1 WHERE tensach = ?";
        sqLiteDatabase.execSQL(sql, new String[]{tenSach});
        sqLiteDatabase.close();
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
