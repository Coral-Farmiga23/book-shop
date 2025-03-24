package org.example.bookshop.dao;

import org.example.bookshop.ds.BookDetailsInfo;
import org.example.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

    @Query("""
select b.price from Book b where b.id = ?1
""")
    double getPriceByBookId(int bookId);

    @Query("""
select new org.example.bookshop.ds.BookDetailsInfo(b.id, a.authorName, a.citizenShip, b.title, b.price, b.stock, b.imageUrl, c.categoryName)
 from Category c join c.books b join b.author a
""")
    List<BookDetailsInfo> findAllBookDetailsInfo();

    @Query("""
select new org.example.bookshop.ds.BookDetailsInfo(b.id, a.authorName, a.citizenShip, b.title, b.price, b.stock, b.imageUrl, c.categoryName)
 from Category c join c.books b join b.author a where b.id = ?1
""")
    Optional<BookDetailsInfo> findBookDetailsInfoById(int bookId);
}
