/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Kho;
import com.hkn.repositories.KhoRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KhoRepositoryImpl implements KhoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Kho> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Kho.findAll",
                Kho.class
        ).getResultList();
    }

    @Override
    public Kho findById(Integer maKho) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Kho.class, maKho);
    }

    @Override
    public List<Kho> findByMaSanPham(Integer maSanPham) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT k FROM Kho k "
                + "WHERE k.maSanPham.maSanPham = :maSanPham",
                Kho.class
        )
        .setParameter("maSanPham", maSanPham)
        .getResultList();
    }

    @Override
    public List<Kho> findSapHetHan(Date ngayKiemTra) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT k FROM Kho k "
                + "WHERE k.hanSuDung <= :ngay",
                Kho.class
        )
        .setParameter("ngay", ngayKiemTra)
        .getResultList();
    }

    @Override
    public Integer sumSoLuongTon(Integer maSanPham) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT COALESCE(SUM(k.soLuongTon), 0) "
                + "FROM Kho k "
                + "WHERE k.maSanPham.maSanPham = :maSanPham",
                Integer.class
        )
        .setParameter("maSanPham", maSanPham)
        .getSingleResult();
    }

    @Override
    public Kho save(Kho kho) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(kho);
        return kho;
    }

    @Override
    public void update(Kho kho) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(kho);
    }

    @Override
    public void delete(Integer maKho) {
        Session session = factory.getObject().getCurrentSession();

        Kho k = session.get(Kho.class, maKho);

        if (k != null) {
            session.remove(k);
        }
    }
}

