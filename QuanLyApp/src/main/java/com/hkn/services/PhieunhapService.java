/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Chitietphieunhap;
import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Phieunhap;
import java.util.List;
import java.util.Map;

public interface PhieunhapService {
    void taoPhieuNhap(Map<String, String> params, Nguoidung user);
    void xacNhanPhieuNhap(Integer id);
    List<Phieunhap> findAll();
}

