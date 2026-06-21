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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "phieunhap")
@NamedQueries({
    @NamedQuery(name = "Phieunhap.findAll", query = "SELECT p FROM Phieunhap p"),
    @NamedQuery(name = "Phieunhap.findByMaPhieuNhap", query = "SELECT p FROM Phieunhap p WHERE p.maPhieuNhap = :maPhieuNhap"),
    @NamedQuery(name = "Phieunhap.findByNgayNhap", query = "SELECT p FROM Phieunhap p WHERE p.ngayNhap = :ngayNhap"),
    @NamedQuery(name = "Phieunhap.findByTrangThai", query = "SELECT p FROM Phieunhap p WHERE p.trangThai = :trangThai")})
public class Phieunhap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaPhieuNhap")
    private Integer maPhieuNhap;
    @Column(name = "NgayNhap")
    @Temporal(TemporalType.DATE)
    private Date ngayNhap;
    @Size(max = 20)
    @Column(name = "TrangThai")
    private String trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phieunhap")
    private Set<Chitietphieunhap> chitietphieunhapSet;
    @JoinColumn(name = "MaNhanVien", referencedColumnName = "MaNhanVien")
    @ManyToOne
    private Nhanvien maNhanVien;

    public Phieunhap() {
    }

    public Phieunhap(Integer maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Integer getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(Integer maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Set<Chitietphieunhap> getChitietphieunhapSet() {
        return chitietphieunhapSet;
    }

    public void setChitietphieunhapSet(Set<Chitietphieunhap> chitietphieunhapSet) {
        this.chitietphieunhapSet = chitietphieunhapSet;
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
        hash += (maPhieuNhap != null ? maPhieuNhap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phieunhap)) {
            return false;
        }
        Phieunhap other = (Phieunhap) object;
        if ((this.maPhieuNhap == null && other.maPhieuNhap != null) || (this.maPhieuNhap != null && !this.maPhieuNhap.equals(other.maPhieuNhap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Phieunhap[ maPhieuNhap=" + maPhieuNhap + " ]";
    }
    
}
