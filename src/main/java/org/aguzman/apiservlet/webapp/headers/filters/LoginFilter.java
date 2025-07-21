package org.aguzman.apiservlet.webapp.headers.filters;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aguzman.apiservlet.webapp.headers.services.LoginService;
import org.aguzman.apiservlet.webapp.headers.services.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;


//Este filtro se aplica a cualquier ruta que empiece con "cart"
@WebFilter({"/cart/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username = service.getUsername( (HttpServletRequest) servletRequest);
        if (username.isPresent()){
            filterChain.doFilter(servletRequest,servletResponse);

        }else {
            ( (HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Sorry, you are not authorized to access this page :(");
        }
    }
}
