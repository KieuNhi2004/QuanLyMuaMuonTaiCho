/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

//import com.hkn.pojo.Nguoidung;
//import java.util.List;
//import java.util.Map;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public interface NguoidungService {
//    List<Nguoidung> layTatCa();
//    Nguoidung timTheoId(Integer maNguoiDung);
//    Nguoidung timTheoTenDangNhap(String tenDangNhap);
//    List<Nguoidung> layTheoVaiTro(String vaiTro);
//    
//    Nguoidung taoTaiKhoan(Map<String, String> params);
//    void capNhatTaiKhoan(Integer maNguoiDung, Nguoidung nguoidung);
//    void doiMatKhau(Integer maNguoiDung, String matKhauCu, String matKhauMoi);
//    void xoa(Integer maNguoiDung);
//}

import com.hkn.pojo.Nguoidung;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface NguoidungService extends UserDetailsService {
    Nguoidung getUserByUsername(String username);
    Nguoidung addUser(Map<String, String> params, MultipartFile avatar);
    boolean authenticate(String username, String password);
    List<Nguoidung> searchUsers(Map<String, String> params);
    List<Nguoidung> getAllUsers();
    Nguoidung getById(int id);
    void update(Nguoidung u);
    void updateUser(Map<String, String> params);
}