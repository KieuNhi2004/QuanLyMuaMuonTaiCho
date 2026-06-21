/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Embeddable
public class ChitietphieunhapPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MaPhieuNhap")
    private int maPhieuNhap;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaSanPham")
    private int maSanPham;

    public ChitietphieunhapPK() {
    }

    public ChitietphieunhapPK(int maPhieuNhap, int maSanPham) {
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
    }

    public int getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(int maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maPhieuNhap;
        hash += (int) maSanPham;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitietphieunhapPK)) {
            return false;
        }
        ChitietphieunhapPK other = (ChitietphieunhapPK) object;
        if (this.maPhieuNhap != other.maPhieuNhap) {
            return false;
        }
        if (this.maSanPham != other.maSanPham) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.ChitietphieunhapPK[ maPhieuNhap=" + maPhieuNhap + ", maSanPham=" + maSanPham + " ]";
    }
    
}
