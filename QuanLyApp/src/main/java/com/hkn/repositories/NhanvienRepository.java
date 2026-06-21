/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;


import com.hkn.pojo.Nguoidung;
import com.hkn.pojo.Nhanvien;
import java.util.List;

public interface NhanvienRepository {
//    List<Nhanvien> findAll();
//    Nhanvien findById(Integer maNhanVien);
//    Nhanvien findByMaNguoiDung(Integer maNguoiDung);
//    Nhanvien save(Nhanvien nhanvien);
//    void update(Nhanvien nhanvien);
//    void delete(Integer maNhanVien);
    //
    Nhanvien addNhanvien(Nhanvien nv);
    Nhanvien getByUser(Nguoidung u);
    void update(Nhanvien nv);

}

