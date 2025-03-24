package org.example.bookshop.event;

import lombok.Getter;
import lombok.Setter;
import org.example.bookshop.ds.CartItem;
import org.springframework.context.ApplicationEvent;

import java.util.Set;

@Getter
public class ProductEvent extends ApplicationEvent {

    private Set<CartItem> cartItems;
    private String registeredUser;

    public ProductEvent(Object source, Set<CartItem> cartItems, String registeredUser) {
        super(source);
        this.cartItems = cartItems;
        this.registeredUser = registeredUser;
    }
}
