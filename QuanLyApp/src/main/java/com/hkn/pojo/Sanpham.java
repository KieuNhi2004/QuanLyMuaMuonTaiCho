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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "sanpham")
@NamedQueries({
    @NamedQuery(name = "Sanpham.findAll", query = "SELECT s FROM Sanpham s"),
    @NamedQuery(name = "Sanpham.findByMaSanPham", query = "SELECT s FROM Sanpham s WHERE s.maSanPham = :maSanPham"),
    @NamedQuery(name = "Sanpham.findByTenSanPham", query = "SELECT s FROM Sanpham s WHERE s.tenSanPham = :tenSanPham"),
    @NamedQuery(name = "Sanpham.findByGia", query = "SELECT s FROM Sanpham s WHERE s.gia = :gia"),
    @NamedQuery(name = "Sanpham.findByTrangThai", query = "SELECT s FROM Sanpham s WHERE s.trangThai = :trangThai"),
    @NamedQuery(name = "Sanpham.findByHinhAnh", query = "SELECT s FROM Sanpham s WHERE s.hinhAnh = :hinhAnh")})
public class Sanpham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaSanPham")
    private Integer maSanPham;
    @Size(max = 100)
    @Column(name = "TenSanPham")
    private String tenSanPham;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Gia")
    private BigDecimal gia;
    @Size(max = 20)
    @Column(name = "TrangThai")
    private String trangThai;
    @Lob
    @Size(max = 65535)
    @Column(name = "GhiChu")
    private String ghiChu;
    @Size(max = 255)
    @Column(name = "HinhAnh")
    private String hinhAnh;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sanpham")
    private Set<Chitietdonhang> chitietdonhangSet;
    @JoinColumn(name = "MaLoai", referencedColumnName = "MaLoai")
    @ManyToOne
    private Loaisanpham maLoai;
    @OneToMany(mappedBy = "maSanPham")
    private Set<Kho> khoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sanpham")
    private Set<Chitietphieunhap> chitietphieunhapSet;

    public Sanpham() {
    }

    public Sanpham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Set<Chitietdonhang> getChitietdonhangSet() {
        return chitietdonhangSet;
    }

    public void setChitietdonhangSet(Set<Chitietdonhang> chitietdonhangSet) {
        this.chitietdonhangSet = chitietdonhangSet;
    }

    public Loaisanpham getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Loaisanpham maLoai) {
        this.maLoai = maLoai;
    }

    public Set<Kho> getKhoSet() {
        return khoSet;
    }

    public void setKhoSet(Set<Kho> khoSet) {
        this.khoSet = khoSet;
    }

    public Set<Chitietphieunhap> getChitietphieunhapSet() {
        return chitietphieunhapSet;
    }

    public void setChitietphieunhapSet(Set<Chitietphieunhap> chitietphieunhapSet) {
        this.chitietphieunhapSet = chitietphieunhapSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maSanPham != null ? maSanPham.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanpham)) {
            return false;
        }
        Sanpham other = (Sanpham) object;
        if ((this.maSanPham == null && other.maSanPham != null) || (this.maSanPham != null && !this.maSanPham.equals(other.maSanPham))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Sanpham[ maSanPham=" + maSanPham + " ]";
    }
    
}
