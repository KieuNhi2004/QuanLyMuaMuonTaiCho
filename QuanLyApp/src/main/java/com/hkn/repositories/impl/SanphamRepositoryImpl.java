/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Sanpham;
import com.hkn.repositories.SanphamRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SanphamRepositoryImpl implements SanphamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Sanpham> findAll() {
        Session session = factory.getObject().getCurrentSession();
        return session.createNamedQuery("Sanpham.findAll", Sanpham.class).getResultList();
    }

    @Override
    public List<Sanpham> findByLoai(Integer maLoai) {
        Session session = factory.getObject().getCurrentSession();
        return session.createQuery(
                "SELECT s FROM Sanpham s WHERE s.maLoai.maLoai = :maLoai", Sanpham.class)
                .setParameter("maLoai", maLoai)
                .getResultList();
    }

    @Override
    public Sanpham findById(Integer maSanPham) {
        Session session = factory.getObject().getCurrentSession();
        return session.createNamedQuery("Sanpham.findByMaSanPham", Sanpham.class)
                .setParameter("maSanPham", maSanPham)
                .uniqueResult();
    }

    @Override
    public Sanpham save(Sanpham sanpham) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(sanpham);
        return sanpham;
    }
}

