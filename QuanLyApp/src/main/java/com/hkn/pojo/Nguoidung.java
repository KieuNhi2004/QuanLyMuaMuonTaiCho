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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "nguoidung")
@NamedQueries({
    @NamedQuery(name = "Nguoidung.findAll", query = "SELECT n FROM Nguoidung n"),
    @NamedQuery(name = "Nguoidung.findByMaNguoiDung", query = "SELECT n FROM Nguoidung n WHERE n.maNguoiDung = :maNguoiDung"),
    @NamedQuery(name = "Nguoidung.findByHinhAnh", query = "SELECT n FROM Nguoidung n WHERE n.hinhAnh = :hinhAnh"),
    @NamedQuery(name = "Nguoidung.findByTenDangNhap", query = "SELECT n FROM Nguoidung n WHERE n.tenDangNhap = :tenDangNhap"),
    @NamedQuery(name = "Nguoidung.findByMatKhau", query = "SELECT n FROM Nguoidung n WHERE n.matKhau = :matKhau"),
    @NamedQuery(name = "Nguoidung.findByVaiTro", query = "SELECT n FROM Nguoidung n WHERE n.vaiTro = :vaiTro"),
    @NamedQuery(name = "Nguoidung.findActiveUsers", query = "SELECT n FROM Nguoidung n WHERE n.trangThai = 'ACTIVE'")})
public class Nguoidung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNguoiDung")
    private Integer maNguoiDung;
    @Size(max = 255)
    @Column(name = "HinhAnh")
    private String hinhAnh;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TenDangNhap")
    private String tenDangNhap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MatKhau")
    private String matKhau;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "VaiTro")
    private String vaiTro;
    @OneToOne(mappedBy = "maNguoiDung")
    private Khachhang khachhang;
    @OneToOne(mappedBy = "maNguoiDung")
    private Nhanvien nhanvien;
    @Size(max = 20)
    @Column(name = "TrangThai")
    private String trangThai;

    public Nguoidung() {
    }

    public Nguoidung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public Nguoidung(Integer maNguoiDung, String tenDangNhap, String matKhau, String vaiTro) {
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public Integer getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(Integer maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Khachhang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(Khachhang khachhang) {
        this.khachhang = khachhang;
    }

    public Nhanvien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(Nhanvien nhanvien) {
        this.nhanvien = nhanvien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNguoiDung != null ? maNguoiDung.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nguoidung)) {
            return false;
        }
        Nguoidung other = (Nguoidung) object;
        if ((this.maNguoiDung == null && other.maNguoiDung != null) || (this.maNguoiDung != null && !this.maNguoiDung.equals(other.maNguoiDung))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Nguoidung[ maNguoiDung=" + maNguoiDung + " ]";
    }

}
