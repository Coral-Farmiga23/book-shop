package org.example.bookshop.controller;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.admin.AdminService;
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
