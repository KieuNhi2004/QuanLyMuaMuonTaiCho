/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;


import com.hkn.pojo.Chitietdonhang;
import com.hkn.pojo.ChitietdonhangPK;
import java.util.Date;
import java.util.List;

public interface ChitietdonhangRepository {
    List<Chitietdonhang> findAll();
    Chitietdonhang findById(ChitietdonhangPK id);
    List<Chitietdonhang> findByMaDonHang(Integer maDonHang);
    List<Chitietdonhang> findByTrangThai(String trangThai);
    Chitietdonhang save(Chitietdonhang chitiet);
    void update(Chitietdonhang chitiet);
    void delete(ChitietdonhangPK id);
    void deleteByDonHangAndSanPham(Integer maDonHang, Integer maSanPham);
    boolean existsByMaDonHang(Integer maDonHang);
   
}

