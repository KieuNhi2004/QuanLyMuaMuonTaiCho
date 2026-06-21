/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Khachhang;
import com.hkn.pojo.Nguoidung;
import java.util.List;
import java.util.Map;

public interface KhachhangService {
//    List<Khachhang> layTatCa();
//    Khachhang timTheoId(Integer maKhachHang);
//    Khachhang timTheoMaBan(Integer maBan);
//    Khachhang them(Khachhang khachhang);
//    void capNhat(Khachhang khachhang);
//    void xoa(Integer maKhachHang);
    Khachhang addKhachhang(Nguoidung user, Map<String, String> params);
    Khachhang getByUserId(Integer maNguoiDung);
    void updateBan(Nguoidung u, String tenBan, String soCho);
}
