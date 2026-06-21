/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import com.hkn.repositories.NhanvienRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NhanvienRepositoryImpl implements NhanvienRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Nhanvien addNhanvien(Nhanvien nv) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(nv);
        return nv;
    }

    @Override
    public Nhanvien getByUser(Nguoidung u) {
        Session session = factory.getObject().getCurrentSession();

        String hql = "FROM Nhanvien nv WHERE nv.maNguoiDung = :user";
        return session.createQuery(hql, Nhanvien.class)
                .setParameter("user", u)
                .uniqueResult();
    }

    @Override
    public void update(Nhanvien nv) {
        Session session = factory.getObject().getCurrentSession();
        session.update(nv);
    }

}
