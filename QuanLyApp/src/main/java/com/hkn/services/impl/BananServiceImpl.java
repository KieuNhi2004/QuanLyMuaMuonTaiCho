/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Banan;
import com.hkn.repositories.BananRepository;
import com.hkn.services.BananService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BananServiceImpl implements BananService {

    @Autowired
    private BananRepository bananRepo;

    @Override
    public Banan addBanan(Banan ban) {
        return bananRepo.addBanan(ban);
    }
}

