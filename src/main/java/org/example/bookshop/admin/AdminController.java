package org.example.bookshop.admin;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.entity.Author;
import org.example.bookshop.entity.Book;
import org.example.bookshop.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public String index() {
        return "admin/admin-index";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("authors", adminService.findAllAuthors());
        return "admin/authors";
    }

    @PostMapping("/authors")
    public String saveAuthor(Author author, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/authors";
        }
        adminService.saveAuthor(author);
        return "redirect:/admin/authors";
    }

    @PostMapping("/books")
    public String saveBook(Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/books";
        }
        adminService.saveBook(book, book.getCategory().getId(), book.getAuthor().getId());
        return "redirect:/admin/books";
    }

    @GetMapping("/books")
    public String listAllBooks(Model model) {
        model.addAttribute("categories", adminService.findAllCategories());
        model.addAttribute("authors", adminService.findAllAuthors());
        model.addAttribute("book", new Book());
        model.addAttribute("books", adminService.findAllBooks());
        return "admin/books";
    }

    @GetMapping("/list-category")
    public String listAllCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", adminService.findAllCategories());
        return "admin/categories";
    }

    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/categories";
        }
        adminService.saveCategory(category);
        return "redirect:/admin/list-category";
    }
}
