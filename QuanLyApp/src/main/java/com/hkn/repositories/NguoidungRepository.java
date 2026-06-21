/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories;
import com.hkn.pojo.Nguoidung;
import java.util.List;
import java.util.Map;
//import java.util.List;

//public interface NguoidungRepository {
//    List<Nguoidung> findAll();
//    Nguoidung findById(Integer maNguoiDung);
//    Nguoidung findByTenDangNhap(String tenDangNhap);
//    List<Nguoidung> findByVaiTro(String vaiTro);
//    Nguoidung save(Nguoidung nguoidung);
//    void update(Nguoidung nguoidung);
//    void delete(Integer maNguoiDung);
//    boolean existsByTenDangNhap(String tenDangNhap);
//}
public interface NguoidungRepository {
    Nguoidung getUserByUsername(String username);
    Nguoidung addUser(Nguoidung u);
    boolean authenticate(String username, String password);
    List<Nguoidung> searchUsers(Map<String, String> params);
    List<Nguoidung> getAllUsers();
    Nguoidung getById(int id);
    void update(Nguoidung u);
}