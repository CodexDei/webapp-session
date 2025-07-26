package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryJdbcImpl implements Repository<Product>{

    private Connection conn;

    public ProductRepositoryJdbcImpl(Connection conn){

        this.conn = conn;
    }


    @Override
    public List<Product> List() throws SQLException {

        List<Product> products = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre_categoria as categoria FROM productos as p " +
                    " INNER JOIN categorias as c ON (p.categoria_id = c.idcategorias)")){
            while (rs.next()){

                Product p = getProduct(rs);
                products.add(p);
            }
            return products;
        }
    }

    private static Product getProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getLong("idproductos"));
        p.setName(rs.getString("nombre"));
        p.setPrice(rs.getInt("precio"));
        p.setType(rs.getString("categoria"));
        return p;
    }

    @Override
    public Product find(Long id) throws SQLException {
        return null;
    }

    @Override
    public void save(Product product) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}
