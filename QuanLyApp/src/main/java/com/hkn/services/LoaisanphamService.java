/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Loaisanpham;
import java.util.List;

public interface LoaisanphamService {
    List<Loaisanpham> getAllLoai();
    Loaisanpham getLoaiById(Integer maLoai);
    List<Loaisanpham> getLoaisanphamByNhom(String nhom);
}