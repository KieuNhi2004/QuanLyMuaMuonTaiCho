/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Chitietphieunhap;
import com.hkn.pojo.ChitietphieunhapPK;
import com.hkn.repositories.ChitietphieunhapRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ChitietphieunhapRepositoryImpl implements ChitietphieunhapRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Chitietphieunhap> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Chitietphieunhap.findAll",
                Chitietphieunhap.class
        ).getResultList();
    }

    @Override
    public Chitietphieunhap findById(ChitietphieunhapPK id) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Chitietphieunhap.class, id);
    }

    @Override
    public List<Chitietphieunhap> findByMaPhieuNhap(Integer maPhieuNhap) {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Chitietphieunhap.findByMaPhieuNhap",
                Chitietphieunhap.class
        )
        .setParameter("maPhieuNhap", maPhieuNhap)
        .getResultList();
    }

    @Override
    public List<Chitietphieunhap> findByMaSanPham(Integer maSanPham) {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Chitietphieunhap.findByMaSanPham",
                Chitietphieunhap.class
        )
        .setParameter("maSanPham", maSanPham)
        .getResultList();
    }

    @Override
    public Chitietphieunhap save(Chitietphieunhap chitiet) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(chitiet);
        return chitiet;
    }

    @Override
    public void delete(ChitietphieunhapPK id) {
        Session session = factory.getObject().getCurrentSession();

        Chitietphieunhap ct = session.get(Chitietphieunhap.class, id);

        if (ct != null) {
            session.remove(ct);
        }
    }
}
