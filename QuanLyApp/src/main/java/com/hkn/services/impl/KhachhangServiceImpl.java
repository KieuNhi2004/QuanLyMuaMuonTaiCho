/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Banan;
import com.hkn.pojo.Khachhang;
import com.hkn.pojo.Nguoidung;
import com.hkn.repositories.BananRepository;
import com.hkn.repositories.KhachhangRepository;
import com.hkn.services.KhachhangService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KhachhangServiceImpl implements KhachhangService {

    @Autowired
    private KhachhangRepository khachhangRepo;
    @Autowired
    private BananRepository bananRepo;

    @Override
    public Khachhang addKhachhang(Nguoidung user, Map<String, String> params) {
        Banan ban = new Banan();
        String tenBan = params.getOrDefault("tenBan", "").trim();
        ban.setTenBan(tenBan.isEmpty() ? "Bàn của " + user.getTenDangNhap() : tenBan);
        String soChoStr = params.getOrDefault("soCho", "").trim();
        ban.setSoCho(soChoStr.isEmpty() ? 4 : Integer.parseInt(soChoStr));
        ban.setTrangThai("Trống");
        bananRepo.addBanan(ban);

        Khachhang kh = new Khachhang();
        kh.setMaNguoiDung(user);
        kh.setMaBan(ban);
        return khachhangRepo.addKhachhang(kh);
    }

    @Override
    public Khachhang getByUserId(Integer maNguoiDung) {
        return khachhangRepo.findByMaNguoiDung(maNguoiDung);
    }

    @Override
    public void updateBan(Nguoidung u, String tenBan, String soCho) {
        Khachhang kh = khachhangRepo.getByUser(u);

        if (kh != null && kh.getMaBan() != null) {
            Banan b = kh.getMaBan();
            b.setTenBan(tenBan);
            b.setSoCho(Integer.parseInt(soCho));

            bananRepo.update(b);
        }
    }
}
