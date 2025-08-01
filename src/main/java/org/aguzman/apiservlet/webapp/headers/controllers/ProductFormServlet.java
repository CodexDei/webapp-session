package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.services.ProductService;
import org.aguzman.apiservlet.webapp.headers.services.ProductServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;

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

    }
}
