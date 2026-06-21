/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Hoadon;
import com.hkn.repositories.HoadonRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class HoadonRepositoryImpl implements HoadonRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Hoadon> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Hoadon.findAll",
                Hoadon.class
        ).getResultList();
    }

    @Override
    public Hoadon findById(Integer maHoaDon) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Hoadon.class, maHoaDon);
    }

    @Override
    public Hoadon findByMaDonHang(Integer maDonHang) {
        Session session = factory.getObject().getCurrentSession();

        List<Hoadon> result = session.createQuery(
                "SELECT h FROM Hoadon h "
                + "WHERE h.maDonHang.maDonHang = :maDonHang",
                Hoadon.class
        )
                .setParameter("maDonHang", maDonHang)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Hoadon> findByThoiGianBetween(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT h FROM Hoadon h "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to",
                Hoadon.class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public BigDecimal sumDoanhThu(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT COALESCE(SUM(h.thanhTien), 0) "
                + "FROM Hoadon h "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to",
                BigDecimal.class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();
    }

    @Override
    public Hoadon save(Hoadon hoadon) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(hoadon);
        return hoadon;
    }

    @Override
    public void update(Hoadon hoadon) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(hoadon);
    }

   
}
