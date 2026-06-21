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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "banan")
@NamedQueries({
    @NamedQuery(name = "Banan.findAll", query = "SELECT b FROM Banan b"),
    @NamedQuery(name = "Banan.findByMaBan", query = "SELECT b FROM Banan b WHERE b.maBan = :maBan"),
    @NamedQuery(name = "Banan.findByTenBan", query = "SELECT b FROM Banan b WHERE b.tenBan = :tenBan"),
    @NamedQuery(name = "Banan.findBySoCho", query = "SELECT b FROM Banan b WHERE b.soCho = :soCho"),
    @NamedQuery(name = "Banan.findByTrangThai", query = "SELECT b FROM Banan b WHERE b.trangThai = :trangThai")})
public class Banan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaBan")
    private Integer maBan;
    @Size(max = 50)
    @Column(name = "TenBan")
    private String tenBan;
    @Column(name = "SoCho")
    private Integer soCho;
    @Size(max = 20)
    @Column(name = "TrangThai")
    private String trangThai;
    @OneToMany(mappedBy = "maBan")
    private Set<Donhang> donhangSet;
    @OneToOne(mappedBy = "maBan")
    private Khachhang khachhang;

    public Banan() {
    }

    public Banan(Integer maBan) {
        this.maBan = maBan;
    }

    public Integer getMaBan() {
        return maBan;
    }

    public void setMaBan(Integer maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public Integer getSoCho() {
        return soCho;
    }

    public void setSoCho(Integer soCho) {
        this.soCho = soCho;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Set<Donhang> getDonhangSet() {
        return donhangSet;
    }

    public void setDonhangSet(Set<Donhang> donhangSet) {
        this.donhangSet = donhangSet;
    }



    public Khachhang getKhachhang() {
        return khachhang;
    }

    public void setKhachhang(Khachhang khachhang) {
        this.khachhang = khachhang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maBan != null ? maBan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banan)) {
            return false;
        }
        Banan other = (Banan) object;
        if ((this.maBan == null && other.maBan != null) || (this.maBan != null && !this.maBan.equals(other.maBan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Banan[ maBan=" + maBan + " ]";
    }
    
}
