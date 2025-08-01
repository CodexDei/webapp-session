package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Category;
import org.aguzman.apiservlet.webapp.headers.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> productList();
    Optional<Product> productFindById(Long id);

    void save(Product product);
    void delete(Long id);

    List<Category> categoryList();
    Optional<Category> categoryFindById(Long id);
}
