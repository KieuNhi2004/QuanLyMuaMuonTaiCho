/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Chitietphieunhap;
import com.hkn.pojo.ChitietphieunhapPK;
import java.util.List;

public interface ChitietphieunhapRepository {

    List<Chitietphieunhap> findAll();

    Chitietphieunhap findById(ChitietphieunhapPK id);

    List<Chitietphieunhap> findByMaPhieuNhap(Integer maPhieuNhap);

    List<Chitietphieunhap> findByMaSanPham(Integer maSanPham);

    Chitietphieunhap save(Chitietphieunhap chitiet);

    void delete(ChitietphieunhapPK id);
}
