package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aguzman.apiservlet.webapp.headers.models.Cart;
import org.aguzman.apiservlet.webapp.headers.models.ItemCart;
import org.aguzman.apiservlet.webapp.headers.models.Product;
import org.aguzman.apiservlet.webapp.headers.services.ProductService;
import org.aguzman.apiservlet.webapp.headers.services.ProductServiceImpl;
import org.aguzman.apiservlet.webapp.headers.services.ProductServiceJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/cart/add")
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        Connection conn = (Connection) req.getAttribute("conn");
        ProductService service = new ProductServiceJdbcImpl(conn);
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()){

            ItemCart item = new ItemCart(1, optionalProduct.get());
            HttpSession session = req.getSession();
            Cart cart= (Cart) session.getAttribute("cart");
            cart.addItems(item);
        }
        resp.sendRedirect(req.getContextPath() + "/cart/view");
    }
}
