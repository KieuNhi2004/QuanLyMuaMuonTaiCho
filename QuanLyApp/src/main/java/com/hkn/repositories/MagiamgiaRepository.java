/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Magiamgia;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MagiamgiaRepository {
    List<Magiamgia> findAll();
    Magiamgia findById(Integer maGiamGia);
    List<Magiamgia> findConHieuLuc(Date ngayHienTai);
    Magiamgia save(Magiamgia magiamgia);
    void update(Magiamgia magiamgia);
    void delete(Integer maGiamGia);
    List<Magiamgia> search(Map<String, String> params);
}
