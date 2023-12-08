package com.example.buysell.services;

import com.example.buysell.models.Product;
import com.example.buysell.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j

public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }
    @Transactional
    public void saveProduct(Product product) {
        log.info("Saving new{}", product);
        productRepository.save(product);
    }

    public Product findById(Long id){
        return productRepository.findById(id).get();
    }

    public List<Product> findBySex(String sex){
        return productRepository.findBySex(sex);
    }

    public List<Product> findBySexAndTitle(List<Product> sexProduct, String title) {
        List<Product> filteredProducts = new ArrayList<>();
        // Перебираем все продукты и фильтруем их по полу (sex) и названию (title)
        for (Product product : sexProduct) {
            if (product.getTitle().contains(title)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public List<Product> filterByCategory(List<Product> sexProduct, String category) {
        List<Product> filteredProducts = new ArrayList<>();
        // Перебираем все продукты и фильтруем их по полу (sex) и названию (title)
        for (Product product : sexProduct) {
            if (product.getCategory().name().equals(category)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }


    @Transactional
    public void deleteProduct(Long id) {

        for(String photoUrl: getProductById(id).getPhotoUrl()){
            Path path = Paths.get("D:\\kursach\\buysell\\src\\main\\resources\\static\\photos\\", photoUrl);
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}

//    public void saveProduct(Product product) {
//        productRepository.save(product);
//    }

//    public Product getProductById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }
//}
