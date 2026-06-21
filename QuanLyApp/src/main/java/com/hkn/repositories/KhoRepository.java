/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Kho;
import java.util.Date;
import java.util.List;

public interface KhoRepository {
    List<Kho> findAll();
    Kho findById(Integer maKho);
    List<Kho> findByMaSanPham(Integer maSanPham);
    List<Kho> findSapHetHan(Date ngayKiemTra);
    Integer sumSoLuongTon(Integer maSanPham);
    Kho save(Kho kho);
    void update(Kho kho);
    void delete(Integer maKho);
}

