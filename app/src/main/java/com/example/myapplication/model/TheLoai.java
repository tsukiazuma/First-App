package com.example.myapplication.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TheLoai {
    private String maTheloai;
    private String tenTheloai;
    private String moTa;
    private String diaDiem;

    public TheLoai() {
    }

    public TheLoai(String maTheloai, String tenTheloai, String moTa, String diaDiem) {
        this.maTheloai = maTheloai;
        this.tenTheloai = tenTheloai;
        this.moTa = moTa;
        this.diaDiem = diaDiem;
    }

    public String getMaTheloai() {
        return maTheloai;
    }

    public void setMaTheloai(String maTheloai) {
        this.maTheloai = maTheloai;
    }

    public String getTenTheloai() {
        return tenTheloai;
    }

    public void setTenTheloai(String tenTheloai) {
        this.tenTheloai = tenTheloai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    @Override
    public String toString() {
        return getMaTheloai() + " | " + getTenTheloai();
    }
}