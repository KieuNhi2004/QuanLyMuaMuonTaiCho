/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;


import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.ChitietdonhangPK;
import java.math.BigDecimal;
import java.util.List;

public interface ChitietdonhangService {
    List<Chitietdonhang> layTatCa();
    Chitietdonhang timTheoId(ChitietdonhangPK id);
    List<Chitietdonhang> layTheoMaDonHang(Integer maDonHang);
    Chitietdonhang themSanPhamVaoDon(Integer maDonHang, Integer maSanPham, int soLuong);
    void capNhatSoLuong(Integer maDonHang, Integer maSanPham, int soLuong,String username);
    void capNhatTrangThai(Integer maDonHang, Integer maSanPham, String trangThai,String username);
    void xoaSanPhamKhoiDon(Integer maDonHang, Integer maSanPham,String username);
    boolean kiemTraConSanPhamChuaTra(Integer maDonHang);
    boolean conSanPham(Integer maDonHang);
    boolean coTheThanhToan(Integer maDonHang);
    BigDecimal tinhTongTien(Integer maDonHang);
}
