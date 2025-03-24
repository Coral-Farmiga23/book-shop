package org.example.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.BookDao;
import org.example.bookshop.ds.BookDetailsInfo;
import org.example.bookshop.entity.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;

    public BookDetailsInfo getBookDetails(int bookId) {
        return bookDao.findBookDetailsInfoById(bookId).orElse(null);
    }

    public ResponseEntity<String> calculateStock(String bookTitle, int stock) {
        Optional<Book> bookOptional = bookDao.findByTitle(bookTitle);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            if (book.getStock() < stock) {
                return ResponseEntity.badRequest().body("Not enough stock");
            }
            int newStock = book.getStock() - stock;
            book.setStock(newStock);
            bookDao.saveAndFlush(book);
            return ResponseEntity.ok("Stock updated Successfully");
        }
        return ResponseEntity.notFound().build();
    }

    public List<BookDetailsInfo> listAllBookDetailsInfo() {
        return bookDao.findAllBookDetailsInfo();
    }

}
