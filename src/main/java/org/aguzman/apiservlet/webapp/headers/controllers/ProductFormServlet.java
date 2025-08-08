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
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/products/form")
public class ProductFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        ProductService service = new ProductServiceJdbcImpl(conn);
        Long id;
        try{
            id = Long.valueOf(req.getParameter("id"));
        }catch (NumberFormatException e){
            id = 0L;
        }
        Product product = new Product();
        product.setCategory(new Category());
        if (id > 0){
            Optional<Product> optionalProduct = service.productFindById(id);
            if (optionalProduct.isPresent()){
                product = optionalProduct.get();
            }
        }
        req.setAttribute("categories", service.categoryList());
        req.setAttribute("product", product);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);


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
        } else if (sku.length() > 10) {
            errors.put("sku", "Sku most not exceed 10 characters");
        }

        if (dateStr == null || dateStr.isBlank()){

            errors.put("registration_date", "Date Register is required");
        }
        if (idCategory.equals(0L)){

            errors.put("category", "Invalid category");
        }

            LocalDate date_register;

            try {
                date_register = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }catch (DateTimeParseException e){
                date_register = null;
            }

            Long id;

            try{

                id = Long.valueOf(req.getParameter("id"));
            }catch (NumberFormatException e){
                id = 0L;
            }

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setSku(sku);
            product.setRegistrationDate(date_register);

            Category category = new Category();
            category.setId(idCategory);
            product.setCategory(category);

        if(errors.isEmpty()){

            service.save(product);

            resp.sendRedirect(req.getContextPath() + "/products");

        }else {
            req.setAttribute("errors", errors);
            req.setAttribute("categories", service.categoryList());
            req.setAttribute("product", product);
            getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp);
        }
    }
}
