/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Donhang;
import com.hkn.repositories.DonhangRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DonhangRepositoryImpl implements DonhangRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Donhang> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Donhang.findAll",
                Donhang.class
        ).getResultList();
    }

    @Override
    public Donhang findById(Integer maDonHang) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Donhang.class, maDonHang);
    }

    @Override
    public List<Donhang> findByMaBan(Integer maBan) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT d FROM Donhang d "
                + "WHERE d.maBan.maBan = :maBan",
                Donhang.class
        )
                .setParameter("maBan", maBan)
                .getResultList();
    }

    @Override
    public List<Donhang> findByTrangThai(String trangThai) {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Donhang.findByTrangThai",
                Donhang.class
        )
                .setParameter("trangThai", trangThai)
                .getResultList();
    }

    @Override
    public List<Donhang> findByMaNhanVien(Integer maNhanVien) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT d FROM Donhang d "
                + "WHERE d.maNhanVien.maNhanVien = :maNhanVien",
                Donhang.class
        )
                .setParameter("maNhanVien", maNhanVien)
                .getResultList();
    }

    @Override
    public List<Donhang> findByThoiGianBetween(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT d FROM Donhang d "
                + "WHERE d.thoiGianBatDau BETWEEN :from AND :to",
                Donhang.class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public Donhang save(Donhang donhang) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(donhang);
        return donhang;
    }

    @Override
    public void update(Donhang donhang) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(donhang);
    }

    @Override
    public void delete(Integer maDonHang) {
        Session session = factory.getObject().getCurrentSession();

        Donhang dh = session.get(Donhang.class, maDonHang);

        if (dh != null) {
            session.remove(dh);
        }
    }

    @Override
    public List<Donhang> findChuaThanhToan() {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "FROM Donhang d WHERE d.hoadon IS NULL",
                Donhang.class
        ).getResultList();
    }

    @Override
    public List<Donhang> findDaThanhToan() {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "FROM Donhang d WHERE d.hoadon IS NOT NULL",
                Donhang.class
        ).getResultList();
    }
}
