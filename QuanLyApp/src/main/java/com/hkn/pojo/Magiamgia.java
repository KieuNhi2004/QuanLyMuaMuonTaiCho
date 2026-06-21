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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "magiamgia")
@NamedQueries({
    @NamedQuery(name = "Magiamgia.findAll", query = "SELECT m FROM Magiamgia m"),
    @NamedQuery(name = "Magiamgia.findByMaGiamGia", query = "SELECT m FROM Magiamgia m WHERE m.maGiamGia = :maGiamGia"),
    @NamedQuery(name = "Magiamgia.findByGiaTriGiam", query = "SELECT m FROM Magiamgia m WHERE m.giaTriGiam = :giaTriGiam"),
    @NamedQuery(name = "Magiamgia.findByNgayBatDau", query = "SELECT m FROM Magiamgia m WHERE m.ngayBatDau = :ngayBatDau"),
    @NamedQuery(name = "Magiamgia.findByNgayKetThuc", query = "SELECT m FROM Magiamgia m WHERE m.ngayKetThuc = :ngayKetThuc")})
public class Magiamgia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaGiamGia")
    private Integer maGiamGia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GiaTriGiam")
    private BigDecimal giaTriGiam;
    @Lob
    @Size(max = 65535)
    @Column(name = "DieuKien")
    private String dieuKien;
    @Column(name = "NgayBatDau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;
    @Column(name = "NgayKetThuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    @OneToMany(mappedBy = "maGiamGia")
    private Set<Hoadon> hoadonSet;

    public Magiamgia() {
    }

    public Magiamgia(Integer maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public Integer getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(Integer maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public BigDecimal getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(BigDecimal giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public String getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(String dieuKien) {
        this.dieuKien = dieuKien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Set<Hoadon> getHoadonSet() {
        return hoadonSet;
    }

    public void setHoadonSet(Set<Hoadon> hoadonSet) {
        this.hoadonSet = hoadonSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maGiamGia != null ? maGiamGia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Magiamgia)) {
            return false;
        }
        Magiamgia other = (Magiamgia) object;
        if ((this.maGiamGia == null && other.maGiamGia != null) || (this.maGiamGia != null && !this.maGiamGia.equals(other.maGiamGia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hkn.pojo.Magiamgia[ maGiamGia=" + maGiamGia + " ]";
    }
    
}
