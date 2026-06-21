/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Kho;
import com.hkn.repositories.KhoRepository;
import com.hkn.services.KhoService;
import com.hkn.services.SanphamService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
//@Transactional
//public class KhoServiceImpl implements KhoService {
//
//    @Autowired
//    private KhoRepository khoRepository;
//    @Autowired
//    private SanphamService sanphamService;
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Kho> layTatCa() {
//        return khoRepository.findAll();
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Kho timTheoId(Integer maKho) {
//        Kho k = khoRepository.findById(maKho);
//        if (k == null) {
//            throw new RuntimeException("Khong tim thay kho: " + maKho);
//        }
//        return k;
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Kho> layTheoSanPham(Integer maSanPham) {
//        return khoRepository.findByMaSanPham(maSanPham);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Kho> laySapHetHan(int soNgayToi) {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DAY_OF_MONTH, soNgayToi);
//        return khoRepository.findSapHetHan(cal.getTime());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public int kiemTraSoLuongTon(Integer maSanPham) {
//        Integer sl = khoRepository.sumSoLuongTon(maSanPham);
//        return sl != null ? sl : 0;
//    }
//
//    @Override
//    public Kho nhapKho(Kho kho) {
//        sanphamService.timTheoId(kho.getMaSanPham().getMaSanPham());
//        kho.setNgayNhap(new Date());
//        return khoRepository.save(kho);
//    }
//
//    @Override
//    public void capNhatSoLuong(Integer maKho, int soLuongMoi) {
//        Kho k = timTheoId(maKho);
//        k.setSoLuongTon(soLuongMoi);
//        String trangThai = soLuongMoi <= 0 ? "HET_HANG" : "CON_HANG";
//        sanphamService.capNhatTrangThai(k.getMaSanPham().getMaSanPham(), trangThai);
//        khoRepository.update(k);
//    }
//
//    @Override
//    public void xoa(Integer maKho) {
//        timTheoId(maKho);
//        khoRepository.delete(maKho);
//    }
//}
