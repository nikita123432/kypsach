package com.example.buysell.repositories;

import com.example.buysell.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findBySex(String sex);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
}
