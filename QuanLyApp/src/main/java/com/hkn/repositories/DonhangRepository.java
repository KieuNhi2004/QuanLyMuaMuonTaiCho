/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;


import com.hkn.pojo.Donhang;
import java.util.Date;
import java.util.List;

public interface DonhangRepository {
    List<Donhang> findAll();
    Donhang findById(Integer maDonHang);
    List<Donhang> findByMaBan(Integer maBan);
    List<Donhang> findByTrangThai(String trangThai);
    List<Donhang> findByMaNhanVien(Integer maNhanVien);
    List<Donhang> findByThoiGianBetween(Date from, Date to);
    Donhang save(Donhang donhang);
    void update(Donhang donhang);
    void delete(Integer maDonHang);
    List<Donhang> findChuaThanhToan();
    List<Donhang> findDaThanhToan();
}

