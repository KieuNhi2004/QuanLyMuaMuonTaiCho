/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Nhatkychinhsuadon;
import java.util.Date;
import java.util.List;

public interface NhatkychinhsuadonRepository {
    List<Nhatkychinhsuadon> findAll();
    Nhatkychinhsuadon findById(Integer maYeuCau);
    List<Nhatkychinhsuadon> findByMaDonHang(Integer maDonHang);
    List<Nhatkychinhsuadon> findByMaNhanVien(Integer maNhanVien);
    List<Nhatkychinhsuadon> findByHanhDong(String hanhDong);
    List<Nhatkychinhsuadon> findByThoiGianBetween(Date from, Date to);
    Nhatkychinhsuadon save(Nhatkychinhsuadon nhatky);
    public void delete(Integer id);
    public List<Nhatkychinhsuadon> filterLogs(
        Integer maDonHang,
        Integer maNhanVien,
        String hanhDong,
        Date from,
        Date to);
}
