/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;


import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import java.util.List;
import java.util.Map;

public interface NhanvienService {
//    List<Nhanvien> layTatCa();
//    Nhanvien timTheoId(Integer maNhanVien);
//    Nhanvien timTheoMaNguoiDung(Integer maNguoiDung);
//    Nhanvien them(Nhanvien nhanvien);
//    void capNhat(Nhanvien nhanvien);
//    void xoa(Integer maNhanVien);
    Nhanvien addNhanvien(Nguoidung user, Map<String, String> params);
    void updateHoTen(Nguoidung u, String hoTen);
}

