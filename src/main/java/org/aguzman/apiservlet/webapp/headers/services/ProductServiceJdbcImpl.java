package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Product;
import org.aguzman.apiservlet.webapp.headers.repositories.ProductRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceJdbcImpl implements ProductService{

    private ProductRepositoryJdbcImpl repositoryJdbc;

    public ProductServiceJdbcImpl(Connection connection) {

        repositoryJdbc = new ProductRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Product> toList() {

        try {
            return repositoryJdbc.list();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.find(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
}
