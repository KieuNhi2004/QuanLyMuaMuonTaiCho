/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services.impl;

import com.hkn.pojo.Cart;
import com.hkn.pojo.Sanpham;
import com.hkn.services.CartService;
import com.hkn.services.SanphamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private SanphamService sanphamService;

    @Override
    public void addToCart(Cart cart, Integer maSanPham, int soLuong) {
        Sanpham sp = sanphamService.getSanphamById(maSanPham);
        cart.addItem(sp, soLuong);
    }

    @Override
    public void remove(Cart cart, Integer maSanPham) {
        cart.removeItem(maSanPham);
    }

    @Override
    public void update(Cart cart, Integer maSanPham, int soLuong) {
        cart.updateQuantity(maSanPham, soLuong);
    }

    @Override
    public void clear(Cart cart) {
        cart.getItems().clear();
    }
}
