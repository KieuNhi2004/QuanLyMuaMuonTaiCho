/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.pojo.Loaisanpham;
import com.hkn.pojo.Sanpham;
import com.hkn.services.LoaisanphamService;
import com.hkn.services.SanphamService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SanphamController {

    @Autowired
    private LoaisanphamService loaisanphamService;

    @Autowired
    private SanphamService sanphamService;

    @GetMapping("/sanpham")
    public String sanphamTheoLoai(@RequestParam("maLoai") Integer maLoai, Model model) {

        List<Sanpham> sanphams = sanphamService.getSanphamByLoai(maLoai);

        model.addAttribute("sanphams", sanphams);
        model.addAttribute("maLoai", maLoai);

        return "Sanpham"; // file Sanpham.html
    }

    @GetMapping("/admin/sanpham/create-from-phieunhap")
    public String createFromPhieuNhap() {
        return "Sanpham_From_PhieuNhap";
    }

    @PostMapping("/admin/sanpham/create-from-phieunhap")
    public String createSPFromPhieuNhap(
            @RequestParam Map<String, String> params,
            @RequestParam("file") MultipartFile file) {

        sanphamService.addSanphamFromPhieuNhap(params, file);

        // quay lại phiếu nhập
        return "redirect:/admin/phieunhap/create";
    }
}
