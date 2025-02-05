package org.example.bookshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends IdClass{
    private String title;
    private double price;
    private int stock;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate yearPublished;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

    public Book(String title, double price, int stock, LocalDate yearPublished) {
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.yearPublished = yearPublished;
    }
}
