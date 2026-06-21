/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Khachhang;
import com.hkn.pojo.Nguoidung;
import com.hkn.repositories.KhachhangRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class KhachhangRepositoryImpl implements KhachhangRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Khachhang addKhachhang(Khachhang kh) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(kh);
        return kh;
    }

    @Override
    public Khachhang findByMaNguoiDung(Integer maNguoiDung) {
        Session session = factory.getObject().getCurrentSession();

        String hql = "FROM Khachhang k WHERE k.maNguoiDung.maNguoiDung = :uid";

        List<Khachhang> list = session
                .createQuery(hql, Khachhang.class)
                .setParameter("uid", maNguoiDung)
                .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Khachhang> findAll() {
        Session session = factory.getObject().getCurrentSession();
        return session.createQuery("FROM Khachhang", Khachhang.class)
                .getResultList();
    }

    @Override
    public Khachhang findById(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(Khachhang.class, id);
    }

    @Override
    public void update(Khachhang kh) {
        Session session = factory.getObject().getCurrentSession();
        session.merge(kh);
    }
    
    @Override
    public Khachhang getByUser(Nguoidung u) {
        Session session = factory.getObject().getCurrentSession();

        String hql = "FROM Khachhang kh WHERE kh.maNguoiDung = :user";
        return session.createQuery(hql, Khachhang.class)
                .setParameter("user", u)
                .uniqueResult();
    }

    @Override
    public void delete(Integer id) {
        Session session = factory.getObject().getCurrentSession();
        Khachhang kh = this.findById(id);

        if (kh != null) {
            session.remove(kh);
        }
    }
}
