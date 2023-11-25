package com.example.quanlythuvien;

import java.io.Serializable;

public class Sach_TS implements Serializable {
    String MaMS, TenDG, MaThe, NgayDT, NgayTT, MaSach, TinhTS;
    public Sach_TS(){}

    public Sach_TS(String maMS, String tenDG, String maThe, String ngayDT, String ngayTT, String maSach, String tinhTS) {
        MaMS = maMS;
        TenDG = tenDG;
        MaThe = maThe;
        NgayDT = ngayDT;
        NgayTT = ngayTT;
        MaSach = maSach;
        TinhTS = tinhTS;
    }

    public String getMaMS() {
        return MaMS;
    }

    public void setMaMS(String maMS) {
        MaMS = maMS;
    }

    public String getTenDG() {
        return TenDG;
    }

    public void setTenDG(String tenDG) {
        TenDG = tenDG;
    }

    public String getMaThe() {
        return MaThe;
    }

    public void setMaThe(String maThe) {
        MaThe = maThe;
    }

    public String getNgayDT() {
        return NgayDT;
    }

    public void setNgayDT(String ngayDT) {
        NgayDT = ngayDT;
    }

    public String getNgayTT() {
        return NgayTT;
    }

    public void setNgayTT(String ngayTT) {
        NgayTT = ngayTT;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getTinhTS() {
        return TinhTS;
    }

    public void setTinhTS(String tinhTS) {
        TinhTS = tinhTS;
    }

    @Override
    public String toString() {
        return "Sach_TS{" +
                "MaMS='" + MaMS + '\'' +
                ", TenDG='" + TenDG + '\'' +
                ", MaThe='" + MaThe + '\'' +
                ", NgayDT='" + NgayDT + '\'' +
                ", NgayTT='" + NgayTT + '\'' +
                ", MaSach='" + MaSach + '\'' +
                ", TinhTS='" + TinhTS + '\'' +
                '}';
    }
}
