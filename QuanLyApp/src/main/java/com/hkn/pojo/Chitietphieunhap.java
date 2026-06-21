/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "chitietphieunhap")
@NamedQueries({
    @NamedQuery(name = "Chitietphieunhap.findAll", query = "SELECT c FROM Chitietphieunhap c"),
    @NamedQuery(name = "Chitietphieunhap.findByMaPhieuNhap", query = "SELECT c FROM Chitietphieunhap c WHERE c.chitietphieunhapPK.maPhieuNhap = :maPhieuNhap"),
    @NamedQuery(name = "Chitietphieunhap.findByMaSanPham", query = "SELECT c FROM Chitietphieunhap c WHERE c.chitietphieunhapPK.maSanPham = :maSanPham"),
    @NamedQuery(name = "Chitietphieunhap.findBySoLuong", query = "SELECT c FROM Chitietphieunhap c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "Chitietphieunhap.findByGiaNhap", query = "SELECT c FROM Chitietphieunhap c WHERE c.giaNhap = :giaNhap")})
public class Chitietphieunhap implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChitietphieunhapPK chitietphieunhapPK;
    @Column(name = "SoLuong")
    private Integer soLuong;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;
    @Lob
    @Size(max = 65535)
    @Column(name = "GhiChu")
    private String ghiChu;
    @JoinColumn(name = "MaPhieuNhap", referencedColumnName = "MaPhieuNhap", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Phieunhap phieunhap;
    @JoinColumn(name = "MaSanPham", referencedColumnName = "MaSanPham", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sanpham sanpham;

    public Chitietphieunhap() {
    }

    public Chitietphieunhap(ChitietphieunhapPK chitietphieunhapPK) {
        this.chitietphieunhapPK = chitietphieunhapPK;
    }

    public Chitietphieunhap(int maPhieuNhap, int maSanPham) {
        this.chitietphieunhapPK = new ChitietphieunhapPK(maPhieuNhap, maSanPham);
    }

    public ChitietphieunhapPK getChitietphieunhapPK() {
        return chitietphieunhapPK;
    }

    public void setChitietphieunhapPK(ChitietphieunhapPK chitietphieunhapPK) {
        this.chitietphieunhapPK = chitietphieunhapPK;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Phieunhap getPhieunhap() {
        return phieunhap;
    }

    public void setPhieunhap(Phieunhap phieunhap) {
        this.phieunhap = phieunhap;
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
        hash += (chitietphieunhapPK != null ? chitietphieunhapPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chitietphieunhap)) {
            return false;
        }
        Chitietphieunhap other = (Chitietphieunhap) object;
        if ((this.chitietphieunhapPK == null && other.chitietphieunhapPK != null) || (this.chitietphieunhapPK != null && !this.chitietphieunhapPK.equals(other.chitietphieunhapPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Chitietphieunhap[ chitietphieunhapPK=" + chitietphieunhapPK + " ]";
    }
    
}
