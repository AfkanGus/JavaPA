package vkaretko.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vkaretko.DBManager;
import vkaretko.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller servlet for user sign in.
 *
 * @author Karetko Victor.
 * @version 1.00.
 * @since 27.03.2017.
 */
public class SignInController extends HttpServlet {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SignInController.class);

    /**
     * Forward to Login.jsp.
     * @param req request from client to server.
     * @param resp response from server to client.
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = DBManager.getInstance().getUser(login, password);
        HttpSession session = req.getSession();
        if (user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}
