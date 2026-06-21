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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "nhanvien")
@NamedQueries({
    @NamedQuery(name = "Nhanvien.findAll", query = "SELECT n FROM Nhanvien n"),
    @NamedQuery(name = "Nhanvien.findByMaNhanVien", query = "SELECT n FROM Nhanvien n WHERE n.maNhanVien = :maNhanVien"),
    @NamedQuery(name = "Nhanvien.findByHoTen", query = "SELECT n FROM Nhanvien n WHERE n.hoTen = :hoTen"),
    @NamedQuery(name = "Nhanvien.findByNgaySinh", query = "SELECT n FROM Nhanvien n WHERE n.ngaySinh = :ngaySinh")})
public class Nhanvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNhanVien")
    private Integer maNhanVien;
    @Size(max = 100)
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "NgaySinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @OneToMany(mappedBy = "maNhanVien")
    private Set<Donhang> donhangSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nhanvien")
//    private Set<Phancong> phancongSet;
    @JoinColumn(name = "MaNguoiDung", referencedColumnName = "MaNguoiDung")
    @OneToOne
    private Nguoidung maNguoiDung;
    @OneToMany(mappedBy = "maNhanVien")
    private Set<Phieunhap> phieunhapSet;
    @OneToMany(mappedBy = "maNhanVien")
    private Set<Nhatkychinhsuadon> nhatkychinhsuadonSet;

    public Nhanvien() {
    }

    public Nhanvien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Set<Donhang> getDonhangSet() {
        return donhangSet;
    }

    public void setDonhangSet(Set<Donhang> donhangSet) {
        this.donhangSet = donhangSet;
    }

//    public Set<Phancong> getPhancongSet() {
//        return phancongSet;
//    }
//
//    public void setPhancongSet(Set<Phancong> phancongSet) {
//        this.phancongSet = phancongSet;
//    }

    public Nguoidung getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(Nguoidung maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public Set<Phieunhap> getPhieunhapSet() {
        return phieunhapSet;
    }

    public void setPhieunhapSet(Set<Phieunhap> phieunhapSet) {
        this.phieunhapSet = phieunhapSet;
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
        hash += (maNhanVien != null ? maNhanVien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nhanvien)) {
            return false;
        }
        Nhanvien other = (Nhanvien) object;
        if ((this.maNhanVien == null && other.maNhanVien != null) || (this.maNhanVien != null && !this.maNhanVien.equals(other.maNhanVien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Nhanvien[ maNhanVien=" + maNhanVien + " ]";
    }
    
}
