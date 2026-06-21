/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Magiamgia;
import com.hkn.repositories.MagiamgiaRepository;
import com.hkn.services.MagiamgiaService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MagiamgiaServiceImpl implements MagiamgiaService {

    @Autowired
    private MagiamgiaRepository magiamgiaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Magiamgia> layTatCa() {
        return magiamgiaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Magiamgia timTheoId(Integer maGiamGia) {
        Magiamgia mgg = magiamgiaRepository.findById(maGiamGia);
        if (mgg == null) {
            throw new RuntimeException("Khong tim thay ma giam gia: " + maGiamGia);
        }
        return mgg;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Magiamgia> layConHieuLuc() {
        return magiamgiaRepository.findConHieuLuc(new Date());
    }

    @Override
    public Magiamgia them(Map<String, String> params) {

        try {

            SimpleDateFormat sdf
                    = new SimpleDateFormat("yyyy-MM-dd");

            Magiamgia m = new Magiamgia();

            m.setGiaTriGiam(
                    new BigDecimal(params.get("giaTriGiam")));

            m.setDieuKien(
                    params.get("dieuKien"));

            m.setNgayBatDau(
                    sdf.parse(params.get("ngayBatDau")));

            m.setNgayKetThuc(
                    sdf.parse(params.get("ngayKetThuc")));

            return magiamgiaRepository.save(m);

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void capNhat(Map<String, String> params) {

        try {

            SimpleDateFormat sdf
                    = new SimpleDateFormat("yyyy-MM-dd");

            Integer id
                    = Integer.valueOf(params.get("maGiamGia"));

            Magiamgia m
                    = timTheoId(id);

            m.setGiaTriGiam(
                    new BigDecimal(params.get("giaTriGiam")));

            m.setDieuKien(
                    params.get("dieuKien"));

            m.setNgayBatDau(
                    sdf.parse(params.get("ngayBatDau")));

            m.setNgayKetThuc(
                    sdf.parse(params.get("ngayKetThuc")));

            magiamgiaRepository.update(m);

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal tinhTienSauGiam(Integer maGiamGia, BigDecimal tongTien) {
        Magiamgia mgg = timTheoId(maGiamGia);
        Date now = new Date();
        if (now.before(mgg.getNgayBatDau()) || now.after(mgg.getNgayKetThuc())) {
            throw new RuntimeException("Ma giam gia het han hoac chua co hieu luc");
        }
        return tongTien.subtract(mgg.getGiaTriGiam()).max(BigDecimal.ZERO);
    }

    @Override
    public List<Magiamgia> search(Map<String, String> params) {
        return magiamgiaRepository.search(params);
    }
}
