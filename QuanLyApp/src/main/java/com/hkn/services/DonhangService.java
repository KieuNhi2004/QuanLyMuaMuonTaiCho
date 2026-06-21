/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;


import com.hkn.pojo.Donhang;
import java.util.Date;
import java.util.List;

public interface DonhangService {
    List<Donhang> layTatCa();
    Donhang timTheoId(Integer maDonHang);
    List<Donhang> layTheoMaBan(Integer maBan);
    List<Donhang> layTheoTrangThai(String trangThai);
    List<Donhang> layTheoKhoangThoiGian(Date from, Date to);
    Donhang them(Donhang donhang);
    void capNhat(Donhang donhang);
    void capNhatTrangThai(Integer maDonHang, String trangThai);
    void capNhatTongTien(Integer maDonHang);
    void xoa(Integer maDonHang);
    public void capNhatTrangThaiTheoChiTiet(Integer maDonHang);
    public void thanhToan(Integer maDonHang, Integer maGiamGia, String username);
    List<Donhang> findChuaThanhToan();
    List<Donhang> findDaThanhToan();
    public List<Donhang> getOrdersByThanhToan(String status);
}

