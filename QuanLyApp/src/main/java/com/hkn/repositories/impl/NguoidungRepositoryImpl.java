/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Nguoidung;
import com.hkn.repositories.NguoidungRepository;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NguoidungRepositoryImpl implements NguoidungRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Nguoidung getUserByUsername(String username) {
        Session session = this.factory.getObject().getCurrentSession();

        Query<Nguoidung> q = session.createNamedQuery("Nguoidung.findByTenDangNhap", Nguoidung.class);
        q.setParameter("tenDangNhap", username);

        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null; // tránh crash nếu không tìm thấy
        }
    }

    @Override
    public Nguoidung addUser(Nguoidung u) {
        Session session = this.factory.getObject().getCurrentSession();

        session.persist(u);
        return u;
    }

    @Override
    public boolean authenticate(String username, String password) {
        Nguoidung u = this.getUserByUsername(username);

        if (u == null) {
            return false;
        }

        return this.passwordEncoder.matches(password, u.getMatKhau());
    }

    @Override
    public List<Nguoidung> searchUsers(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();

        StringBuilder hql = new StringBuilder("FROM Nguoidung n LEFT JOIN n.nhanvien nv WHERE 1=1");

        // username
        if (params.get("username") != null && !params.get("username").isEmpty()) {
            hql.append(" AND n.tenDangNhap LIKE :username");
        }

        // vai trò
        if (params.get("vaiTro") != null && !params.get("vaiTro").isEmpty()) {
            hql.append(" AND n.vaiTro = :vaiTro");
        }

        // trạng thái
        if (params.get("trangThai") != null && !params.get("trangThai").isEmpty()) {
            hql.append(" AND n.trangThai = :trangThai");
        }

        // tên nhân viên (JOIN)
        if (params.get("hoTen") != null && !params.get("hoTen").isEmpty()) {
            hql.append(" AND nv.hoTen LIKE :hoTen");
        }

        Query<Nguoidung> query = session.createQuery(hql.toString(), Nguoidung.class);

        // set param
        if (params.get("username") != null && !params.get("username").isEmpty()) {
            query.setParameter("username", "%" + params.get("username") + "%");
        }

        if (params.get("vaiTro") != null && !params.get("vaiTro").isEmpty()) {
            query.setParameter("vaiTro", "ROLE_" + params.get("vaiTro"));
        }

        if (params.get("trangThai") != null && !params.get("trangThai").isEmpty()) {
            query.setParameter("trangThai", params.get("trangThai"));
        }

        if (params.get("hoTen") != null && !params.get("hoTen").isEmpty()) {
            query.setParameter("hoTen", "%" + params.get("hoTen") + "%");
        }

        return query.getResultList();
    }

    @Override
    public List<Nguoidung> getAllUsers() {
        Session session = factory.getObject().getCurrentSession();
        return session.createQuery("FROM Nguoidung", Nguoidung.class)
                .getResultList();
    }

    @Override
    public Nguoidung getById(int id) {
        Session session = factory.getObject().getCurrentSession();
        return session.get(Nguoidung.class, id);
    }

    @Override
    public void update(Nguoidung u) {
        Session session = factory.getObject().getCurrentSession();
        session.update(u);
    }
}
