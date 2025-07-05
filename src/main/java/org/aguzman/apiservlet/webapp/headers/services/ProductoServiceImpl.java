package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Product;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductoService{
    @Override
    public List<Product> listar() {
        return Arrays.asList(new Product(1L, "notebook", "computacion", 175000),
                new Product(2L, "mesa escritorio", "oficina", 100000),
                new Product(3L, "teclado mecanico", "computacion", 40000));
    }
}
