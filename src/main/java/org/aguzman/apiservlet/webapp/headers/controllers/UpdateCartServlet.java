package org.aguzman.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.aguzman.apiservlet.webapp.headers.models.Cart;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/cart/update")
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("cart") != null){

            Cart cart = (Cart) session.getAttribute("cart");
            updateProducts(req,cart);
            updateQuantities(req,cart);
        }
        resp.sendRedirect(req.getContextPath() + "/cart/view");
    }

    private void updateQuantities(HttpServletRequest req, Cart cart) {

        String[] deleteProducts = req.getParameterValues("deleteProduct");

        if (deleteProducts != null && deleteProducts.length > 0){

            List<String> removeProducts = Arrays.asList(deleteProducts);
            cart.removeProducts(removeProducts);
        }
    }

    private void updateProducts(HttpServletRequest req, Cart cart) {

        Enumeration<String> enumeration = req.getParameterNames();

        while (enumeration.hasMoreElements()){

            String paranName = enumeration.nextElement();

            if (paranName.startsWith("quan_")){

                String id = paranName.substring(5);
                String quantity = req.getParameter(paranName);

                if (quantity != null){
                    cart.updateQuantity(id, Integer.parseInt(quantity));
                }
            }
        }
    }
}
