package org.example.bookshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.BookDao;
import org.example.bookshop.ds.CartItem;
import org.example.bookshop.entity.Book;
import org.example.bookshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
    private final CartService cartService;
    private final BookDao bookDao;

    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("id") int id) {
        cartService.removeFromCart(id);
        return "redirect:/view-cart";
    }

    @GetMapping("/clear-cart")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/view-cart";
    }

    @GetMapping("/increase/item/{id}")
    public String increaseCartItemQuantity(@PathVariable("id") int id) {
        cartService.increaseCartItemQuantity(id);
        return "redirect:/view-cart";
    }

    @GetMapping("/decrease/item/{id}")
    public String decreaseCartItemQuantity(@PathVariable("id") int id) {
        cartService.decreaseCartItemQuantity(id);
        return "redirect:/view-cart";
    }

    @GetMapping("/view-cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getAllCartItems());
        return "cart";
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("books", bookDao.findAll());
        return "home";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") int bookId) {
        Book book = bookDao.findById(bookId).get();
        cartService.addToCart(toCartItem(book));
        return "redirect:/";
    }

    private CartItem toCartItem(Book book) {
        CartItem cartItem = new CartItem();
        cartItem.setBookId(book.getId());
        cartItem.setQuantity(1);
        cartItem.setPrice(book.getPrice());
        cartItem.setTitle(book.getTitle());
        return cartItem;
    }

    @ModelAttribute("cartSize")
    public int cartSize() {
        return cartService.cartSize();
    }
}
