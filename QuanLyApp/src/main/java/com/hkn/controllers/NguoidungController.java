/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.pojo.Cart;
import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.Donhang;
import com.hkn.pojo.Loaisanpham;
import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Sanpham;
import com.hkn.repositories.NguoidungRepository;
import com.hkn.services.DonhangService;
import com.hkn.services.KhachhangService;
import com.hkn.services.LoaisanphamService;
import com.hkn.services.NguoidungService;
import com.hkn.services.NhanvienService;
import com.hkn.services.SanphamService;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class NguoidungController {

    @Autowired
    private NguoidungService nguoidungService;
    @Autowired
    private NguoidungRepository nguoidungRepo;
    @Autowired
    private NhanvienService nhanvienService;
    @Autowired
    private KhachhangService khachhangService;
    @Autowired
    private LoaisanphamService loaisanphamService;

    @Autowired
    private SanphamService sanphamService;
    @Autowired
    private DonhangService donhangService;

    @ModelAttribute
    public void addCurrentUser(Model model, Principal principal) {
        if (principal != null) {
            Nguoidung u = nguoidungService.getUserByUsername(principal.getName());
            model.addAttribute("currentUser", u);
        }
    }

    // ================= REGISTER =================
    @GetMapping("/register")
    public String registerView() {
        return "DangKy"; // trang JSP/HTML
    }

    @PostMapping("/register")
    public String registerHandler(
            @RequestParam Map<String, String> params,
            @RequestParam("avatar") MultipartFile avatar,
            Model model
    ) {
        try {
            // 1. Tạo tài khoản
            Nguoidung newUser = this.nguoidungService.addUser(params, avatar);

            // 2. Tạo bản ghi tương ứng theo vai trò
            String role = params.get("vaiTro");
            switch (role) {
                case "NHANVIEN":
                    this.nhanvienService.addNhanvien(newUser, params);
                    break;
                case "KHACHHANG":
                    this.khachhangService.addKhachhang(newUser, params); // ✅
                    break;
                // ADMIN không cần bảng riêng
            }

            return "redirect:/admin/users?success=true";

        } catch (Exception e) {
            model.addAttribute("errMsg", e.getMessage());
            return "DangKy";
        }
    }

    // ================= LOGIN =================
    @GetMapping("/login")
    public String loginView() {
        return "DangNhap";
    }

    // ================= HOME (sau login) =================
    @GetMapping("/")
    public String home(Model model, Principal principal, HttpSession session,
            @RequestParam(value = "nhom", required = false) String nhom) {
        if (principal != null) {
            String username = principal.getName();
            Nguoidung u = this.nguoidungService.getUserByUsername(username);
            model.addAttribute("currentUser", u);
            List<Loaisanpham> danhSachLoai = loaisanphamService.getAllLoai();
            model.addAttribute("danhSachLoai", danhSachLoai);

            // Lấy sản phẩm
            List<Sanpham> sanphams = sanphamService.getAllSanpham();
            danhSachLoai.forEach(l -> System.out.println(l.getTenLoai()));
            model.addAttribute("sanphams", sanphams);
            Set<String> nhoms = danhSachLoai.stream()
                    .map(Loaisanpham::getNhom)
                    .filter(Objects::nonNull)
                    .map(n -> n.trim().toUpperCase().replace(" ", "_")) // chuẩn hóa
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            model.addAttribute("nhoms", nhoms);
            if (nhom != null && !nhom.isEmpty()) {
                List<Loaisanpham> loaiTheoNhom = loaisanphamService.getLoaisanphamByNhom(nhom);
                model.addAttribute("loaiTheoNhom", loaiTheoNhom);

                model.addAttribute("nhomHienTai", nhom);
            }

            if (principal != null) {
                model.addAttribute("username", principal.getName());
            }
        }

        // ===== GIỎ HÀNG =====
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        model.addAttribute("cart", cart);
        Map<Integer, Integer> cartQty = new HashMap<>();

        if (cart != null && cart.getItems() != null) {
            cart.getItems().forEach((id, item) -> {
                cartQty.put(id, item.getSoLuong());
            });
        }

        model.addAttribute("cartQty", cartQty);

        return "TrangChu";
    }

    // ================= QUẢN LÍ NGƯỜI DÙNG =================
    @GetMapping("/admin/users")
    public String userManagement(@RequestParam(required = false) Map<String, String> params, Model model) {

        if (params == null) {
            params = new HashMap<>();
        }

        List<Nguoidung> users;

        boolean hasFilter = params.values().stream()
                .anyMatch(v -> v != null && !v.trim().isEmpty());

        if (!hasFilter) {
            users = nguoidungService.getAllUsers();
        } else {
            users = nguoidungService.searchUsers(params);
        }

        if ("true".equals(params.get("success"))) {
            model.addAttribute("msg", "Tạo người dùng thành công!");
        }

        model.addAttribute("users", users);
        model.addAttribute("params", params);

        return "QuanLyNguoiDung";
    }

    @GetMapping("/admin/products/new")
// @PreAuthorize("hasRole('ADMIN')")
    public String createProductView(Model model) {

        List<Loaisanpham> danhSachLoai
                = loaisanphamService.getAllLoai();

        Set<String> nhoms = danhSachLoai.stream()
                .map(Loaisanpham::getNhom)
                .filter(Objects::nonNull)
                .map(n -> n.trim().toUpperCase().replace(" ", "_"))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        model.addAttribute("nhoms", nhoms);

        
        model.addAttribute("danhSachLoai", danhSachLoai);

        return "TaoSanPham";
    }

    @PostMapping("/admin/products/new")
    public String createProductHandler(
            @RequestParam Map<String, String> params,
            @RequestParam("image") MultipartFile image,
            Model model) {

        try {

            sanphamService.addSanpham(params, image);

            return "redirect:/?successProduct=true";

        } catch (Exception e) {

            model.addAttribute("errMsg", e.getMessage());

            List<Loaisanpham> danhSachLoai
                    = loaisanphamService.getAllLoai();

            Set<String> nhoms = danhSachLoai.stream()
                    .map(Loaisanpham::getNhom)
                    .filter(Objects::nonNull)
                    .map(n -> n.trim().toUpperCase().replace(" ", "_"))
                    .collect(Collectors.toCollection(LinkedHashSet::new));

            model.addAttribute("nhoms", nhoms);

            
            model.addAttribute("danhSachLoai", danhSachLoai);

            return "TaoSanPham";
        }
    }
}
