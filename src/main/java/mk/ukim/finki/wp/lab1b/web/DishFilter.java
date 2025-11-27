package mk.ukim.finki.wp.lab1b.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.model.Dish;

import java.io.IOException;
@WebFilter
public class DishFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        Dish dishId = (Dish) req.getSession().getAttribute("dishId");
        Chef chefId = (Chef) req.getSession().getAttribute("chefId");

        String path = req.getServletPath();
        if(chefId == null && path.equals("/dish") || dishId == null && path.equals("chefDetails")){
            resp.sendRedirect("/listChefs");
        } else {
            filterChain.doFilter(req,resp);
        }
    }
}
