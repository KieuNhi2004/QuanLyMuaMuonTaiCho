/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Chitietphieunhap;
import java.util.List;

public interface ChitietphieunhapService {

    List<Chitietphieunhap> layTatCa();

    Chitietphieunhap timTheoId(Integer maPhieuNhap, Integer maSanPham);

    List<Chitietphieunhap> layTheoPhieuNhap(Integer maPhieuNhap);

    List<Chitietphieunhap> layTheoSanPham(Integer maSanPham);

    Chitietphieunhap them(Chitietphieunhap chitietphieunhap);

    Chitietphieunhap capNhat(Chitietphieunhap chitietphieunhap);

    void xoa(Integer maPhieuNhap, Integer maSanPham);
}