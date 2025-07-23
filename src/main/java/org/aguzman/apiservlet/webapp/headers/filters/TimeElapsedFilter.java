package org.aguzman.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Logger;

@WebFilter("/*")
public class TimeElapsedFilter implements Filter {

    private final static Logger logger = Logger.getLogger("TimeElapsedFilter");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long end = System.currentTimeMillis();
        long result = end - start;

        logger.info(String.format("The elapsed time is %s",result));
        System.out.println(String.format("The elapsed time is %s",result));
    }
}
