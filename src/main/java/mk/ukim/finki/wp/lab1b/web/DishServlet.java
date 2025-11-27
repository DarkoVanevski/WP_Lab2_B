package mk.ukim.finki.wp.lab1b.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1b.service.ChefService;
import mk.ukim.finki.wp.lab1b.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "dishServlet",urlPatterns = "/dish")
public class DishServlet extends HttpServlet {
    public final DishService dishService;
    public final ChefService chefService;
    public final SpringTemplateEngine springTemplateEngine;

    public DishServlet(DishService dishService, ChefService chefService, SpringTemplateEngine springTemplateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        var chef = chefService.findById(chefId);
        var dishes = dishService.listDishes();

        WebContext context = new WebContext(JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp));
        context.setVariable("chef",chef);
        context.setVariable("dishes",dishes);
        springTemplateEngine.process("dishesList.html",context,resp.getWriter());
    }
}
