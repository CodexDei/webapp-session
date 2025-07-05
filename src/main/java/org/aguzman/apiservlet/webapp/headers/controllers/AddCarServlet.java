package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aguzman.apiservlet.webapp.headers.models.Cart;
import org.aguzman.apiservlet.webapp.headers.models.ItemCar;
import org.aguzman.apiservlet.webapp.headers.models.Product;
import org.aguzman.apiservlet.webapp.headers.services.ProductService;
import org.aguzman.apiservlet.webapp.headers.services.ProductServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/add-cart")
public class AddCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        ProductService service = new ProductServiceImpl();
        Optional<Product> optionalProduct = service.findById(id);
        if (optionalProduct.isPresent()){

            ItemCar item = new ItemCar(1, optionalProduct.get());
            HttpSession session = req.getSession();
            Cart cart;

            if (session.getAttribute("cart") == null){

                cart = new Cart();
                session.setAttribute("cart", cart);

            }else {
                cart = (Cart) session.getAttribute("cart");
            }
            cart.addItems(item);
        }
        resp.sendRedirect(req.getContextPath() + "/view-cart");
    }
}
