package ua.nure.samoylenko.web.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ChangeLanguage")
public class ChangeLanguageServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String language = httpServletRequest.getParameter("language");

        if (language != null) {
            httpServletRequest.getSession().setAttribute("language", language);
        }
        httpServletResponse.sendRedirect("Enter");
    }
}
