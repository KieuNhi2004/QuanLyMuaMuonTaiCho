/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Loaisanpham;
import com.hkn.repositories.LoaisanphamRepository;
import com.hkn.services.LoaisanphamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoaisanphamServiceImpl implements LoaisanphamService {

    @Autowired
    private LoaisanphamRepository loaisanphamRepo;

    @Override
    public List<Loaisanpham> getAllLoai() {
        return loaisanphamRepo.findAll();
    }

    @Override
    public Loaisanpham getLoaiById(Integer maLoai) {
        return loaisanphamRepo.findById(maLoai);
    }
    @Override
    public List<Loaisanpham> getLoaisanphamByNhom(String nhom) {
        return loaisanphamRepo.findByNhom(nhom);
    }
  
}

