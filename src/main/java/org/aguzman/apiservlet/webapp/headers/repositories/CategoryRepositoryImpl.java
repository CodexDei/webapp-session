package org.aguzman.apiservlet.webapp.headers.repositories;

import org.aguzman.apiservlet.webapp.headers.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements Repository<Category>{

    private Connection conn;

    public CategoryRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Category> list() throws SQLException {

        List<Category> categories = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias")){

            while (rs.next()){

                Category c = getCategory(rs);
                categories.add(c);
            }
        }
        return categories;
    }


    @Override
    public Category find(Long id) throws SQLException {

        Category category = null;

        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias WHERE idcategorias = ?")){

            stmt.setLong(1,id);

            try(ResultSet rs = stmt.executeQuery()) {

                if (rs.next()){

                    category = getCategory(rs);
                }
            }
        return category;
        }
    }

    @Override
    public void save(Category category) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    private Category getCategory(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getLong("idcategorias"));
        c.setName(rs.getString("nombre_categoria"));
        return c;
    }
}
