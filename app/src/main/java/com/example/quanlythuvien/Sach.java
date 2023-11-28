package com.example.quanlythuvien;

public class Sach {
    String MaSach, TenSach, SoTrang, SoLuong, GiaSach, TinhTS, TacGia, TenNXB, NhanVien, NamXB;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String soTrang, String soLuong, String giaSach, String tacGia, String tinhTS, String tenNXB, String nhanVien, String namXB) {
        MaSach = maSach;
        TenSach = tenSach;
        SoTrang = soTrang;
        SoLuong = soLuong;
        GiaSach = giaSach;
        TinhTS = tinhTS;
        TacGia = tacGia;
        TenNXB = tenNXB;
        NhanVien = nhanVien;
        NamXB = namXB;
    }

    public String getTinhTS() {
        return TinhTS;
    }

    public void setTinhTS(String tinhTS) {
        TinhTS = tinhTS;
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

    public String getSoTrang() {
        return SoTrang;
    }

    public void setSoTrang(String soTrang) {
        SoTrang = soTrang;
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

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getTenNXB() {
        return TenNXB;
    }

    public void setTenNXB(String tenNXB) {
        TenNXB = tenNXB;
    }

    public String getNhanVien() {
        return NhanVien;
    }

    public void setNhanVien(String nhanVien) {
        NhanVien = nhanVien;
    }

    public String getNamXB() {
        return NamXB;
    }

    public void setNamXB(String namXB) {
        NamXB = namXB;
    }

    @Override
    public String toString() {
        return
                "Mã sách: " + MaSach + '\n' +
                        "Tên sách: " + TenSach + '\n' +
                        "Số trang: " + SoTrang + '\n' +
                        "Số lượng: " + SoLuong + '\n' +
                        "Giá sách: " + GiaSach + '\n' +
                        "Tác giả: " + TacGia + '\n' +
                        "Tình trạng: "+ TinhTS + '\n' +
                        "Tên NXB: " + TenNXB + '\n' +
                        "Nhân viên: " + NhanVien + '\n' +
                        "Năm xuất bản: " + NamXB + '\n';
    }
}
