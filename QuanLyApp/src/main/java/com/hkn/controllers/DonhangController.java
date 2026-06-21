/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.Donhang;
import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhatkychinhsuadon;
import com.hkn.repositories.MagiamgiaRepository;
import com.hkn.repositories.NhatkychinhsuadonRepository;
import com.hkn.services.ChitietdonhangService;
import com.hkn.services.DonhangService;
import com.hkn.services.NguoidungService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/orders")
public class DonhangController {

    @Autowired
    private DonhangService donhangService;

    @Autowired
    private ChitietdonhangService chitietService;

    @Autowired
    private NhatkychinhsuadonRepository nhatkyRepo;
    @Autowired
    private MagiamgiaRepository magiamgiaRepository;

    @Autowired
    private NguoidungService nguoidungService;

    @ModelAttribute("username")
    public String getUsername(Principal principal) {
        return (principal != null) ? principal.getName() : null;
    }

    // ================= DANH SÁCH =================
    @GetMapping("")

    public String listOrders(Model model,
            @RequestParam(name = "trangThai", required = false) String trangThai,
            @RequestParam(name = "maBan", required = false) Integer maBan,
            @RequestParam(name = "time", required = false) String time,
            @RequestParam(value = "payStatus", required = false) String payStatus,
            Principal principal) {
        Nguoidung user = nguoidungService.getUserByUsername(principal.getName());
        List<Donhang> ds;

        if (user != null
                && "ROLE_NHANVIEN".equalsIgnoreCase(user.getVaiTro())) {

            // 🔥 Nhân viên: chỉ thấy đơn chưa có hóa đơn
            ds = donhangService.findChuaThanhToan();

        } else {
            // 🔥 Admin hoặc khác: thấy tất cả
            ds = donhangService.layTatCa();
        }

        // FILTER
        if (trangThai != null && !trangThai.isEmpty()) {
            ds = ds.stream()
                    .filter(d -> trangThai.equals(d.getTrangThai()))
                    .toList();
        }

        if (maBan != null) {
            ds = ds.stream()
                    .filter(d -> d.getMaBan() != null
                    && d.getMaBan().getMaBan().equals(maBan))
                    .toList();
        }
        // 🔥 LỌC THEO THANH TOÁN
        if (payStatus != null && !payStatus.isEmpty()) {
            if ("paid".equals(payStatus)) {
                // đã có hóa đơn
                ds = ds.stream()
                        .filter(d -> d.getHoadon() != null)
                        .toList();
            } else if ("unpaid".equals(payStatus)) {
                // chưa có hóa đơn
                ds = ds.stream()
                        .filter(d -> d.getHoadon() == null)
                        .toList();
            }
        }
        // lọc theo thời gian gần nhất
        if ("today".equals(time)) {
            Date now = new Date();
            ds = ds.stream()
                    .filter(d -> d.getThoiGianBatDau().after(
                    new Date(now.getTime() - 24 * 60 * 60 * 1000)))
                    .toList();
        }

        model.addAttribute("orders", ds);
        return "quanly-donhang";
    }

    // ================= CHI TIẾT =================
    @GetMapping("/{id}")
    public String orderDetail(@PathVariable(name = "id") Integer id, Model model) {
        Donhang dh = donhangService.timTheoId(id);
        boolean coTheThanhToan = chitietService.coTheThanhToan(id);
        List<Chitietdonhang> ct = chitietService.layTheoMaDonHang(id);
        // ✅ LẤY LOG
        List<Nhatkychinhsuadon> logs = nhatkyRepo.findByMaDonHang(id);
        model.addAttribute("order", dh);
        model.addAttribute("details", ct);
        model.addAttribute("logs", logs);
        model.addAttribute("coTheThanhToan", coTheThanhToan);
        model.addAttribute("giamGias",
                magiamgiaRepository.findConHieuLuc(new Date()));
        model.addAttribute("tongTien", chitietService.tinhTongTien(id));
        return "donhang-chitiet";
    }

    // ================= UPDATE =================
    @PostMapping("/update")
    public String updateOrder(
            @RequestParam(name = "maDonHang") Integer maDonHang,
            @RequestParam(name = "maSanPham") Integer maSanPham,
            @RequestParam(name = "soLuong") int soLuong,
            @RequestParam(name = "trangThai") String trangThai,
            Principal principal) {

        // cập nhật
        String username = principal.getName();
        chitietService.capNhatSoLuong(maDonHang, maSanPham, soLuong, username);
        chitietService.capNhatTrangThai(maDonHang, maSanPham, trangThai, username);
        return "redirect:/orders/" + maDonHang;
    }

    // ================= XÓA =================
    @GetMapping("/delete-item")
    public String deleteItem(
            @RequestParam(name = "maDonHang") Integer maDonHang,
            @RequestParam(name = "maSanPham") Integer maSanPham,
            Principal principal) {
        String username = principal.getName();
        chitietService.xoaSanPhamKhoiDon(maDonHang, maSanPham, username);

        if (!chitietService.conSanPham(maDonHang)) {
            return "redirect:/orders"; // quay về list
        }
        return "redirect:/orders/" + maDonHang;
    }

    @PostMapping("/pay")
    public String thanhToan(@RequestParam("maDonHang") Integer maDonHang,
            @RequestParam(value = "maGiamGia", required = false) Integer maGiamGia,
        Principal principal) {
        donhangService.thanhToan(maDonHang, maGiamGia, principal.getName());
        return "redirect:/orders";
    }
}
