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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "hoadon")
@NamedQueries({
    @NamedQuery(name = "Hoadon.findAll", query = "SELECT h FROM Hoadon h"),
    @NamedQuery(name = "Hoadon.findByMaHoaDon", query = "SELECT h FROM Hoadon h WHERE h.maHoaDon = :maHoaDon"),
    @NamedQuery(name = "Hoadon.findByTongTien", query = "SELECT h FROM Hoadon h WHERE h.tongTien = :tongTien"),
    @NamedQuery(name = "Hoadon.findByThanhTien", query = "SELECT h FROM Hoadon h WHERE h.thanhTien = :thanhTien"),
    @NamedQuery(name = "Hoadon.findByThoiGianThanhToan", query = "SELECT h FROM Hoadon h WHERE h.thoiGianThanhToan = :thoiGianThanhToan")})
public class Hoadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHoaDon")
    private Integer maHoaDon;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;
    @Column(name = "ThoiGianThanhToan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianThanhToan;
    @JoinColumn(name = "MaDonHang", referencedColumnName = "MaDonHang")
    @OneToOne
    private Donhang maDonHang;
    @JoinColumn(name = "MaGiamGia", referencedColumnName = "MaGiamGia")
    @ManyToOne
    private Magiamgia maGiamGia;

    public Hoadon() {
    }

    public Hoadon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Integer getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Integer maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Date getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(Date thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public Donhang getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Donhang maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Magiamgia getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(Magiamgia maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maHoaDon != null ? maHoaDon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoadon)) {
            return false;
        }
        Hoadon other = (Hoadon) object;
        if ((this.maHoaDon == null && other.maHoaDon != null) || (this.maHoaDon != null && !this.maHoaDon.equals(other.maHoaDon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Hoadon[ maHoaDon=" + maHoaDon + " ]";
    }
    
}
