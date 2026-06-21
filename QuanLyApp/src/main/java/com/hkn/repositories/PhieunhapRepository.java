/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Phieunhap;
import java.util.List;

public interface PhieunhapRepository {
    List<Phieunhap> findAll();
    Phieunhap findById(Integer maPhieuNhap);
    List<Phieunhap> findByTrangThai(String trangThai);
    List<Phieunhap> findByMaNhanVien(Integer maNhanVien);
    Phieunhap save(Phieunhap phieunhap);
    void update(Phieunhap phieunhap);
    void delete(Integer maPhieuNhap);
}
