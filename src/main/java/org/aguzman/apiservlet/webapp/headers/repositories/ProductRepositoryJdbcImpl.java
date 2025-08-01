package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Category;
import org.aguzman.apiservlet.webapp.headers.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryJdbcImpl implements Repository<Product> {

    private Connection conn;

    public ProductRepositoryJdbcImpl(Connection conn) {

        this.conn = conn;
    }


    @Override
    public List<Product> list() throws SQLException {

        List<Product> products = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre_categoria as categoria FROM productos as p " +
                     " INNER JOIN categorias as c ON (p.categoria_id = c.idcategorias)")) {
            while (rs.next()) {

                Product p = getProduct(rs);
                products.add(p);
            }
            return products;
        }
    }


    @Override
    public Product find(Long id) throws SQLException {

        Product product = null;

        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.nombre_categoria as categoria FROM productos as p " +
                " INNER JOIN categorias as c ON (p.categoria_id = c.idcategorias) WHERE p.idproductos = ?")) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    product = getProduct(rs);
                }
            }
            return product;
        }
    }

    @Override
    public void save(Product product) throws SQLException {

        String sql;

        if (product.getId() != null && product.getId() > 0) {

            sql = "UPDATE productos SET nombre=?, precio=?, categoria_id=?, sku=? WHERE idproductos=?";

        } else {

            sql = "INSERT INTO productos (nombre, precio, categoria_id, sku, fecha_registro) VALUES(?,?,?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getPrice());
            stmt.setLong(3, product.getCategory().getId());
            stmt.setString(4, product.getSku());

            if (product.getId() != null && product.getId() > 0) {
                stmt.setLong(5, product.getId());

            } else {
                stmt.setDate(5, Date.valueOf(product.getRegistrationDate()));
            }
            stmt.executeUpdate();
        }

    }

    @Override
    public void delete(Long id) throws SQLException {

        String sql = "DELETE FROM productos WHERE idproductos=?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1,id);
            stmt.executeUpdate();
        }
            }

    private static Product getProduct(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setId(rs.getLong("idproductos"));
        p.setName(rs.getString("nombre"));
        p.setPrice(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setRegistrationDate(rs.getDate("fecha_registro").toLocalDate());
        Category c = new Category();
        c.setId(rs.getLong("categoria_id"));
        c.setName(rs.getString("categoria"));
        p.setCategory(c);
        return p;
    }
}
