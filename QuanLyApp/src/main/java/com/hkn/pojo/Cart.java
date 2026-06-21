/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hkn.pojo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Integer, CartItem> items = new HashMap<>();

    public void addItem(Sanpham sp, int soLuong) {
        CartItem item = items.get(sp.getMaSanPham());

        if (item == null) {
            item = new CartItem();
            item.setMaSanPham(sp.getMaSanPham());
            item.setTenSanPham(sp.getTenSanPham());
            item.setGia(sp.getGia());
            item.setSoLuong(soLuong);
            item.setHinhAnh(sp.getHinhAnh());
        } else {
            item.setSoLuong(item.getSoLuong() + soLuong);
        }

        items.put(sp.getMaSanPham(), item);
    }

    public void removeItem(Integer id) {
        items.remove(id);
    }

    public void updateQuantity(Integer id, int soLuong) {
        if (items.containsKey(id)) {
            items.get(id).setSoLuong(soLuong);
        }
    }

    public BigDecimal getTongTien() {
        return items.values().stream()
                .map(i -> i.getGia().multiply(BigDecimal.valueOf(i.getSoLuong())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public int getSoLuong() {
        return items.values().stream().mapToInt(CartItem::getSoLuong).sum();
    }
}
