/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "donhang")
@NamedQueries({
    @NamedQuery(name = "Donhang.findAll", query = "SELECT d FROM Donhang d"),
    @NamedQuery(name = "Donhang.findByMaDonHang", query = "SELECT d FROM Donhang d WHERE d.maDonHang = :maDonHang"),
    @NamedQuery(name = "Donhang.findByTrangThai", query = "SELECT d FROM Donhang d WHERE d.trangThai = :trangThai"),
    @NamedQuery(name = "Donhang.findByTongTien", query = "SELECT d FROM Donhang d WHERE d.tongTien = :tongTien"),
    @NamedQuery(name = "Donhang.findByThoiGianBatDau", query = "SELECT d FROM Donhang d WHERE d.thoiGianBatDau = :thoiGianBatDau")})
public class Donhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaDonHang")
    private Integer maDonHang;
    @Size(max = 20)
    @Column(name = "TrangThai")
    private String trangThai;
    @Size(max = 255)
    @Column(name = "GhiChu")
    private String ghiChu;
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @Column(name = "ThoiGianBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianBatDau;
    @JoinColumn(name = "MaBan", referencedColumnName = "MaBan")
    @ManyToOne
    private Banan maBan;
    @JoinColumn(name = "MaNhanVien", referencedColumnName = "MaNhanVien")
    @ManyToOne
    private Nhanvien maNhanVien;
    @OneToOne(mappedBy = "maDonHang")
    private Hoadon hoadon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donhang")
    private Set<Chitietdonhang> chitietdonhangSet;
    @OneToMany(mappedBy = "maDonHang")
    private Set<Nhatkychinhsuadon> nhatkychinhsuadonSet;

    public Donhang() {
    }

    public Donhang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Integer getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Integer maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Banan getMaBan() {
        return maBan;
    }

    public void setMaBan(Banan maBan) {
        this.maBan = maBan;
    }

    public Nhanvien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Nhanvien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Hoadon getHoadon() {
        return hoadon;
    }

    public void setHoadon(Hoadon hoadon) {
        this.hoadon = hoadon;
    }

    public Set<Chitietdonhang> getChitietdonhangSet() {
        return chitietdonhangSet;
    }

    public void setChitietdonhangSet(Set<Chitietdonhang> chitietdonhangSet) {
        this.chitietdonhangSet = chitietdonhangSet;
    }

    public Set<Nhatkychinhsuadon> getNhatkychinhsuadonSet() {
        return nhatkychinhsuadonSet;
    }

    public void setNhatkychinhsuadonSet(Set<Nhatkychinhsuadon> nhatkychinhsuadonSet) {
        this.nhatkychinhsuadonSet = nhatkychinhsuadonSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maDonHang != null ? maDonHang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Donhang)) {
            return false;
        }
        Donhang other = (Donhang) object;
        if ((this.maDonHang == null && other.maDonHang != null) || (this.maDonHang != null && !this.maDonHang.equals(other.maDonHang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Donhang[ maDonHang=" + maDonHang + " ]";
    }

}
