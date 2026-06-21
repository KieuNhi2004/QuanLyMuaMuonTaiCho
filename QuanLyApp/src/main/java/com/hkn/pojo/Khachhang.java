/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "khachhang")
@NamedQueries({
    @NamedQuery(name = "Khachhang.findAll", query = "SELECT k FROM Khachhang k"),
    @NamedQuery(name = "Khachhang.findByMaKhachHang", query = "SELECT k FROM Khachhang k WHERE k.maKhachHang = :maKhachHang")})
public class Khachhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaKhachHang")
    private Integer maKhachHang;
    @JoinColumn(name = "MaBan", referencedColumnName = "MaBan")
    @OneToOne
    private Banan maBan;
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    @OneToOne
    private Nguoidung maNguoiDung;

    public Khachhang() {
    }

    public Khachhang(Integer maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Integer getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(Integer maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Banan getMaBan() {
        return maBan;
    }

    public void setMaBan(Banan maBan) {
        this.maBan = maBan;
    }

    public Nguoidung getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(Nguoidung maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maKhachHang != null ? maKhachHang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khachhang)) {
            return false;
        }
        Khachhang other = (Khachhang) object;
        if ((this.maKhachHang == null && other.maKhachHang != null) || (this.maKhachHang != null && !this.maKhachHang.equals(other.maKhachHang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Khachhang[ maKhachHang=" + maKhachHang + " ]";
    }
    
}
