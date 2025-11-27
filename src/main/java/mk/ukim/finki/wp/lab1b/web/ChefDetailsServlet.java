package mk.ukim.finki.wp.lab1b.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab1b.model.Chef;
import mk.ukim.finki.wp.lab1b.service.ChefService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "chefDetailsServlet",urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {
    public final ChefService chefService;
    public final SpringTemplateEngine springTemplateEngine;

    public ChefDetailsServlet(ChefService chefService, SpringTemplateEngine springTemplateEngine) {
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");
        Chef chef = chefService.addDishToChef(chefId,dishId);
        WebContext context = new WebContext(JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp));
        context.setVariable("chef",chef);
        springTemplateEngine.process("chefDetails.html",context,resp.getWriter());
    }
}
