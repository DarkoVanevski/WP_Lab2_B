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
import java.util.List;

@WebServlet(name = "chefListServlet", urlPatterns = "/listChefs")
public class ChefListServlet extends HttpServlet {
    private final ChefService chefService;
    private final SpringTemplateEngine springTemplateEngine;

    public ChefListServlet(ChefService chefService, SpringTemplateEngine springTemplateEngine) {
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Chef> chefs = chefService.listChefs();
        WebContext context = new WebContext(JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req, resp));
        context.setVariable("chefs",chefs);
        springTemplateEngine.process("listChefs.html",context,resp.getWriter());
    }
}
