/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.repositories.ThongkeRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class ThongkeRepositoryImpl implements ThongkeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    // ================= DOANH THU =================
    @Override
    public double doanhThu(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        BigDecimal result = session.createQuery(
                "SELECT COALESCE(SUM(h.thanhTien), 0) FROM Hoadon h "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to",
                BigDecimal.class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();

        return result.doubleValue();
    }

    // ================= TOP SP DOANH THU =================
    @Override
    public List<Map<String, Object>> topSanPhamDoanhThu(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        List<Object[]> result = session.createQuery(
                "SELECT c.sanpham.tenSanPham, SUM(c.gia * c.soLuong) "
                + "FROM Chitietdonhang c "
                + "JOIN c.donhang d "
                + "JOIN d.hoadon h "
                + "JOIN c.sanpham s "
                + "JOIN s.maLoai l "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to "
                + "AND l.nhom <> 'MUON' "
                + "GROUP BY c.sanpham.tenSanPham "
                + "ORDER BY SUM(c.gia * c.soLuong) DESC",
                Object[].class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .setMaxResults(5)
                .getResultList();

        return result.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tenSanPham", r[0]);
            map.put("doanhThu", r[1]);
            return map;
        }).toList();
    }

    // ================= TOP SP BÁN NHIỀU =================
    @Override
    public List<Map<String, Object>> topSanPhamBanNhieu(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        List<Object[]> result = session.createQuery(
                "SELECT c.sanpham.tenSanPham, SUM(c.soLuong) "
                + "FROM Chitietdonhang c "
                + "JOIN c.donhang d "
                + "JOIN d.hoadon h "
                + "JOIN c.sanpham s "
                + "JOIN s.maLoai l "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to "
                + "AND l.nhom <> 'MUON' "
                + "GROUP BY c.sanpham.tenSanPham "
                + "ORDER BY SUM(c.soLuong) DESC",
                Object[].class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .setMaxResults(5)
                .getResultList();

        return result.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tenSanPham", r[0]);
            map.put("soLuong", r[1]);
            return map;
        }).toList();
    }

    // ================= TOP NHÂN VIÊN =================
    @Override
    public List<Map<String, Object>> topNhanVien(Date from, Date to, int limit) {
        Session session = factory.getObject().getCurrentSession();

        List<Object[]> result = session.createQuery(
                "SELECT d.maNhanVien.hoTen, SUM(h.thanhTien) "
                + "FROM Hoadon h "
                + "JOIN h.maDonHang d "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to "
                + "GROUP BY d.maNhanVien.hoTen "
                + "ORDER BY SUM(h.thanhTien) DESC",
                Object[].class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .setMaxResults(limit)
                .getResultList();

        return result.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tenNhanVien", r[0]);
            map.put("doanhThu", r[1]);
            return map;
        }).toList();
    }

    @Override
    public Long countHoaDon(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        return session.createQuery(
                "SELECT COUNT(h) FROM Hoadon h "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to",
                Long.class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getSingleResult();
    }

    @Override
    public List<Map<String, Object>> doanhThuTheoNgay(Date from, Date to) {
        Session session = factory.getObject().getCurrentSession();

        List<Object[]> result = session.createQuery(
                "SELECT DATE(h.thoiGianThanhToan), SUM(h.thanhTien) "
                + "FROM Hoadon h "
                + "WHERE h.thoiGianThanhToan BETWEEN :from AND :to "
                + "GROUP BY DATE(h.thoiGianThanhToan) "
                + "ORDER BY DATE(h.thoiGianThanhToan)",
                Object[].class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();

        return result.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("ngay", r[0]);
            map.put("doanhThu", r[1]);
            return map;
        }).toList();
    }

    @Override
    public List<Map<String, Object>> topSanPhamMuon(Date from, Date to, int limit) {
        Session session = factory.getObject().getCurrentSession();

        List<Object[]> result = session.createQuery(
                "SELECT s.tenSanPham, SUM(c.soLuong) "
                + "FROM Chitietdonhang c "
                + "JOIN c.sanpham s "
                + "JOIN s.maLoai l "
                + "WHERE l.nhom = 'MUON' "
                + // ✅ CHỖ QUAN TRỌNG
                "AND c.donhang.hoadon IS NOT NULL "
                + "AND c.donhang.hoadon.thoiGianThanhToan BETWEEN :from AND :to "
                + "GROUP BY s.tenSanPham "
                + "ORDER BY SUM(c.soLuong) DESC",
                Object[].class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .setMaxResults(limit)
                .getResultList();

        return result.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tenSanPham", r[0]);
            map.put("soLuong", r[1]);
            return map;
        }).toList();
    }

    @Override
    public List<Map<String, Object>> topSanPhamMuonDoanhThu(Date from, Date to, int limit) {
        Session session = factory.getObject().getCurrentSession();

        List<Object[]> result = session.createQuery(
                "SELECT s.tenSanPham, SUM(c.gia * c.soLuong) "
                + "FROM Chitietdonhang c "
                + "JOIN c.sanpham s "
                + "JOIN s.maLoai l "
                + "WHERE l.nhom = 'MUON' "
                + // ✅ CHỈ LẤY NHÓM MƯỢN
                "AND c.donhang.hoadon IS NOT NULL "
                + "AND c.donhang.hoadon.thoiGianThanhToan BETWEEN :from AND :to "
                + "GROUP BY s.tenSanPham "
                + "ORDER BY SUM(c.gia * c.soLuong) DESC",
                Object[].class
        )
                .setParameter("from", from)
                .setParameter("to", to)
                .setMaxResults(limit)
                .getResultList();

        return result.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tenSanPham", r[0]);
            map.put("doanhThu", r[1]);
            return map;
        }).toList();
    }
}
