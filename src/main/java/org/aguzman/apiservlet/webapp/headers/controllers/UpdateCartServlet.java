package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.services.ProductService;
import org.aguzman.apiservlet.webapp.headers.services.ProductServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

        Long id = Long.parseLong(req.getParameter("id"));

        boolean delete = req.getParameter("delete") != null &&
                         req.getParameter("delete").contains("on");

        ProductService service = new ProductServiceImpl();

        if (delete){

            service.deleteById(id);
        }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view-cart");
    }
}
