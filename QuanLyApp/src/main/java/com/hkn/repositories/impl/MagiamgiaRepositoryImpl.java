/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Magiamgia;
import com.hkn.repositories.MagiamgiaRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;

@Repository
@Transactional
public class MagiamgiaRepositoryImpl implements MagiamgiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Magiamgia> findAll() {
        Session session = factory.getObject().getCurrentSession();

        return session.createNamedQuery(
                "Magiamgia.findAll",
                Magiamgia.class
        ).getResultList();
    }

    @Override
    public Magiamgia findById(Integer maGiamGia) {
        Session session = factory.getObject().getCurrentSession();

        return session.get(Magiamgia.class, maGiamGia);
    }

    @Override
    public List<Magiamgia> findConHieuLuc(Date ngayHienTai) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT m FROM Magiamgia m "
                + "WHERE m.ngayBatDau <= :ngay "
                + "AND m.ngayKetThuc >= :ngay",
                Magiamgia.class
        )
                .setParameter("ngay", ngayHienTai)
                .getResultList();
    }

    @Override
    public Magiamgia save(Magiamgia magiamgia) {
        Session session = factory.getObject().getCurrentSession();

        session.persist(magiamgia);
        return magiamgia;
    }

    @Override
    public void update(Magiamgia magiamgia) {
        Session session = factory.getObject().getCurrentSession();

        session.merge(magiamgia);
    }

    @Override
    public void delete(Integer maGiamGia) {
        Session session = factory.getObject().getCurrentSession();

        Magiamgia mgg = session.get(Magiamgia.class, maGiamGia);

        if (mgg != null) {
            session.remove(mgg);
        }
    }

    @Override
    public List<Magiamgia> search(Map<String, String> params) {

        Session session = factory.getObject().getCurrentSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Magiamgia> cq = cb.createQuery(Magiamgia.class);

        Root<Magiamgia> root = cq.from(Magiamgia.class);

        List<Predicate> predicates = new ArrayList<>();

        // ===== LỌC TRẠNG THÁI =====
        String trangThai = params.get("trangThai");

        if (trangThai != null && !trangThai.isEmpty()) {

            Date now = new Date();

            if (trangThai.equals("CON_HAN")) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("ngayKetThuc"), now)
                );
            } else if (trangThai.equals("HET_HAN")) {
                predicates.add(
                        cb.lessThan(root.get("ngayKetThuc"), now)
                );
            }
        }

        // ===== LỌC ĐIỀU KIỆN =====
        String dieuKien = params.get("dieuKien");

        if (dieuKien != null && !dieuKien.isEmpty()) {
            predicates.add(
                    cb.like(
                            root.get("dieuKien"),
                            "%" + dieuKien + "%"
                    )
            );
        }

        // ===== LỌC GIÁ TRỊ GIẢM =====
        String giaTri = params.get("giaTriGiam");

        if (giaTri != null && !giaTri.isEmpty()) {

            predicates.add(
                    cb.equal(
                            root.get("giaTriGiam"),
                            new BigDecimal(giaTri)
                    )
            );
        }

        cq.where(predicates.toArray(Predicate[]::new));

        // ===== SẮP XẾP =====
        String sort = params.get("sort");

        if ("asc".equals(sort)) {
            cq.orderBy(
                    cb.asc(root.get("ngayKetThuc"))
            );
        } else {
            cq.orderBy(
                    cb.desc(root.get("ngayKetThuc"))
            );
        }

        return session.createQuery(cq).getResultList();
    }
}
