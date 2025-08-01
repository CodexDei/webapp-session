package org.aguzman.apiservlet.webapp.headers.services;

import org.aguzman.apiservlet.webapp.headers.models.Category;
import org.aguzman.apiservlet.webapp.headers.models.Product;
import org.aguzman.apiservlet.webapp.headers.repositories.CategoryRepositoryImpl;
import org.aguzman.apiservlet.webapp.headers.repositories.ProductRepositoryJdbcImpl;
import org.aguzman.apiservlet.webapp.headers.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceJdbcImpl implements ProductService{

    private Repository<Product> repositoryJdbc;
    private Repository<Category> categoryRepositoryJdbc;

    public ProductServiceJdbcImpl(Connection connection) {

        this.repositoryJdbc = new ProductRepositoryJdbcImpl(connection);
        this.categoryRepositoryJdbc = new CategoryRepositoryImpl(connection);
    }

    @Override
    public List<Product> productList() {

        try {
            return repositoryJdbc.list();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Product> productFindById(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.find(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void save(Product product) {
        try {
            repositoryJdbc.save(product);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repositoryJdbc.delete(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }

    }

    @Override
    public List<Category> categoryList() {
        try {
            return categoryRepositoryJdbc.list();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }

    @Override
    public Optional<Category> categoryFindById(Long id) {
        try {
            return Optional.ofNullable(categoryRepositoryJdbc.find(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(),throwables.getCause());
        }
    }
}
