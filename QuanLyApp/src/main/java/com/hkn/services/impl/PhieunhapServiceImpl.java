/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Chitietphieunhap;
import com.hkn.pojo.ChitietphieunhapPK;
import com.hkn.pojo.Kho;
import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import com.hkn.pojo.Phieunhap;
import com.hkn.pojo.Sanpham;
import com.hkn.repositories.ChitietphieunhapRepository;
import com.hkn.repositories.KhoRepository;
import com.hkn.repositories.PhieunhapRepository;
import com.hkn.repositories.SanphamRepository;
import com.hkn.services.KhoService;
import com.hkn.services.NhanvienService;
import com.hkn.services.PhieunhapService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhieunhapServiceImpl implements PhieunhapService {

    @Autowired
    private PhieunhapRepository phieunhapRepo;

    @Autowired
    private ChitietphieunhapRepository chitietRepo;

    @Autowired
    private KhoRepository khoRepo;

    @Autowired
    private SanphamRepository sanphamRepo;

    @Override
    public List<Phieunhap> findAll() {
        return phieunhapRepo.findAll();
    }

    @Override
public void taoPhieuNhap(Map<String, String> params, Nguoidung user) {

    Phieunhap pn = new Phieunhap();
    pn.setNgayNhap(new Date());
    pn.setTrangThai("CHO_XAC_NHAN");
    pn.setMaNhanVien(user.getNhanvien());
    phieunhapRepo.save(pn);

    int i = 0;

    while (true) {

        String maSPStr = params.get("details[" + i + "].maSanPham");
        String tenMoi = params.get("details[" + i + "].tenSanPham");

        if (maSPStr == null && tenMoi == null)
            break;

        Integer soLuong = Integer.parseInt(
                params.get("details[" + i + "].soLuong"));

        if (soLuong <= 0) {
            i++;
            continue;
        }

        Integer maSanPham = null;

        // ===== CASE 1: CHỌN SẢN PHẨM =====
        if (maSPStr != null && !maSPStr.isEmpty()) {
            maSanPham = Integer.parseInt(maSPStr);
        }

        // ===== CASE 2: TẠO SẢN PHẨM MỚI =====
        else if (tenMoi != null && !tenMoi.isEmpty()) {

            Sanpham sp = new Sanpham();
            sp.setTenSanPham(tenMoi);
            sp.setGia(new BigDecimal(
                    params.get("details[" + i + "].giaNhap")
            ));
            sp.setTrangThai("CON_HANG");

            sanphamRepo.save(sp);

            maSanPham = sp.getMaSanPham();
        }

        // ===== TẠO CHI TIẾT =====
        if (maSanPham != null) {

            Chitietphieunhap ct =
                    new Chitietphieunhap(
                            pn.getMaPhieuNhap(),
                            maSanPham
                    );

            ct.setSoLuong(soLuong);

            ct.setGiaNhap(
                    new BigDecimal(
                            params.get("details[" + i + "].giaNhap")
                    )
            );

            chitietRepo.save(ct);
        }

        i++;
    }
}

    @Override
    public void xacNhanPhieuNhap(Integer id) {

        Phieunhap pn = phieunhapRepo.findById(id);

        if (pn == null || !"CHO_XAC_NHAN".equals(pn.getTrangThai()))
            return;

        List<Chitietphieunhap> ds =
                chitietRepo.findByMaPhieuNhap(id);

        for (Chitietphieunhap ct : ds) {

            List<Kho> khoList =
                    khoRepo.findByMaSanPham(
                            ct.getSanpham().getMaSanPham()
                    );

            if (khoList.isEmpty()) {
                Kho k = new Kho();
                k.setMaSanPham(ct.getSanpham());
                k.setSoLuongTon(ct.getSoLuong());
                k.setGiaNhap(ct.getGiaNhap());
                k.setNgayNhap(new java.util.Date());

                khoRepo.save(k);
            } else {
                Kho k = khoList.get(0);
                k.setSoLuongTon(
                        k.getSoLuongTon() + ct.getSoLuong()
                );
                khoRepo.update(k);
            }
        }

        pn.setTrangThai("DA_NHAP");
        phieunhapRepo.update(pn);
    }
}
