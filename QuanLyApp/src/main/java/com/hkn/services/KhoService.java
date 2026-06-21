/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Kho;
import java.util.List;

public interface KhoService {
    List<Kho> layTatCa();
    Kho timTheoId(Integer maKho);
    List<Kho> layTheoSanPham(Integer maSanPham);
    List<Kho> laySapHetHan(int soNgayToi);
    int kiemTraSoLuongTon(Integer maSanPham);
    Kho nhapKho(Kho kho);
    void capNhatSoLuong(Integer maKho, int soLuongMoi);
    void xoa(Integer maKho);
}
