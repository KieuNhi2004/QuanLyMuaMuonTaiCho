/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Sanpham;
import com.hkn.repositories.SanphamRepository;
import com.hkn.services.SanphamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hkn.pojo.Loaisanpham;
import com.hkn.repositories.LoaisanphamRepository;
import com.hkn.services.LoaisanphamService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class SanphamServiceImpl implements SanphamService {

    @Autowired
    private SanphamRepository sanphamRepo;
    @Autowired
    private LoaisanphamService loaisanphamService;
    @Autowired
    private LoaisanphamRepository loaisanphamRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Sanpham> getAllSanpham() {
        return sanphamRepo.findAll();
    }

    @Override
    public List<Sanpham> getSanphamByLoai(Integer maLoai) {
        return sanphamRepo.findByLoai(maLoai);
    }

    @Override
    public Sanpham getSanphamById(Integer maSanPham) {
        return sanphamRepo.findById(maSanPham);
    }

    @Override
public Sanpham addSanpham(Map<String, String> params, MultipartFile file) {

    Sanpham sp = new Sanpham();

    sp.setTenSanPham(params.get("tenSanPham"));

    sp.setGia(new BigDecimal(params.get("gia")));

    sp.setTrangThai("CON_HANG");

    sp.setGhiChu(params.get("ghiChu"));

    // ===== TẠO LOẠI SẢN PHẨM =====
    Loaisanpham loai = new Loaisanpham();

    loai.setNhom(params.get("nhom"));

    loai.setTenLoai(params.get("tenLoai"));

    // lưu loại sản phẩm trước
    loaisanphamRepo.save(loai);

    // gán vào sản phẩm
    sp.setMaLoai(loai);

    // ===== HÌNH ẢNH =====
    if (!file.isEmpty()) {
        try {

            Map res = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto")
            );

            sp.setHinhAnh(
                    res.get("secure_url").toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    return sanphamRepo.save(sp);
}
@Override
public Sanpham addSanphamFromPhieuNhap(Map<String, String> params, MultipartFile file) {

    Sanpham sp = new Sanpham();

    sp.setTenSanPham(params.get("tenSanPham"));

    sp.setGia(new BigDecimal(params.get("gia")));

    // 👉 FIX CỨNG
    sp.setTrangThai("HET_HANG");

    sp.setGhiChu(params.get("ghiChu"));

    // ===== LOẠI =====
    Loaisanpham loai = new Loaisanpham();

    // 👉 FIX CỨNG NHÓM
    loai.setNhom("KHO");

    loai.setTenLoai(params.get("tenLoai"));

    loaisanphamRepo.save(loai);

    sp.setMaLoai(loai);

    // ===== HÌNH ẢNH =====
    if (file != null && !file.isEmpty()) {
        try {
            Map res = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto")
            );

            sp.setHinhAnh(res.get("secure_url").toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    return sanphamRepo.save(sp);
}
}
