package com.nchu.bookstore.domain;



import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> listItem= new ArrayList<>();

    public ShoppingCart(){

    }

    public ShoppingCart(List<CartItem> listItem) {
        this.listItem = listItem;
    }

    //计算购物车的总价
    public double getTotalPrice(){
        Double totalPrice = new Double(0);
        for (CartItem item:listItem) {
            totalPrice+=item.getBook_price()*item.getNumber();
        }
        String result = String .format("%.2f",totalPrice);
        totalPrice = Double.valueOf(result);
        return totalPrice;
    }

    //购物车中全部的商品
    public List<CartItem> getListItem(){
        return listItem;
    }

    //购物车中的商品数量
    public int getSize(){
        return getListItem().size();
    }
}
