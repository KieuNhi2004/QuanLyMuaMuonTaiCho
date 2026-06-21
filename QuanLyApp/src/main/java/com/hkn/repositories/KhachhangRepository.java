/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Khachhang;
import com.hkn.pojo.Nguoidung;
import java.util.List;

public interface KhachhangRepository {

    List<Khachhang> findAll();

    Khachhang findById(Integer id);

    Khachhang findByMaNguoiDung(Integer maNguoiDung); // 🔥 QUAN TRỌNG

    Khachhang addKhachhang(Khachhang kh);

    void update(Khachhang kh);

    void delete(Integer id);
    Khachhang getByUser(Nguoidung u);
    
}
