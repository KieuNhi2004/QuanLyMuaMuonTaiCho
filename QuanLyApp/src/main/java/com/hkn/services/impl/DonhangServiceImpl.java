/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.Donhang;
import com.hkn.pojo.Hoadon;
import com.hkn.pojo.Magiamgia;
import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import com.hkn.repositories.DonhangRepository;
import com.hkn.repositories.HoadonRepository;
import com.hkn.repositories.MagiamgiaRepository;
import com.hkn.repositories.NhanvienRepository;
import com.hkn.services.ChitietdonhangService;
import com.hkn.services.DonhangService;
import com.hkn.services.NguoidungService;
import com.hkn.services.NhatkychinhsuadonService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DonhangServiceImpl implements DonhangService {

    @Autowired
    private DonhangRepository donhangRepository;
    @Autowired
    private NhatkychinhsuadonService logService;
    @Autowired
    private NguoidungService nguoidungService;

    @Autowired
    private ChitietdonhangService chitietdonhangService;
    @Autowired
    private HoadonRepository hoadonRepository;
    @Autowired
    private MagiamgiaRepository magiamgiaRepository;
    @Autowired
    private NhanvienRepository nhanvienRepository;

    @Override
    public void thanhToan(Integer maDonHang, Integer maGiamGia, String username) {

        // ❗ 1. Check đã có hóa đơn chưa
        if (hoadonRepository.findByMaDonHang(maDonHang) != null) {
            throw new RuntimeException("Đã thanh toán rồi!");
        }

        // ❗ 2. Lấy đơn hàng
        Donhang dh = timTheoId(maDonHang);

        // ❗ 3. Check điều kiện thanh toán
        if (!chitietdonhangService.coTheThanhToan(maDonHang)) {
            throw new RuntimeException("Chưa thể thanh toán!");
        }

        // ❗ 4. Tính tổng tiền
        BigDecimal tongTien = chitietdonhangService.tinhTongTien(maDonHang);
        BigDecimal thanhTien = tongTien;

        Magiamgia gg = null;

        // ❗ 5. Áp dụng mã giảm giá (nếu có)
        if (maGiamGia != null) {
            gg = magiamgiaRepository.findById(maGiamGia);

            if (gg != null) {

                // ✅ CHECK HẠN Ở ĐÂY
                Date now = new Date();
                if (gg.getNgayBatDau().after(now) || gg.getNgayKetThuc().before(now)) {
                    throw new RuntimeException("Mã giảm giá đã hết hạn!");
                }

                // ✅ TÍNH GIẢM GIÁ SAU KHI CHECK
                BigDecimal giam = gg.getGiaTriGiam();

                if (giam.compareTo(tongTien) > 0) {
                    giam = tongTien;
                }

                thanhTien = tongTien.subtract(giam);
            }
        }
        // 🔥 LẤY NHÂN VIÊN THEO USER
        Nguoidung user = nguoidungService.getUserByUsername(username);

        Nhanvien nv = user.getNhanvien(); // 👈 QUAN TRỌNG 
        if (nv != null) {
            dh.setMaNhanVien(nv);
        }
        // ❗ 6. Tạo hóa đơn
        Hoadon hd = new Hoadon();
        hd.setMaDonHang(dh);
        hd.setTongTien(tongTien);
        hd.setThanhTien(thanhTien);
        hd.setThoiGianThanhToan(new Date());

        if (gg != null) {
            hd.setMaGiamGia(gg);
        }

        hoadonRepository.save(hd);

        // ❗ 7. Update đơn hàng
        dh.setTrangThai("HOAN_THANH");
        donhangRepository.update(dh);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donhang> layTatCa() {
        return donhangRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Donhang timTheoId(Integer maDonHang) {
        Donhang dh = donhangRepository.findById(maDonHang);
        if (dh == null) {
            throw new RuntimeException("Khong tim thay don hang: " + maDonHang);
        }
        return dh;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donhang> layTheoMaBan(Integer maBan) {
        return donhangRepository.findByMaBan(maBan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donhang> layTheoTrangThai(String trangThai) {
        return donhangRepository.findByTrangThai(trangThai);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donhang> layTheoKhoangThoiGian(Date from, Date to) {
        return donhangRepository.findByThoiGianBetween(from, to);
    }

    @Override
    public Donhang them(Donhang donhang) {
        donhang.setTrangThai("DANG_XU_LY");
        donhang.setTongTien(BigDecimal.ZERO);
        donhang.setThoiGianBatDau(new Date());
        donhang.setGhiChu(donhang.getGhiChu() != null ? donhang.getGhiChu() : "");
        return donhangRepository.save(donhang);
    }

    @Override
    public void capNhat(Donhang donhang) {
        timTheoId(donhang.getMaDonHang());
        donhangRepository.update(donhang);
    }

    @Override
    public void capNhatTrangThai(Integer maDonHang, String trangThai) {
        Donhang dh = timTheoId(maDonHang);
        dh.setTrangThai(trangThai);
        donhangRepository.update(dh);
    }

    @Override
    public void capNhatTongTien(Integer maDonHang) {
        Donhang dh = timTheoId(maDonHang);
        List<Chitietdonhang> dsChiTiet
                = chitietdonhangService.layTheoMaDonHang(maDonHang);
        BigDecimal tong = dsChiTiet.stream()
                .map(ct -> ct.getGia().multiply(
                BigDecimal.valueOf(ct.getSoLuong())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dh.setTongTien(tong);
        donhangRepository.update(dh);
    }

    @Override
    public void xoa(Integer maDonHang) {
        timTheoId(maDonHang);
        donhangRepository.delete(maDonHang);
    }

    @Override
    public void capNhatTrangThaiTheoChiTiet(Integer maDonHang) {
        Donhang dh = timTheoId(maDonHang);

        List<Chitietdonhang> ds = chitietdonhangService.layTheoMaDonHang(maDonHang);

        boolean allDone = ds.stream()
                .allMatch(ct -> "HOAN_THANH".equals(ct.getTrangThai()));

        if (allDone && !ds.isEmpty()) {
            dh.setTrangThai("HOAN_THANH");
        } else {
            dh.setTrangThai("DANG_XU_LY");
        }

        donhangRepository.update(dh);
    }

    @Override
    public List<Donhang> findChuaThanhToan() {
        return donhangRepository.findChuaThanhToan();
    }

    @Override
    public List<Donhang> findDaThanhToan() {
        return donhangRepository.findDaThanhToan();
    }

    public List<Donhang> getOrdersByThanhToan(String status) {
        if ("paid".equals(status)) {
            return donhangRepository.findDaThanhToan();
        } else if ("unpaid".equals(status)) {
            return donhangRepository.findChuaThanhToan();
        }
        return donhangRepository.findAll();
    }
}
