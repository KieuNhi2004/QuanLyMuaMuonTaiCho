/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Chitietphieunhap;
import com.hkn.pojo.ChitietphieunhapPK;
import com.hkn.repositories.ChitietphieunhapRepository;
import com.hkn.services.ChitietphieunhapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChitietphieunhapServiceImpl implements ChitietphieunhapService {

    @Autowired
    private ChitietphieunhapRepository repo;

    @Override
    public List<Chitietphieunhap> layTatCa() {
        return repo.findAll();
    }

    @Override
    public Chitietphieunhap timTheoId(Integer maPhieuNhap, Integer maSanPham) {
        ChitietphieunhapPK pk = new ChitietphieunhapPK(maPhieuNhap, maSanPham);
        return repo.findById(pk);
    }

    @Override
    public List<Chitietphieunhap> layTheoPhieuNhap(Integer maPhieuNhap) {
        return repo.findByMaPhieuNhap(maPhieuNhap);
    }

    @Override
    public List<Chitietphieunhap> layTheoSanPham(Integer maSanPham) {
        return repo.findByMaSanPham(maSanPham);
    }

    @Override
    public Chitietphieunhap them(Chitietphieunhap chitietphieunhap) {
        return repo.save(chitietphieunhap);
    }

    @Override
    public Chitietphieunhap capNhat(Chitietphieunhap chitietphieunhap) {
        return repo.save(chitietphieunhap);
    }

    @Override
    public void xoa(Integer maPhieuNhap, Integer maSanPham) {
        ChitietphieunhapPK pk = new ChitietphieunhapPK(maPhieuNhap, maSanPham);
        repo.delete(pk);
    }
}
