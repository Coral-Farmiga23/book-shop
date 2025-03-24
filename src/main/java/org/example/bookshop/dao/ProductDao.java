package org.example.bookshop.dao;

import org.example.bookshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query("""
select p from Product p where p.userName =?1
""")
    List<Product> findProductByRegisterUser(String username);
}
