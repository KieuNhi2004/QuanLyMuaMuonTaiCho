/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Loaisanpham;
import com.hkn.repositories.LoaisanphamRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LoaisanphamRepositoryImpl implements LoaisanphamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Loaisanpham> findAll() {
        Session session = factory.getObject().getCurrentSession();
        return session.createNamedQuery("Loaisanpham.findAll", Loaisanpham.class).getResultList();
    }

    @Override
    public Loaisanpham findById(Integer maLoai) {
        Session session = factory.getObject().getCurrentSession();
        return session.createNamedQuery("Loaisanpham.findByMaLoai", Loaisanpham.class)
                .setParameter("maLoai", maLoai)
                .uniqueResult();
    }

    @Override
    public List<Loaisanpham> findByNhom(String nhom) {
        Session session = factory.getObject().getCurrentSession();
        return session.createNamedQuery("Loaisanpham.findByNhom", Loaisanpham.class)
                .setParameter("nhom", nhom)
                .getResultList();
    }

    @Override
    public Loaisanpham save(Loaisanpham loai) {

        Session session = factory.getObject().getCurrentSession();

        session.persist(loai);

        return loai;
    }
}
