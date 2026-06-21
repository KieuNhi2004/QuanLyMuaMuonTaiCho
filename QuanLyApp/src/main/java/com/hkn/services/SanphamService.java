/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Sanpham;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface SanphamService {
    List<Sanpham> getAllSanpham();
    List<Sanpham> getSanphamByLoai(Integer maLoai);
    Sanpham getSanphamById(Integer maSanPham);
    Sanpham addSanpham(Map<String, String> params, MultipartFile file);
    Sanpham addSanphamFromPhieuNhap(Map<String, String> params, MultipartFile file);
}