package com.example.quanlythuvien;

import java.io.Serializable;

public class Sach implements Serializable {

    int SoLuong, NamXB;
    byte[] Hinh;
    double GiaSach;
    String TenSach,MaSach, TinhTS;

    public Sach() {
    }

    public Sach( byte[] hinh,String maSach,String tenSach, int soLuong, double giaSach,  String tinhTS, int namXB) {
        Hinh = hinh;
        MaSach = maSach;
        TenSach = tenSach;
        SoLuong = soLuong;
        GiaSach = giaSach;
        TinhTS = tinhTS;
        NamXB = namXB;

    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getNamXB() {
        return NamXB;
    }

    public void setNamXB(int namXB) {
        NamXB = namXB;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }

    public double getGiaSach() {
        return GiaSach;
    }

    public void setGiaSach(double giaSach) {
        GiaSach = giaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getTinhTS() {
        return TinhTS;
    }

    public void setTinhTS(String tinhTS) {
        TinhTS = tinhTS;
    }
}


