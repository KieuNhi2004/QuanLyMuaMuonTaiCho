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
public class ChitietdonhangPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "MaDonHang")
    private int maDonHang;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MaSanPham")
    private int maSanPham;

    public ChitietdonhangPK() {
    }

    public ChitietdonhangPK(int maDonHang, int maSanPham) {
        this.maDonHang = maDonHang;
        this.maSanPham = maSanPham;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
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
        hash += (int) maDonHang;
        hash += (int) maSanPham;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitietdonhangPK)) {
            return false;
        }
        ChitietdonhangPK other = (ChitietdonhangPK) object;
        if (this.maDonHang != other.maDonHang) {
            return false;
        }
        if (this.maSanPham != other.maSanPham) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.ChitietdonhangPK[ maDonHang=" + maDonHang + ", maSanPham=" + maSanPham + " ]";
    }
    
}
