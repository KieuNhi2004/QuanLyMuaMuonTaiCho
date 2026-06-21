/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Phieunhap;
import com.hkn.repositories.PhieunhapRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PhieunhapRepositoryImpl implements PhieunhapRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Phieunhap> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Phieunhap.findAll",
                Phieunhap.class
        ).getResultList();
    }

    @Override
    public Phieunhap findById(Integer maPhieuNhap) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Phieunhap.class, maPhieuNhap);
    }

    @Override
    public List<Phieunhap> findByTrangThai(String trangThai) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT p FROM Phieunhap p "
                + "WHERE p.trangThai = :trangThai",
                Phieunhap.class
        )
        .setParameter("trangThai", trangThai)
        .getResultList();
    }

    @Override
    public List<Phieunhap> findByMaNhanVien(Integer maNhanVien) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT p FROM Phieunhap p "
                + "WHERE p.maNhanVien.maNhanVien = :maNhanVien",
                Phieunhap.class
        )
        .setParameter("maNhanVien", maNhanVien)
        .getResultList();
    }

    @Override
    public Phieunhap save(Phieunhap phieunhap) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(phieunhap);
        return phieunhap;
    }

    @Override
    public void update(Phieunhap phieunhap) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(phieunhap);
    }

    @Override
    public void delete(Integer maPhieuNhap) {
        Session session = factory.getObject().getCurrentSession();

        Phieunhap pn = session.get(Phieunhap.class, maPhieuNhap);

        if (pn != null) {
            session.remove(pn);
        }
    }
}
