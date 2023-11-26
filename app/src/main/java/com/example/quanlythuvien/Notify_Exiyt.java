package com.example.quanlythuvien;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Notify_Exiyt {

        public static void showLogoutConfirmationDialog(Context context, final OnLogoutConfirmedListener listener) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xác nhận đăng xuất")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Người dùng chọn "Có"
                            if (listener != null) {
                                listener.onLogoutConfirmed();
                            }
                        }
                    })
                    .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Người dùng chọn "Không"
                            // Đóng hộp thoại
                            dialog.dismiss();
                        }
                    });
            // Tạo và hiển thị AlertDialog
            builder.create().show();
        }

        public interface OnLogoutConfirmedListener {
            void onLogoutConfirmed();

    }
}
