/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Magiamgia;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MagiamgiaService {

    List<Magiamgia> layTatCa();

    Magiamgia timTheoId(Integer maGiamGia);

    List<Magiamgia> layConHieuLuc();


    BigDecimal tinhTienSauGiam(Integer maGiamGia, BigDecimal tongTien);

    Magiamgia them(Map<String, String> params);

    void capNhat(Map<String, String> params);
    List<Magiamgia> search(Map<String, String> params);
}
