/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.ChitietdonhangPK;
import com.hkn.pojo.Donhang;
import com.hkn.pojo.Sanpham;
import com.hkn.repositories.ChitietdonhangRepository;
import com.hkn.repositories.DonhangRepository;
import com.hkn.services.ChitietdonhangService;
import com.hkn.services.DonhangService;
import com.hkn.services.NhanvienService;
import com.hkn.services.NhatkychinhsuadonService;
import com.hkn.services.SanphamService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChitietdonhangServiceImpl implements ChitietdonhangService {

    @Autowired
    private ChitietdonhangRepository chitietdonhangRepository;

    @Autowired
    private DonhangRepository donhangRepository;
    @Autowired
    private DonhangService donhangService;

    @Autowired
    private SanphamService sanphamService;

    @Autowired
    private NhatkychinhsuadonService logService;

    @Autowired
    private NhanvienService nhanvienService;

    @Override
    @Transactional(readOnly = true)
    public List<Chitietdonhang> layTatCa() {
        return chitietdonhangRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Chitietdonhang timTheoId(ChitietdonhangPK id) {
        Chitietdonhang ct = chitietdonhangRepository.findById(id);
        if (ct == null) {
            throw new RuntimeException("Khong tim thay chi tiet don hang");
        }
        return ct;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Chitietdonhang> layTheoMaDonHang(Integer maDonHang) {
        return chitietdonhangRepository.findByMaDonHang(maDonHang);
    }

    @Override
    public Chitietdonhang themSanPhamVaoDon(Integer maDonHang,
            Integer maSanPham,
            int soLuong) {

        Donhang dh = donhangRepository.findById(maDonHang);
        if (dh == null) {
            throw new RuntimeException("Khong tim thay don hang: " + maDonHang);
        }

        // ✅ LẤY SẢN PHẨM
        Sanpham sp = sanphamService.getSanphamById(maSanPham);

        ChitietdonhangPK pk = new ChitietdonhangPK(maDonHang, maSanPham);
        Chitietdonhang existing = chitietdonhangRepository.findById(pk);

        if (existing != null) {
            existing.setSoLuong(existing.getSoLuong() + soLuong);
            chitietdonhangRepository.update(existing);
            return existing;
        }

        Chitietdonhang ct = new Chitietdonhang();
        ct.setChitietdonhangPK(pk);
        ct.setSoLuong(soLuong);

        ct.setGia(sp.getGia());

        ct.setTrangThai("DANG_XU_LY");

        return chitietdonhangRepository.save(ct);
    }

    @Override
    public void capNhatSoLuong(Integer maDonHang, Integer maSanPham, int soLuong, String username) {
        ChitietdonhangPK pk = new ChitietdonhangPK(maDonHang, maSanPham);
        Chitietdonhang ct = timTheoId(pk);
        int old = ct.getSoLuong();
        if (old != soLuong) {   // ✅ CHỈ update khi khác
            ct.setSoLuong(soLuong);
            chitietdonhangRepository.update(ct);
            donhangService.capNhatTongTien(maDonHang);
            Sanpham sp = sanphamService.getSanphamById(maSanPham);
            String tenSP = sp != null ? sp.getTenSanPham() : ("SP-" + maSanPham);

            logService.logUpdate(
                    maDonHang,
                    "Thay đổi số lượng",
                    "Số lượng " + tenSP + " đổi từ " + old + " thành " + soLuong,
                    username
            );
        }
    }

    @Override
    public void capNhatTrangThai(Integer maDonHang, Integer maSanPham, String trangThai, String username) {
        ChitietdonhangPK pk = new ChitietdonhangPK(maDonHang, maSanPham);
        Chitietdonhang ct = timTheoId(pk);
        String old = ct.getTrangThai();
        if (!old.equals(trangThai)) {   // ✅ CHỈ log khi thay đổi
            ct.setTrangThai(trangThai);
            chitietdonhangRepository.update(ct);
            donhangService.capNhatTrangThaiTheoChiTiet(maDonHang);
            donhangService.capNhatTongTien(maDonHang);
            Sanpham sp = sanphamService.getSanphamById(maSanPham);
            String tenSP = sp != null ? sp.getTenSanPham() : ("SP-" + maSanPham);

            logService.logUpdate(
                    maDonHang,
                    "Thay đổi trạng thái",
                    "Trạng thái " + tenSP + " đổi từ " + old + " thành " + trangThai,
                    username
            );
        }

    }

    @Override
    public void xoaSanPhamKhoiDon(Integer maDonHang, Integer maSanPham, String username) {

        // 1. LẤY CHI TIẾT TRƯỚC KHI XÓA
        ChitietdonhangPK pk = new ChitietdonhangPK(maDonHang, maSanPham);
        Chitietdonhang ct = timTheoId(pk);

        if (ct == null) {
            return;
        }

        int soLuong = ct.getSoLuong();
        String trangThai = ct.getTrangThai();

        Sanpham sp = ct.getSanpham();
        String tenSP = sp != null ? sp.getTenSanPham() : ("SP-" + maSanPham);

        Donhang dh = ct.getDonhang();

        // 2. XOÁ
        chitietdonhangRepository.deleteByDonHangAndSanPham(maDonHang, maSanPham);
        donhangService.capNhatTongTien(maDonHang);
        donhangService.capNhatTrangThaiTheoChiTiet(maDonHang);
        // 3. LOG
        logService.logUpdate(
                maDonHang,
                "Xóa sản phẩm",
                "Đã xóa sản phẩm: " + tenSP
                + " | Số lượng: " + soLuong
                + " | Trạng thái: " + trangThai,
                username
        );

    }

    @Override
    @Transactional(readOnly = true)
    public boolean kiemTraConSanPhamChuaTra(Integer maDonHang) {
        List<Chitietdonhang> ds = layTheoMaDonHang(maDonHang);
        return ds.stream().anyMatch(ct
                -> "MUON".equals(ct.getTrangThai()) || "CHUA_TRA".equals(ct.getTrangThai())
        );
    }

    @Override
    @Transactional(readOnly = true)
    public boolean conSanPham(Integer maDonHang) {
        return chitietdonhangRepository.existsByMaDonHang(maDonHang);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean coTheThanhToan(Integer maDonHang) {
        List<Chitietdonhang> ds = layTheoMaDonHang(maDonHang);

        for (Chitietdonhang ct : ds) {
            String trangThai = ct.getTrangThai();

            Sanpham sp = ct.getSanpham();
            if (sp == null || sp.getMaLoai() == null) {
                continue;
            }

            String nhom = sp.getMaLoai().getNhom();

            // ❗ MUON: phải DA_TRA
            if ("MUON".equalsIgnoreCase(nhom)) {
                if (!"DA_TRA".equalsIgnoreCase(trangThai)) {
                    return false;
                }
                continue; // đã ok thì bỏ qua check HOAN_THANH
            }

            // ❗ các món khác: phải HOAN_THANH
            if (!"HOAN_THANH".equalsIgnoreCase(trangThai)) {
                return false;
            }
        }

        return !ds.isEmpty();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal tinhTongTien(Integer maDonHang) {
        List<Chitietdonhang> ds = layTheoMaDonHang(maDonHang);

        return ds.stream()
                .map(ct -> ct.getGia().multiply(BigDecimal.valueOf(ct.getSoLuong())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
