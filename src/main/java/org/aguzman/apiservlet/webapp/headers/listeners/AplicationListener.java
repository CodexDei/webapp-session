package org.aguzman.apiservlet.webapp.headers.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.aguzman.apiservlet.webapp.headers.models.Cart;

@WebListener
public class AplicationListener implements ServletContextListener,
        ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        this.servletContext = sce.getServletContext();
        servletContext.log("Initializing the application");
        servletContext.setAttribute("messageApp","Some global value of the app");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        servletContext.log("Destroying the application");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //Aqui se puede hacer la conexion a la base de datos y otras cosas mas
        //por el momento solo un mensaje en consola como en los otros metodos
        servletContext.log("Initializing the request");
        ServletRequest request = sre.getServletRequest();
        request.setAttribute("messageReq","storing some value for the request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destroying the request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Initializing the session http");
        Cart cart = new Cart();
        HttpSession session = se.getSession();
        session.setAttribute("cart", cart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destroying the session http");
    }
}
