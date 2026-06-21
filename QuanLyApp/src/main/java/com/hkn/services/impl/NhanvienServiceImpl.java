/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import com.hkn.repositories.NhanvienRepository;
import com.hkn.services.NhanvienService;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NhanvienServiceImpl implements NhanvienService {

    @Autowired
    private NhanvienRepository nhanvienRepo;

    @Override
    public Nhanvien addNhanvien(Nguoidung user, Map<String, String> params) {
        Nhanvien nv = new Nhanvien();

        nv.setHoTen(params.get("hoTen"));

        try {
            String ns = params.get("ngaySinh");
            if (ns != null && !ns.isEmpty()) {
                nv.setNgaySinh(new SimpleDateFormat("yyyy-MM-dd").parse(ns));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        nv.setMaNguoiDung(user);

        return nhanvienRepo.addNhanvien(nv);
    }

    @Override
    public void updateHoTen(Nguoidung u, String hoTen) {
        Nhanvien nv = nhanvienRepo.getByUser(u);

        if (nv != null) {
            nv.setHoTen(hoTen);
            nhanvienRepo.update(nv);
        }
    }
}
