package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.models.Category;
import org.aguzman.apiservlet.webapp.headers.models.Product;
import org.aguzman.apiservlet.webapp.headers.services.ProductService;
import org.aguzman.apiservlet.webapp.headers.services.ProductServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/products/form")
public class ProductFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductService service = new ProductServiceJdbcImpl(conn);
        req.setAttribute("categories", service.categoryList());
        req.getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductService service = new ProductServiceJdbcImpl(conn);
        String name = req.getParameter("name");

        Integer price;

        try{
            price = Integer.valueOf(req.getParameter("price"));
        }catch (NumberFormatException e){
            price = 0;
        }

        String sku = req.getParameter("sku");
        String dateStr = req.getParameter("registration_date");

        Long idCategory;

        try{

        idCategory = Long.valueOf(req.getParameter("category"));
        }catch (NumberFormatException e){
            idCategory = 0L;
        }

        //validacion de errores
        Map<String, String> errors = new HashMap<>();

        if (name == null || name.isBlank()){

            errors.put("name", "Name is required");
        }
        if (price.equals(0)){

            errors.put("price", "Invalid Price");
        }
        if (sku == null || sku.isBlank()){

            errors.put("sku", "Sku is required");
        }
        if (dateStr == null || dateStr.isBlank()){

            errors.put("registration_date", "Date Register is required");
        }
        if (idCategory.equals(0L)){

            errors.put("category", "Invalid category");
        }

        if(errors.isEmpty()){

            LocalDate date_register = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setSku(sku);
            product.setRegistrationDate(date_register);
            Category category = new Category();
            category.setId(idCategory);
            product.setCategory(category);

            service.save(product);

            resp.sendRedirect(req.getContextPath() + "/products");

        }else {
            req.setAttribute("errors", errors);
            doGet(req, resp);
        }
    }
}
