/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Hoadon;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HoadonService {
    List<Hoadon> layTatCa();
    Hoadon timTheoId(Integer maHoaDon);
    Hoadon timTheoMaDonHang(Integer maDonHang);
    List<Hoadon> layLichSu(Date from, Date to);
    BigDecimal tinhDoanhThu(Date from, Date to);
    Hoadon taoHoaDon(Integer maDonHang, Integer maGiamGia);
}
