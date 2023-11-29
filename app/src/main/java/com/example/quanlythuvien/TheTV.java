package com.example.quanlythuvien;

import java.io.Serializable;

public class TheTV implements Serializable {
    String hoTen, maThe,ngaySinh,gioiTinh, diaChi, email, soDT;
    public TheTV(){}

    public TheTV( String gioiTinh, String hoTen, String maThe, String ngaySinh, String diaChi, String email, String soDT) {
        this.gioiTinh = gioiTinh;
        this.hoTen = hoTen;
        this.maThe = maThe;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        this.soDT = soDT;
    }

//    public String getHinhNV() {
//        return hinhNV;
//    }
//
//    public void setHinhNV(String hinhNV) {
//        this.hinhNV = hinhNV;
//    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "TheTV{" +
                ", hoTen='" + hoTen + '\'' +
                ", maThe='" + maThe + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                "Giới tính: "+gioiTinh+"\n"+
                ", diaChi='" + diaChi + '\'' +
                ", email='" + email + '\'' +
                ", soDT='" + soDT + '\'' +
                '}';
    }
}
