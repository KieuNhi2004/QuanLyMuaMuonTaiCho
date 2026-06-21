/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.services;

import com.hkn.pojo.Cart;

public interface CartService {
    void addToCart(Cart cart, Integer maSanPham, int soLuong);
    void remove(Cart cart, Integer maSanPham);
    void update(Cart cart, Integer maSanPham, int soLuong);
    void clear(Cart cart);
}
