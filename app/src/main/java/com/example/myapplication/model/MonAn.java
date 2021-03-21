package com.example.myapplication.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MonAn {
    private String maMonAn;
    private String maTheLoai;
    private String tenMonAn;
    private String moTa;
    private String chatLuong;
    private double giaBan;
    private int soLuong;

    public MonAn() {
    }

    public MonAn(String maMonAn, String maTheLoai, String tenMonAn, String moTa, String chatLuong, double giaBan, int soLuong) {
        this.maMonAn = maMonAn;
        this.maTheLoai = maTheLoai;
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.chatLuong = chatLuong;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public String getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getChatLuong() {
        return chatLuong;
    }

    public void setChatLuong(String chatLuong) {
        this.chatLuong = chatLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "maMonAn='" + maMonAn + '\'' +
                ", maTheLoai='" + maTheLoai + '\'' +
                ", tenMonAn='" + tenMonAn + '\'' +
                ", moTa='" + moTa + '\'' +
                ", chatLuong='" + chatLuong + '\'' +
                ", giaBan=" + giaBan +
                ", soLuong=" + soLuong +
                '}';
    }
}