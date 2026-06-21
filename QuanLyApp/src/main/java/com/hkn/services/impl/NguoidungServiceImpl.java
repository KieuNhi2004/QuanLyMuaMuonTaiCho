/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hkn.pojo.Nguoidung;
import com.hkn.repositories.NguoidungRepository;
import com.hkn.services.KhachhangService;
import com.hkn.services.NguoidungService;
import com.hkn.services.NhanvienService;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("userDetailsService")
public class NguoidungServiceImpl implements NguoidungService {

    @Autowired
    private NguoidungRepository nguoidungRepo;
    @Autowired
    private NhanvienService nhanvienService;
    @Autowired
    private KhachhangService khachhangService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Nguoidung getUserByUsername(String username) {
        return this.nguoidungRepo.getUserByUsername(username);
    }

    @Override
    public Nguoidung addUser(Map<String, String> params, MultipartFile avatar) {
        Nguoidung u = new Nguoidung();

        // ✅ FIX ĐÚNG FIELD THEO HTML
        String username = params.get("tenDangNhap");
        String password = params.get("matKhau");
        String role = params.get("vaiTro");

        // 🔥 DEBUG (nên giữ lúc test)
        System.out.println("USERNAME = " + username);
        System.out.println("PASSWORD = " + password);
        System.out.println("ROLE = " + role);

        // ❌ nếu thiếu dữ liệu thì không lưu
        if (username == null || password == null || role == null) {
            throw new RuntimeException("Thiếu dữ liệu đăng ký!");
        }

        u.setTenDangNhap(username);
        u.setMatKhau(passwordEncoder.encode(password));
        u.setTrangThai("ACTIVE"); // mặc định

        // ✅ luôn thêm ROLE_
        u.setVaiTro("ROLE_" + role);

        // ✅ upload ảnh (nếu có)
        if (avatar != null && !avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(
                        avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto")
                );
                u.setHinhAnh(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(NguoidungServiceImpl.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }

        return this.nguoidungRepo.addUser(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Nguoidung user = this.nguoidungRepo.getUserByUsername(username);

        if (!"ACTIVE".equals(user.getTrangThai())) {
            throw new UsernameNotFoundException("Tài khoản đã bị khóa!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getVaiTro()));

        return new org.springframework.security.core.userdetails.User(
                user.getTenDangNhap(),
                user.getMatKhau(),
                authorities
        );
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.nguoidungRepo.authenticate(username, password);
    }

    @Override
    public List<Nguoidung> searchUsers(Map<String, String> params) {
        return nguoidungRepo.searchUsers(params);
    }

    @Override
    public List<Nguoidung> getAllUsers() {
        return nguoidungRepo.getAllUsers();
    }

    @Override
    public Nguoidung getById(int id) {
        return nguoidungRepo.getById(id);
    }

    @Override
    public void update(Nguoidung u) {
        nguoidungRepo.update(u);
    }

    @Override
    public void updateUser(Map<String, String> params) {

        int id = Integer.parseInt(params.get("id"));
        String username = params.get("username");
        String trangThai = params.get("trangThai");
        String password = params.get("password");

        Nguoidung u = this.nguoidungRepo.getById(id);

        // ===== UPDATE USER =====
        u.setTenDangNhap(username);
        u.setTrangThai(trangThai);

        // 👉 xử lý password 
        if (password != null && !password.trim().isEmpty()) {
            u.setMatKhau(passwordEncoder.encode(password));
        }

        this.nguoidungRepo.update(u);

        String vaiTro = u.getVaiTro();

        // ===== NHÂN VIÊN =====
        if ("ROLE_NHANVIEN".equals(vaiTro)) {
            String hoTen = params.get("hoTen");
            if (hoTen != null) {
                nhanvienService.updateHoTen(u, hoTen);
            }
        }

        // ===== KHÁCH HÀNG =====
        if ("ROLE_KHACHHANG".equals(vaiTro)) {
            String tenBan = params.get("tenBan");
            String soCho = params.get("soCho");

            if (tenBan != null && soCho != null) {
                khachhangService.updateBan(u, tenBan, soCho);
            }
        }
    }
}
