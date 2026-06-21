/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.repositories.impl;

import com.hkn.pojo.Banan;
import com.hkn.repositories.BananRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BananRepositoryImpl implements BananRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Banan addBanan(Banan ban) {
        Session session = factory.getObject().getCurrentSession();
        session.persist(ban);
        return ban;
    }
     @Override
    public void update(Banan b) {
        Session session = factory.getObject().getCurrentSession();
        session.update(b);
    }

}
