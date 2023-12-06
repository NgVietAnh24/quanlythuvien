package com.example.quanlythuvien;

import java.io.Serializable;

public class TSach implements Serializable {
    int soLuong;
    Double giaSach;
    byte[] hinh;
    String  maMS, tenDG,maThe, tenSach,  ngayDT, ngayTT,  tinhTS,spHienTrang;

    public TSach() {
    }

    public TSach(String maMS, String tenDG,String tenSach, byte[] hinh, String maThe, int soLuong, Double giaSach, String tinhTS,  String ngayDT, String ngayTT, String spHienTrang) {
        this.maMS = maMS;
        this.maThe = maThe;
        this.soLuong = soLuong;
        this.giaSach = giaSach;
        this.hinh = hinh;
        this.tenDG = tenDG;
        this.tenSach = tenSach;
        this.ngayDT = ngayDT;
        this.ngayTT = ngayTT;
        this.tinhTS = tinhTS;
        this.spHienTrang = spHienTrang;
    }

    public String getMaMS() {
        return maMS;
    }

    public void setMaMS(String maMS) {
        this.maMS = maMS;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGiaSach() {
        return giaSach;
    }

    public void setGiaSach(Double giaSach) {
        this.giaSach = giaSach;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNgayDT() {
        return ngayDT;
    }

    public void setNgayDT(String ngayDT) {
        this.ngayDT = ngayDT;
    }

    public String getNgayTT() {
        return ngayTT;
    }

    public void setNgayTT(String ngayTT) {
        this.ngayTT = ngayTT;
    }

    public String getTinhTS() {
        return tinhTS;
    }

    public void setTinhTS(String tinhTS) {
        this.tinhTS = tinhTS;
    }

    public String getSpHienTrang() {
        return spHienTrang;
    }

    public void setSpHienTrang(String spHienTrang) {
        this.spHienTrang = spHienTrang;
    }
}
