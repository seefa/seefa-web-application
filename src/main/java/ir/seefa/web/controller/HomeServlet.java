package ir.seefa.web.controller;

import ir.seefa.web.dto.CredentialRequestDto;
import ir.seefa.web.dto.Menu;
import ir.seefa.web.business.WebApplicationEJB;
import ir.seefa.web.dto.UserProfileResponseDto;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 01:48:56
 */
@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(HomeServlet.class);

    @EJB
    WebApplicationEJB businessService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("Post Home page...");
        logger.info("Post Home page...");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        CredentialRequestDto credential = new CredentialRequestDto();
        UserProfileResponseDto profileResponseDto = businessService.login(credential);
        if(profileResponseDto != null && profileResponseDto.getMainMenus().size() > 0) {
            List<Menu> mainMenus = businessService.getGuestMainMenus();


            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Seefa Menu Test</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Seefa Menus</h1>");
                out.println("<ul>");
                mainMenus.forEach(item -> {
                    out.println("<li><a title=\"" + item.getCode() + "\" href=\"" + item.getMenuUrl() + "\">" + item.getTitle() + "</a></li>");
                });
                out.println("</ul>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login failed</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Seefa Login Failed</h1>");
                out.println("<a href=\"/seefasite\">Return to login page</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("doGet method is started.");
        }

        logger.info("Get Home page...");

        if (logger.isDebugEnabled()) {
            logger.debug("doGet method process finished.");
        }

        List<Menu> mainMenus = businessService.getGuestMainMenus();
//        System.out.println("Get");


        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Seefa Menu Test</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Seefa Menus</h1>");
            out.println("<ul>");
            mainMenus.forEach(item -> {
                out.println("<li><a title=\"" + item.getCode() + "\" href=\"" + item.getMenuUrl() + "\">" + item.getTitle() + "</a></li>");
            });
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
