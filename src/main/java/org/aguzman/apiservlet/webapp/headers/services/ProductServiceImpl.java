package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Category;
import org.aguzman.apiservlet.webapp.headers.models.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> productList() {
        return Arrays.asList(new Product(1L, "notebook", "computacion", 175000),
                new Product(2L, "mesa escritorio", "oficina", 100000),
                new Product(3L, "teclado mecanico", "computacion", 40000));
    }

    @Override
    public Optional<Product> productFindById(Long id) {

        return productList().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> categoryList() {
        return List.of();
    }

    @Override
    public Optional<Category> categoryFindById(Long id) {
        return Optional.empty();
    }
}
