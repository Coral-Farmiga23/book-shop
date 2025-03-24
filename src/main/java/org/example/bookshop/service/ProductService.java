package org.example.bookshop.service;

import lombok.RequiredArgsConstructor;
import org.example.bookshop.dao.ProductDao;
import org.example.bookshop.entity.Product;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    @PostFilter(value = "filterObject.userName == authentication.name")
    public List<Product> listAllProductsByLoggedInUser() {
        return productDao.findAll();
    }

    public List<Product> listAllProductsByRegisterUser(String username) {
        return productDao.findProductByRegisterUser(username);
    }
}
