/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Sanpham;
import java.util.List;

public interface SanphamRepository {
    List<Sanpham> findAll();
    List<Sanpham> findByLoai(Integer maLoai);
    Sanpham findById(Integer maSanPham);
    Sanpham save(Sanpham sanpham);
}

