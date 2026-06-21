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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "loaisanpham")
@NamedQueries({
    @NamedQuery(name = "Loaisanpham.findAll", query = "SELECT l FROM Loaisanpham l"),
    @NamedQuery(name = "Loaisanpham.findByMaLoai", query = "SELECT l FROM Loaisanpham l WHERE l.maLoai = :maLoai"),
    @NamedQuery(name = "Loaisanpham.findByTenLoai", query = "SELECT l FROM Loaisanpham l WHERE l.tenLoai = :tenLoai"),
    @NamedQuery(name = "Loaisanpham.findByNhom", query = "SELECT l FROM Loaisanpham l WHERE l.nhom = :nhom")})
public class Loaisanpham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaLoai")
    private Integer maLoai;
    @Size(max = 100)
    @Column(name = "TenLoai")
    private String tenLoai;
    @Size(max = 50)
    @Column(name = "Nhom")
    private String nhom;
    @OneToMany(mappedBy = "maLoai")
    private Set<Sanpham> sanphamSet;

    public Loaisanpham() {
    }

    public Loaisanpham(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public Set<Sanpham> getSanphamSet() {
        return sanphamSet;
    }

    public void setSanphamSet(Set<Sanpham> sanphamSet) {
        this.sanphamSet = sanphamSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maLoai != null ? maLoai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loaisanpham)) {
            return false;
        }
        Loaisanpham other = (Loaisanpham) object;
        if ((this.maLoai == null && other.maLoai != null) || (this.maLoai != null && !this.maLoai.equals(other.maLoai))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Loaisanpham[ maLoai=" + maLoai + " ]";
    }
    
}
