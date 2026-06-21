/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.pojo;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "chitietdonhang")
@NamedQueries({
    @NamedQuery(name = "Chitietdonhang.findAll",
        query = "SELECT c FROM Chitietdonhang c"),
    @NamedQuery(name = "Chitietdonhang.findByMaDonHang",
        query = "SELECT c FROM Chitietdonhang c WHERE c.chitietdonhangPK.maDonHang = :maDonHang"),
    @NamedQuery(name = "Chitietdonhang.findByMaSanPham",
        query = "SELECT c FROM Chitietdonhang c WHERE c.chitietdonhangPK.maSanPham = :maSanPham"),
    @NamedQuery(name = "Chitietdonhang.findBySoLuong",
        query = "SELECT c FROM Chitietdonhang c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "Chitietdonhang.findByGia",
        query = "SELECT c FROM Chitietdonhang c WHERE c.gia = :gia"),
    // Them named query moi cho trangThai
    @NamedQuery(name = "Chitietdonhang.findByTrangThai",
        query = "SELECT c FROM Chitietdonhang c WHERE c.trangThai = :trangThai")
})
public class Chitietdonhang implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ChitietdonhangPK chitietdonhangPK;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "Gia")
    private BigDecimal gia;

    // ✅ THEM FIELD NAY
    @Column(name = "TrangThai", length = 20)
    private String trangThai;

    @JoinColumn(name = "MaDonHang", referencedColumnName = "MaDonHang",
                insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Donhang donhang;

    @JoinColumn(name = "MaSanPham", referencedColumnName = "MaSanPham",
                insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sanpham sanpham;

    public Chitietdonhang() {
    }

    public Chitietdonhang(ChitietdonhangPK chitietdonhangPK) {
        this.chitietdonhangPK = chitietdonhangPK;
    }

    public Chitietdonhang(int maDonHang, int maSanPham) {
        this.chitietdonhangPK = new ChitietdonhangPK(maDonHang, maSanPham);
    }

    public ChitietdonhangPK getChitietdonhangPK() {
        return chitietdonhangPK;
    }

    public void setChitietdonhangPK(ChitietdonhangPK chitietdonhangPK) {
        this.chitietdonhangPK = chitietdonhangPK;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    // ✅ THEM GETTER/SETTER NAY
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Donhang getDonhang() {
        return donhang;
    }

    public void setDonhang(Donhang donhang) {
        this.donhang = donhang;
    }

    public Sanpham getSanpham() {
        return sanpham;
    }

    public void setSanpham(Sanpham sanpham) {
        this.sanpham = sanpham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chitietdonhangPK != null ? chitietdonhangPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Chitietdonhang)) {
            return false;
        }
        Chitietdonhang other = (Chitietdonhang) object;
        if ((this.chitietdonhangPK == null && other.chitietdonhangPK != null)
            || (this.chitietdonhangPK != null
                && !this.chitietdonhangPK.equals(other.chitietdonhangPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Chitietdonhang[ chitietdonhangPK="
            + chitietdonhangPK + " ]";
    }
}

