/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Donhang;
import com.hkn.pojo.Hoadon;
import com.hkn.pojo.Magiamgia;
import com.hkn.repositories.HoadonRepository;
import com.hkn.services.BananService;
import com.hkn.services.ChitietdonhangService;
import com.hkn.services.DonhangService;
import com.hkn.services.HoadonService;
import com.hkn.services.MagiamgiaService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional
//public class HoadonServiceImpl implements HoadonService {

//    @Autowired
//    private HoadonRepository hoadonRepository;
//    @Autowired
//    private DonhangService donhangService;
//    @Autowired
//    private ChitietdonhangService chitietdonhangService;
//    @Autowired
//    private MagiamgiaService magiamgiaService;
//    @Autowired
//    private BananService bananService;
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Hoadon> layTatCa() {
//        return hoadonRepository.findAll();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Hoadon timTheoId(Integer maHoaDon) {
//        Hoadon hd = hoadonRepository.findById(maHoaDon);
//        if (hd == null) {
//            throw new RuntimeException("Khong tim thay hoa don: " + maHoaDon);
//        }
//        return hd;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Hoadon timTheoMaDonHang(Integer maDonHang) {
//        Hoadon hd = hoadonRepository.findByMaDonHang(maDonHang);
//        if (hd == null) {
//            throw new RuntimeException("Chua co hoa don cho don hang: " + maDonHang);
//        }
//        return hd;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Hoadon> layLichSu(Date from, Date to) {
//        return hoadonRepository.findByThoiGianBetween(from, to);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public BigDecimal tinhDoanhThu(Date from, Date to) {
//        return hoadonRepository.sumDoanhThu(from, to);
//    }
//
//    @Override
//    public Hoadon taoHoaDon(Integer maDonHang, Integer maGiamGia) {
//        if (chitietdonhangService.kiemTraConSanPhamChuaTra(maDonHang)) {
//            throw new RuntimeException(
//                "Con san pham muon chua duoc tra. Vui long kiem tra lai truoc khi thanh toan.");
//        }
//        Donhang dh = donhangService.timTheoId(maDonHang);
//        Hoadon hd = new Hoadon();
//        hd.setMaDonHang(dh);
//        hd.setTongTien(dh.getTongTien());
//        hd.setThoiGianThanhToan(new Date());
//
//        BigDecimal thanhTien = dh.getTongTien();
//        if (maGiamGia != null) {
//            Magiamgia mgg = magiamgiaService.timTheoId(maGiamGia);
//            thanhTien = magiamgiaService.tinhTienSauGiam(maGiamGia, dh.getTongTien());
//            hd.setMaGiamGia(mgg);
//        }
//        hd.setThanhTien(thanhTien);
//        Hoadon saved = hoadonRepository.save(hd);
//
//        donhangService.capNhatTrangThai(maDonHang, "HOAN_THANH");
//        bananService.capNhatTrangThai(dh.getMaBan().getMaBan(), "TRONG");
//        return saved;
//    }
//}

