/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Nhatkychinhsuadon;
import com.hkn.repositories.NhatkychinhsuadonRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NhatkychinhsuadonRepositoryImpl implements NhatkychinhsuadonRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Nhatkychinhsuadon> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Nhatkychinhsuadon.findAll",
                Nhatkychinhsuadon.class
        ).getResultList();
    }

    @Override
    public Nhatkychinhsuadon findById(Integer maYeuCau) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Nhatkychinhsuadon.class, maYeuCau);
    }

    @Override
    public List<Nhatkychinhsuadon> findByMaDonHang(Integer maDonHang) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT n FROM Nhatkychinhsuadon n "
                + "WHERE n.maDonHang.maDonHang = :maDonHang",
                Nhatkychinhsuadon.class
        )
                .setParameter("maDonHang", maDonHang)
                .getResultList();
    }

    @Override
    public List<Nhatkychinhsuadon> findByMaNhanVien(Integer maNhanVien) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT n FROM Nhatkychinhsuadon n "
                + "WHERE n.maNhanVien.maNhanVien = :maNhanVien",
                Nhatkychinhsuadon.class
        )
                .setParameter("maNhanVien", maNhanVien)
                .getResultList();
    }

    @Override
    public List<Nhatkychinhsuadon> findByHanhDong(String hanhDong) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT n FROM Nhatkychinhsuadon n "
                + "WHERE n.hanhDong = :hanhDong",
                Nhatkychinhsuadon.class
        )
                .setParameter("hanhDong", hanhDong)
                .getResultList();
    }

    @Override
    public List<Nhatkychinhsuadon> findByThoiGianBetween(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT n FROM Nhatkychinhsuadon n "
                + "WHERE n.thoiGian BETWEEN :from AND :to",
                Nhatkychinhsuadon.class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    @Override
    public Nhatkychinhsuadon save(Nhatkychinhsuadon nhatky) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(nhatky);
        return nhatky;
    }

    @Override
    public void delete(Integer id) {
        Session s = factory.getObject().getCurrentSession();
        Nhatkychinhsuadon log = s.get(Nhatkychinhsuadon.class, id);
        if (log != null) {
            s.remove(log);
        }
    }
    @Override
    public List<Nhatkychinhsuadon> filterLogs(
        Integer maDonHang,
        Integer maNhanVien,
        String hanhDong,
        Date from,
        Date to) {

    Session session = factory.getObject().getCurrentSession();

    String hql = "SELECT n FROM Nhatkychinhsuadon n WHERE 1=1";

    if (maDonHang != null)
        hql += " AND n.maDonHang.maDonHang = :maDonHang";

    if (maNhanVien != null)
        hql += " AND n.maNhanVien.maNhanVien = :maNhanVien";

    if (hanhDong != null && !hanhDong.isEmpty())
        hql += " AND n.hanhDong = :hanhDong";

    if (from != null && to != null)
        hql += " AND n.thoiGian BETWEEN :from AND :to";

    var query = session.createQuery(hql, Nhatkychinhsuadon.class);

    if (maDonHang != null)
        query.setParameter("maDonHang", maDonHang);

    if (maNhanVien != null)
        query.setParameter("maNhanVien", maNhanVien);

    if (hanhDong != null && !hanhDong.isEmpty())
        query.setParameter("hanhDong", hanhDong);

    if (from != null && to != null) {
        query.setParameter("from", from);
        query.setParameter("to", to);
    }

    return query.getResultList();
}
   
}
