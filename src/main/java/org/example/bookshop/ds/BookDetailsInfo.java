package org.example.bookshop.ds;

public record BookDetailsInfo(int bookId,
                              String authorName,
                              String citizenShip,
                              String bookTitle,
                              double price,
                              int stock,
                              String imageUrl,
                              String categoryName) {
}
