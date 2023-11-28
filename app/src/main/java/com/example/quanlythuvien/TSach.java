package com.example.quanlythuvien;

import java.io.Serializable;

public class TSach implements Serializable {
    String maMS, tenDG, tenSach, maThe, ngayDT, ngayTT, maSach, tinhTS;

    public TSach() {
    }
    public TSach(String maMS, String tenDG, String tenSach, String maThe, String ngayDT, String ngayTT, String maSach, String tinhTS) {
        this.maMS = maMS;
        this.tenDG = tenDG;
        this.tenSach = tenSach;
        this.maThe = maThe;
        this.ngayDT = ngayDT;
        this.ngayTT = ngayTT;
        this.maSach = maSach;
        this.tinhTS = tinhTS;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaMS() {
        return maMS;
    }

    public void setMaMS(String maMS) {
        this.maMS = maMS;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

    public String getMaThe() {
        return maThe;
    }

    public void setMaThe(String maThe) {
        this.maThe = maThe;
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

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTinhTS() {
        return tinhTS;
    }

    public void setTinhTS(String tinhTS) {
        this.tinhTS = tinhTS;
    }

    @Override
    public String toString() {
        return
                "Mã mượn sách: " + maMS + '\'' +
                        "Tên độc giả: " + tenDG + '\'' +
                        "Mã thẻ: " + maThe + '\'' +
                        "Ngày dự trả" + ngayDT + '\'' +
                        "Ngày thực trả: " + ngayTT + '\'' +
                        "Mã sách" + maSach + '\'' +
                        "Tình trạng sách: " + tinhTS;

    }
}
