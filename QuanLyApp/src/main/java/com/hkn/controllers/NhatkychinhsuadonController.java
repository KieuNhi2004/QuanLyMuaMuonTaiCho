/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.controllers;

import com.hkn.pojo.Nhatkychinhsuadon;
import com.hkn.services.NhatkychinhsuadonService;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/logs")
public class NhatkychinhsuadonController {

    @Autowired
    private NhatkychinhsuadonService nhatkyService;

    @ModelAttribute("username")
    public String getUsername(Principal principal) {
        return (principal != null) ? principal.getName() : null;
    }

    @GetMapping("")
    public String viewLogs(
            @RequestParam(name = "maDonHang", required = false) Integer maDonHang,
            @RequestParam(name = "maNhanVien", required = false) Integer maNhanVien,
            @RequestParam(name = "hanhDong", required = false) String hanhDong,
            @RequestParam(name = "from", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            Model model) {

        List<Nhatkychinhsuadon> logs
                = nhatkyService.filterLogs(maDonHang, maNhanVien, hanhDong, from, to);

        // sort mới nhất
        logs = logs.stream()
                .sorted((a, b) -> b.getThoiGian().compareTo(a.getThoiGian()))
                .toList();

        model.addAttribute("logs", logs);

        return "nhatky";
    }
}
