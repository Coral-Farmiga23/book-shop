package org.example.bookshop.admin;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.AuthorDao;
import org.example.bookshop.dao.BookDao;
import org.example.bookshop.dao.CategoryDao;
import org.example.bookshop.entity.Author;
import org.example.bookshop.entity.Book;
import org.example.bookshop.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final CategoryDao categoryDao;

    public List<Category> findAllCategories() {
        return categoryDao.findAll();
    }
    
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }
    
    public void saveAuthor(Author author) {
        authorDao.save(author);
    }
    
    public void saveBook(Book book, int categoryId, int authorId) {
        Category category = getCategory(categoryId);
        Author author = getAuthor(authorId);
        category.addBook(book);
        author.addBook(book);
        bookDao.save(book);
    }

    private Author getAuthor(int authorId) {
        return authorDao.findById(authorId)
                .get();
    }

    private Category getCategory(int categoryId) {
        return categoryDao.findById(categoryId).get();
    }
}
