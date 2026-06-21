/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Hoadon;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HoadonRepository {
    List<Hoadon> findAll();
    Hoadon findById(Integer maHoaDon);
    Hoadon findByMaDonHang(Integer maDonHang);
    List<Hoadon> findByThoiGianBetween(Date from, Date to);
    BigDecimal sumDoanhThu(Date from, Date to);
    Hoadon save(Hoadon hoadon);
    void update(Hoadon hoadon);
    
}

