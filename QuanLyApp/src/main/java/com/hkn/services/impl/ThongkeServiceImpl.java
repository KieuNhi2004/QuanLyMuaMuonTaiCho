/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.repositories.ChitietdonhangRepository;
import com.hkn.repositories.HoadonRepository;
import com.hkn.repositories.ThongkeRepository;
import com.hkn.services.ThongkeService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
@Transactional
public class ThongkeServiceImpl implements ThongkeService {

    @Autowired
    private ThongkeRepository thongkeRepo;

    @Override
    public double doanhThu(Date from, Date to) {
        return thongkeRepo.doanhThu(from, to);
    }

    @Override
    public List<Map<String, Object>> topSanPhamDoanhThu(Date from, Date to) {
        return thongkeRepo.topSanPhamDoanhThu(from, to);
    }

    @Override
    public List<Map<String, Object>> topSanPhamBanNhieu(Date from, Date to) {
        return thongkeRepo.topSanPhamBanNhieu(from, to);
    }

    @Override
public List<Map<String, Object>> topNhanVien(Date from, Date to, int limit) {
    return thongkeRepo.topNhanVien(from, to, limit);
}
    @Override
public Long countHoaDon(Date from, Date to) {
    return thongkeRepo.countHoaDon(from, to);
}
@Override
public List<Map<String, Object>> doanhThuTheoNgay(Date from, Date to){
    return thongkeRepo.doanhThuTheoNgay(from, to);
}
@Override
public List<Map<String, Object>> topSanPhamMuon(Date from, Date to, int limit){
    return thongkeRepo.topSanPhamMuon(from, to, limit);
}
@Override
public List<Map<String, Object>> topSanPhamMuonDoanhThu(Date from, Date to, int limit){
    return thongkeRepo.topSanPhamMuonDoanhThu(from, to, limit);
}
}
