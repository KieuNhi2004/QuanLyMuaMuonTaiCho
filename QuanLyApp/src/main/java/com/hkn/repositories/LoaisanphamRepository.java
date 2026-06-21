/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;

import com.hkn.pojo.Loaisanpham;
import java.util.List;

public interface LoaisanphamRepository {
    List<Loaisanpham> findAll();
    Loaisanpham findById(Integer maLoai);
    List<Loaisanpham> findByNhom(String nhom);
    Loaisanpham save(Loaisanpham loai);
}
