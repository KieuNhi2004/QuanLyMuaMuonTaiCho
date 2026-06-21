/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Donhang;
import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import com.hkn.pojo.Nhatkychinhsuadon;
import com.hkn.repositories.DonhangRepository;
import com.hkn.repositories.NhatkychinhsuadonRepository;
import com.hkn.services.NguoidungService;
import com.hkn.services.NhatkychinhsuadonService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NhatkychinhsuadonServiceImpl implements NhatkychinhsuadonService {

    @Autowired
    private NhatkychinhsuadonRepository repo;

    @Autowired
    private DonhangRepository donhangRepository;

    @Autowired
    private NguoidungService nguoidungService;

    @Override
    public void logUpdate(Integer maDonHang, String hanhDong, String noiDung, String username) {

        // ✅ luôn lấy entity managed từ DB
        Donhang dh = donhangRepository.findById(maDonHang);

        if (dh == null) {
            return; // tránh lỗi
        }
        Nhatkychinhsuadon log = new Nhatkychinhsuadon();
        log.setMaDonHang(dh);
        log.setHanhDong(hanhDong);
        log.setNoiDungThayDoi(noiDung);
        log.setThoiGian(new Date());

        if (username != null) {
            Nguoidung nd = nguoidungService.getUserByUsername(username);
            if (nd != null && nd.getNhanvien() != null) {
                log.setMaNhanVien(nd.getNhanvien());
            }
        }

        repo.save(log);
        
    }

    @Override
    public List<Nhatkychinhsuadon> getByDonHang(Integer maDonHang) {
        return repo.findByMaDonHang(maDonHang);
    }
    @Override
    public List<Nhatkychinhsuadon> filterLogs(
        Integer maDonHang,
        Integer maNhanVien,
        String hanhDong,
        Date from,
        Date to) {

    return repo.filterLogs(maDonHang, maNhanVien, hanhDong, from, to);
}
}


