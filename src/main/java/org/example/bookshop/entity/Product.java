package org.example.bookshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends IdClass{
    private String bookTitle;
    private int quantity;
    private double price;
    private String userName;
    private LocalDate dateCreated;

    public Product(String bookTitle, int quantity, double price, String userName, LocalDate dateCreated) {
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
        this.userName = userName;
        this.dateCreated = dateCreated;
    }
}
