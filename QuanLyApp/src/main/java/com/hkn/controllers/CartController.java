/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.pojo.Banan;
import com.hkn.pojo.Cart;
import com.hkn.pojo.CartItem;
import com.hkn.services.CartService;
import com.hkn.services.ChitietdonhangService;
import com.hkn.services.DonhangService;
import com.hkn.pojo.Donhang;
import com.hkn.pojo.Khachhang;
import com.hkn.pojo.Nguoidung;
import com.hkn.services.KhachhangService;
import com.hkn.services.NguoidungService;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private NguoidungService nguoidungService;

    @Autowired
    private DonhangService donhangService;

    @Autowired
    private ChitietdonhangService chitietdonhangService;

    @Autowired
    private KhachhangService khachhangService;

    @ModelAttribute("username")
    public String getUsername(Principal principal) {
        return (principal != null) ? principal.getName() : null;
    }

    @ModelAttribute("cart")
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    // Thêm vào giỏ
    @PostMapping("/cart/add")
    public String add(@RequestParam("maSanPham") Integer maSanPham,
            @RequestParam(value = "soLuong", defaultValue = "1") int soLuong,
            @ModelAttribute("cart") Cart cart) {
        System.out.println("ADD: " + maSanPham);
        cartService.addToCart(cart, maSanPham, soLuong);
        System.out.println("SIZE: " + cart.getSoLuong()); // debug
        return "redirect:/";
    }

    @PostMapping("/cart/update")
    public String update(@RequestParam("maSanPham") Integer maSanPham,
            @RequestParam("soLuong") int soLuong,
            @ModelAttribute("cart") Cart cart) {
        if (soLuong <= 0) {
            cartService.remove(cart, maSanPham);
        } else {
            cartService.update(cart, maSanPham, soLuong);
        }
        return "redirect:/";
    }

    // Xem giỏ
    @GetMapping("/cart")
    public String view(Model model,
            @ModelAttribute("cart") Cart cart) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    // Xóa
    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable("id") Integer id,
            @ModelAttribute("cart") Cart cart) {
        cartService.remove(cart, id);
        return "redirect:/cart";
    }

    // Checkout
    @PostMapping("/cart/checkout")
    public String checkout(HttpSession session, Principal principal,
            @RequestParam(value = "ghiChu", required = false) String ghiChu) {

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // 🔥 1. Lấy user đang login
        String username = principal.getName();
        Nguoidung user = nguoidungService.getUserByUsername(username);

        // 🔥 2. Lấy khách hàng
        Khachhang kh = khachhangService.getByUserId(user.getMaNguoiDung());

        // 🔥 3. Lấy bàn
        Banan ban = kh.getMaBan();

        // 🔥 4. Tạo đơn hàng
        Donhang dh = new Donhang();
        dh.setMaBan(ban);   // ⭐ QUAN TRỌNG
        dh.setGhiChu(ghiChu);
        dh = donhangService.them(dh);

        // 🔥 5. Lưu chi tiết đơn hàng
        for (CartItem item : cart.getItems().values()) {
            chitietdonhangService.themSanPhamVaoDon(
                    dh.getMaDonHang(),
                    item.getMaSanPham(),
                    item.getSoLuong()
            );
        }

        // 🔥 6. Cập nhật tổng tiền
        donhangService.capNhatTongTien(dh.getMaDonHang());

        // 🔥 7. Clear cart
        cart.getItems().clear();

        return "redirect:/?success=true";
    }

//    @PostMapping("/cart/checkout")
//    public String checkout(@ModelAttribute("cart") Cart cart) {
//
//        Donhang dh = new Donhang();
//        dh = donhangService.them(dh);
//
//        for (CartItem item : cart.getItems().values()) {
//            chitietdonhangService.themSanPhamVaoDon(
//                    dh.getMaDonHang(),
//                    item.getMaSanPham(),
//                    item.getSoLuong()
//            );
//        }
//
//        donhangService.capNhatTongTien(dh.getMaDonHang());
//
//        cart.getItems().clear();
//
//        return "redirect:/";
//    }
}
