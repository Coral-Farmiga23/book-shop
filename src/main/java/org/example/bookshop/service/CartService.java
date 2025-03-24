package org.example.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.BookDao;
import org.example.bookshop.ds.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;
import java.util.stream.Collectors;

@SessionScope
@Service
@RequiredArgsConstructor
public class CartService {

    private final BookDao bookDao;
    private Set<CartItem> cartItems = new TreeSet<>();

    public void addToCart(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void removeFromCart(int id) {
        this.cartItems = cartItems.stream()
                .filter(item -> item.getBookId() != id)
                .collect(Collectors.toSet());
    }

    public void decreaseCartItemQuantity(int id) {
        double price = bookDao.getPriceByBookId(id);
       this.cartItems = cartItems.stream()
                .map(item -> {
                    if(item.getBookId() == id && item.getQuantity() > 1) {
                        item.setQuantity(item.getQuantity() - 1);
                        item.setPrice(item.getPrice() - price);
                    }
                    return item;
                }).collect(Collectors.toSet());
    }

    public void increaseCartItemQuantity(int id) {
        double price = bookDao.getPriceByBookId(id);
       this.cartItems = cartItems.stream()
                .map(item -> {
                    if(item.getBookId() == id) {
                        item.setQuantity(item.getQuantity() + 1);
                        item.setPrice(price * item.getQuantity());
                    }
                    return item;})
                .collect(Collectors.toSet());
    }

    public Set<CartItem> getAllCartItems() {
        return cartItems;
    }

    public int cartSize() {
        return cartItems.size();
    }

}
