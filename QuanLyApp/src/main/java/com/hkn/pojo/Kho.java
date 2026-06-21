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
@Table(name = "kho")
@NamedQueries({
    @NamedQuery(name = "Kho.findAll", query = "SELECT k FROM Kho k"),
    @NamedQuery(name = "Kho.findByMaKho", query = "SELECT k FROM Kho k WHERE k.maKho = :maKho"),
    @NamedQuery(name = "Kho.findBySoLuongTon", query = "SELECT k FROM Kho k WHERE k.soLuongTon = :soLuongTon"),
    @NamedQuery(name = "Kho.findByNgayNhap", query = "SELECT k FROM Kho k WHERE k.ngayNhap = :ngayNhap"),
    @NamedQuery(name = "Kho.findByGiaNhap", query = "SELECT k FROM Kho k WHERE k.giaNhap = :giaNhap"),
    @NamedQuery(name = "Kho.findByHanSuDung", query = "SELECT k FROM Kho k WHERE k.hanSuDung = :hanSuDung")})
public class Kho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaKho")
    private Integer maKho;
    @Column(name = "SoLuongTon")
    private Integer soLuongTon;
    @Column(name = "NgayNhap")
    @Temporal(TemporalType.DATE)
    private Date ngayNhap;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GiaNhap")
    private BigDecimal giaNhap;
    @Column(name = "HanSuDung")
    @Temporal(TemporalType.DATE)
    private Date hanSuDung;
    @JoinColumn(name = "MaSanPham", referencedColumnName = "MaSanPham")
    @ManyToOne
    private Sanpham maSanPham;

    public Kho() {
    }

    public Kho(Integer maKho) {
        this.maKho = maKho;
    }

    public Integer getMaKho() {
        return maKho;
    }

    public void setMaKho(Integer maKho) {
        this.maKho = maKho;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public Sanpham getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Sanpham maSanPham) {
        this.maSanPham = maSanPham;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maKho != null ? maKho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kho)) {
            return false;
        }
        Kho other = (Kho) object;
        if ((this.maKho == null && other.maKho != null) || (this.maKho != null && !this.maKho.equals(other.maKho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Kho[ maKho=" + maKho + " ]";
    }
    
}
