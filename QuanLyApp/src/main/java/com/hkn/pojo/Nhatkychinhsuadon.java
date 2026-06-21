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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "nhatkychinhsuadon")
@NamedQueries({
    @NamedQuery(name = "Nhatkychinhsuadon.findAll", query = "SELECT n FROM Nhatkychinhsuadon n"),
    @NamedQuery(name = "Nhatkychinhsuadon.findByMaYeuCau", query = "SELECT n FROM Nhatkychinhsuadon n WHERE n.maYeuCau = :maYeuCau"),
    @NamedQuery(name = "Nhatkychinhsuadon.findByHanhDong", query = "SELECT n FROM Nhatkychinhsuadon n WHERE n.hanhDong = :hanhDong"),
    @NamedQuery(name = "Nhatkychinhsuadon.findByThoiGian", query = "SELECT n FROM Nhatkychinhsuadon n WHERE n.thoiGian = :thoiGian")})
public class Nhatkychinhsuadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaYeuCau")
    private Integer maYeuCau;
    @Size(max = 100)
    @Column(name = "HanhDong")
    private String hanhDong;
    @Column(name = "ThoiGian")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGian;
    @Lob
    @Size(max = 65535)
    @Column(name = "NoiDungThayDoi")
    private String noiDungThayDoi;
    @JoinColumn(name = "MaDonHang", referencedColumnName = "MaDonHang")
    @ManyToOne
    private Donhang maDonHang;
    @JoinColumn(name = "MaNhanVien", referencedColumnName = "MaNhanVien")
    @ManyToOne
    private Nhanvien maNhanVien;

    public Nhatkychinhsuadon() {
    }

    public Nhatkychinhsuadon(Integer maYeuCau) {
        this.maYeuCau = maYeuCau;
    }

    public Integer getMaYeuCau() {
        return maYeuCau;
    }

    public void setMaYeuCau(Integer maYeuCau) {
        this.maYeuCau = maYeuCau;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNoiDungThayDoi() {
        return noiDungThayDoi;
    }

    public void setNoiDungThayDoi(String noiDungThayDoi) {
        this.noiDungThayDoi = noiDungThayDoi;
    }

    public Donhang getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(Donhang maDonHang) {
        this.maDonHang = maDonHang;
    }

    public Nhanvien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Nhanvien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maYeuCau != null ? maYeuCau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nhatkychinhsuadon)) {
            return false;
        }
        Nhatkychinhsuadon other = (Nhatkychinhsuadon) object;
        if ((this.maYeuCau == null && other.maYeuCau != null) || (this.maYeuCau != null && !this.maYeuCau.equals(other.maYeuCau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Nhatkychinhsuadon[ maYeuCau=" + maYeuCau + " ]";
    }
    
}
