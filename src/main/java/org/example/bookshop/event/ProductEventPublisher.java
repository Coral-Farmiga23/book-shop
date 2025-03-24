package org.example.bookshop.event;

import org.example.bookshop.ds.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductEventPublisher {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void orderBook(Set<CartItem> cartItems, String registeredUser) {
        eventPublisher.publishEvent(new ProductEvent(this, cartItems, registeredUser));
    }
}
