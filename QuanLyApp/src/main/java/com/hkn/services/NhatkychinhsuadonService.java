/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Donhang;
import com.hkn.pojo.Nhanvien;
import com.hkn.pojo.Nhatkychinhsuadon;
import java.util.Date;
import java.util.List;

public interface NhatkychinhsuadonService {

    void logUpdate(Integer maDonHang, String hanhDong, String noiDung, String username);

    List<Nhatkychinhsuadon> getByDonHang(Integer maDonHang);

    public List<Nhatkychinhsuadon> filterLogs(
            Integer maDonHang,
            Integer maNhanVien,
            String hanhDong,
            Date from,
            Date to);

}
