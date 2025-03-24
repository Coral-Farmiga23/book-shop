package org.example.bookshop.ds;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CartItem implements Comparable<CartItem> {
    private int bookId;
    private String title;
    private int quantity;
    private double price;

    @Override
    public int compareTo(CartItem o) {
        return bookId - o.bookId;
    }
}
