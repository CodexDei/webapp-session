package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> toList();
    Optional<Product> findById(Long id);
}
