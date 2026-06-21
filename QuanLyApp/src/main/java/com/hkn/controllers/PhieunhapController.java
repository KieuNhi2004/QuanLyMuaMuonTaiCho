package com.hkn.controllers;

import com.hkn.pojo.Nguoidung;
import com.hkn.services.*;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PhieunhapController {

    @Autowired
    private PhieunhapService phieunhapService;

    @Autowired
    private SanphamService sanphamService;

    @Autowired
    private NguoidungService nguoidungService;
    

    // ===== LẤY USER HIỆN TẠI =====
    @ModelAttribute
    public void addCurrentUser(Model model, Principal principal) {
        if (principal != null) {
            Nguoidung u = nguoidungService.getUserByUsername(principal.getName());
            model.addAttribute("currentUser", u);
        }
    }

    // ===== LIST =====
    @GetMapping("/phieunhap")
    public String list(Model model) {
        model.addAttribute("phieunhaps", phieunhapService.findAll());
        return "Kho";
    }

    // ===== FORM CREATE =====
    @GetMapping("/phieunhap/create")
    public String create(Model model) {
        model.addAttribute("sanphams", sanphamService.getAllSanpham());
        return "Phieunhap";
    }

    // ===== SAVE =====
    @PostMapping("/phieunhap/create")
    public String createPhieuNhap(@RequestParam Map<String, String> params,
        Principal principal) {

        Nguoidung u = nguoidungService.getUserByUsername(principal.getName());

    phieunhapService.taoPhieuNhap(params, u);

        return "redirect:/";
    }

    // ===== CONFIRM =====
    @GetMapping("/phieunhap/confirm/{id}")
    public String confirm(@PathVariable("id") Integer id) {

        phieunhapService.xacNhanPhieuNhap(id);

        return "redirect:/phieunhap";
    }
}