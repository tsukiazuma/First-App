package com.example.myapplication.model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class HoaDonChiTiet {
    private int maHDCT;
    private HoaDon hoaDon;
    private MonAn monAn;
    private int soLuongMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, MonAn monAn, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.monAn = monAn;
        this.soLuongMua = soLuongMua;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public void setSach(MonAn monAn) {
        this.monAn = monAn;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon.toString() +
                ", monAn=" + monAn.toString() +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
