/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.pojo.Magiamgia;
import com.hkn.pojo.Nguoidung;
import com.hkn.services.MagiamgiaService;
import com.hkn.services.NguoidungService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MagiamgiaController {

    @Autowired
    private MagiamgiaService magiamgiaService;
    @Autowired
    private NguoidungService nguoidungService;

    @ModelAttribute
    public void addCurrentUser(Model model, Principal principal) {
        if (principal != null) {
            Nguoidung u = nguoidungService.getUserByUsername(principal.getName());
            model.addAttribute("currentUser", u);
        }
    }

    @GetMapping("/admin/coupons")
    public String quanLyMaGiamGia(
            @RequestParam Map<String, String> params,
            Model model) {

        List<Magiamgia> ds
                = magiamgiaService.search(params);

        model.addAttribute("coupons", ds);

        return "QuanLyMaGiamGia";
    }

    @PostMapping("/admin/coupons/create")
    public String createCoupon(
            @RequestParam Map<String, String> params
    ) {

        magiamgiaService.them(params);

        return "redirect:/admin/coupons";
    }

    @PostMapping("/admin/coupons/update")
    public String updateCoupon(
            @RequestParam Map<String, String> params
    ) {

        magiamgiaService.capNhat(params);

        return "redirect:/admin/coupons";
    }

}
