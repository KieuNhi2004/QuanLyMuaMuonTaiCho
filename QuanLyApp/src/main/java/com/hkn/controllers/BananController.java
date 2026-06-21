/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.services.BananService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

///**
// *
// * @author Admin
// */
//@Controller
//@RequestMapping("/ban")
//public class BananController {
//
//    @Autowired
//    private BananService service;
//
//    @GetMapping
//    public String view(Model model) {
//        model.addAttribute("bans", service.layTatCa());
//        return "ban";
//    }
//
//    @GetMapping("/doi-trang-thai/{id}")
//    public String doiTrangThai(@PathVariable Integer id,
//                              @RequestParam String trangThai) {
//        service.capNhatTrangThai(id, trangThai);
//        return "redirect:/ban";
//    }
//}