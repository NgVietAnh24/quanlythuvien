package com.example.quanlythuvien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB_Users extends SQLiteOpenHelper {
    public DB_Users(Context context){ super(context,"dbUser",null,1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "Create table tbUser( username text, password text)";
        sqLiteDatabase.execSQL(sql);
    }
    public void ThemDl(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql ="insert into tbUSer values(?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{user.getUsername(),user.getPassword()});
    }
//    public void XoaDl(User user) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        String sql = "Delete from tbUser where id=?";
//        sqLiteDatabase.execSQL(sql, new String[]{user.getId()});
//    }
public List<User> XoaDL(User user) {
    List<User> data = new ArrayList<>();
    SQLiteDatabase sqLiteDatabase = getReadableDatabase();
    String sql = "Delete from tbUser where username=?";
    Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{user.getUsername()});

    if (cursor != null && cursor.moveToFirst()) {
        do {
            TSach tSach = new TSach();
            tSach.setMaMS(cursor.getString(0));
            tSach.setTenDG(cursor.getString(1));

            data.remove(tSach);
        } while (cursor.moveToNext());

        // Đóng Cursor sau khi sử dụng
        cursor.close();
    }

    return data;
}
    public void SuaDl(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql ="Update tbUser set  password=? where username=? ";
        sqLiteDatabase.execSQL(sql, new String[]{user.getPassword(),user.getUsername()});
    }
    public List<User> DocDL(){
        List<User> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql="Select * from tbUser";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                data.add(user);
            }while (cursor.moveToNext());
        }
        return data;
    }
    //Kiểm tra người dùng có tồn tại hay không
    public boolean isUserExists(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM tbUser WHERE username = ? AND password = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nếu có bản cập nhật cơ sở dữ liệu, thực hiện các thay đổi ở đây
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//        onCreate(db);
    }
}
