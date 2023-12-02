package com.example.quanlythuvien;

public class Sach {
    int imgPath;
    String MaSach, TenSach, SoLuong, GiaSach, TinhTS, NamXB;

    public Sach() {
    }

    public Sach(int imgPath, String maSach, String tenSach, String soLuong, String giaSach, String tinhTS, String namXB) {
        this.imgPath = imgPath;
        MaSach = maSach = "MS" + 1;
        TenSach = tenSach;
        SoLuong = soLuong;
        GiaSach = giaSach;
        TinhTS = tinhTS;
        NamXB = namXB;
    }

    public int getImgPath() {
        return imgPath;
    }

    public void setImgPath(int imgPath) {
        this.imgPath = imgPath;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getGiaSach() {
        return GiaSach;
    }

    public void setGiaSach(String giaSach) {
        GiaSach = giaSach;
    }

    public String getTinhTS() {
        return TinhTS;
    }

    public void setTinhTS(String tinhTS) {
        TinhTS = tinhTS;
    }

    public String getNamXB() {
        return NamXB;
    }

    public void setNamXB(String namXB) {
        NamXB = namXB;
    }
}
