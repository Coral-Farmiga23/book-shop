package org.example.bookshop.api.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.ds.BookDetailsInfo;
import org.example.bookshop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/details/{bookId}")
    public BookDetailsInfo getBookDetails(@PathVariable int bookId) {
        return bookService.getBookDetails(bookId);
    }

    record StockDto(String bookTitle, int stock) {}

    @PutMapping
    public ResponseEntity<String> calculateStock(@RequestBody StockDto stockDto) {
        return bookService.calculateStock(stockDto.bookTitle, stockDto.stock);
    }

    @GetMapping
    public List<BookDetailsInfo> listAllBookDetailsInfo() {
        return bookService.listAllBookDetailsInfo();
    }
}
