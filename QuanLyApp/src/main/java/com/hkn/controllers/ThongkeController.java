/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.services.ThongkeService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Calendar;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping("/stats")
public class ThongkeController {

    @Autowired
    private ThongkeService thongkeService;

    @GetMapping("")
    public String stats(
        @RequestParam(name = "from", required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,

        @RequestParam(name = "to", required = false)
        @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
         @RequestParam(name = "limit", defaultValue = "5") int limit,
        Model model) {

        if (from == null || to == null) {
            Calendar cal = Calendar.getInstance();
            Date now = new Date();

            cal.setTime(now);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            from = cal.getTime();

            cal.setTime(now);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            to = cal.getTime();
        }

        model.addAttribute("doanhThu", thongkeService.doanhThu(from, to));
        model.addAttribute("topSPDoanhThu", thongkeService.topSanPhamDoanhThu(from, to));
        model.addAttribute("topSP", thongkeService.topSanPhamBanNhieu(from, to));
        model.addAttribute("topNV", thongkeService.topNhanVien(from, to, limit));
        model.addAttribute("soHoaDon", thongkeService.countHoaDon(from, to));
        model.addAttribute("chartNgay", thongkeService.doanhThuTheoNgay(from, to));
        model.addAttribute("limit", limit);
        model.addAttribute("topSPMuon",
    thongkeService.topSanPhamMuon(from, to, limit));

model.addAttribute("topSPMuonDT",
    thongkeService.topSanPhamMuonDoanhThu(from, to, limit));
        return "thongket"; // tên file html
    }
}
