package ua.nure.samoylenko.web.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    private static Logger LOGGER = Logger.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        LOGGER.debug("Do get in servlet Logout start");
        HttpSession session = httpServletRequest.getSession();
        LOGGER.debug("Invalidate session");
        session.invalidate();
        LOGGER.debug("Trying to send redirect to startPage.jsp");
        httpServletResponse.sendRedirect("startPage.jsp");
    }
}
