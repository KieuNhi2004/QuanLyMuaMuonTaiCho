/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ThongkeService {
    double doanhThu(Date from, Date to);

    List<Map<String, Object>> topSanPhamDoanhThu(Date from, Date to);

    List<Map<String, Object>> topSanPhamBanNhieu(Date from, Date to);

    List<Map<String, Object>> topNhanVien(Date from, Date to, int limit);
    Long countHoaDon(Date from, Date to);
    List<Map<String, Object>> doanhThuTheoNgay(Date from, Date to);
    List<Map<String, Object>> topSanPhamMuon(Date from, Date to, int limit);
List<Map<String, Object>> topSanPhamMuonDoanhThu(Date from, Date to, int limit);
}
