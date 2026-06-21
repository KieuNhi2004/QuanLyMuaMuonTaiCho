/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.ChitietdonhangPK;
import com.hkn.repositories.ChitietdonhangRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ChitietdonhangRepositoryImpl implements ChitietdonhangRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Chitietdonhang> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Chitietdonhang.findAll",
                Chitietdonhang.class
        ).getResultList();
    }

    @Override
    public Chitietdonhang findById(ChitietdonhangPK id) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Chitietdonhang.class, id);
    }

    @Override
    public List<Chitietdonhang> findByMaDonHang(Integer maDonHang) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT c FROM Chitietdonhang c "
                + "WHERE c.chitietdonhangPK.maDonHang = :maDonHang",
                Chitietdonhang.class
        )
                .setParameter("maDonHang", maDonHang)
                .getResultList();
    }

    @Override
    public List<Chitietdonhang> findByTrangThai(String trangThai) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT c FROM Chitietdonhang c "
                + "WHERE c.trangThai = :trangThai",
                Chitietdonhang.class
        )
                .setParameter("trangThai", trangThai)
                .getResultList();
    }

    @Override
    public Chitietdonhang save(Chitietdonhang chitiet) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(chitiet);
        return chitiet;
    }

    @Override
    public void update(Chitietdonhang chitiet) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(chitiet);
    }

    @Override
    public void delete(ChitietdonhangPK id) {
        Session session = factory.getObject().getCurrentSession();

        Chitietdonhang ct = session.get(Chitietdonhang.class, id);

        if (ct != null) {
            session.remove(ct);
        }
    }

    @Override
    public void deleteByDonHangAndSanPham(Integer maDonHang, Integer maSanPham) {
        Session session = factory.getObject().getCurrentSession();

        session.createQuery(
                "DELETE FROM Chitietdonhang c "
                + "WHERE c.chitietdonhangPK.maDonHang = :maDonHang "
                + "AND c.chitietdonhangPK.maSanPham = :maSanPham"
        )
                .setParameter("maDonHang", maDonHang)
                .setParameter("maSanPham", maSanPham)
                .executeUpdate();
    }

    @Override
    public boolean existsByMaDonHang(Integer maDonHang) {
        Session session = factory.getObject().getCurrentSession();

        String hql = "SELECT COUNT(ct) FROM Chitietdonhang ct WHERE ct.donhang.maDonHang = :maDonHang";

        Long count = session.createQuery(hql, Long.class)
                .setParameter("maDonHang", maDonHang)
                .getSingleResult();

        return count != null && count > 0;
    }

    
    
}
