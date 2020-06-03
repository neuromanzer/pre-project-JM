package servlet;

import model.User;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*    resp.setContentType("text/html;charset=UTF-8");

        final String name = req.getParameter("name");
        req.setAttribute("name", name);
        //resp.sendRedirect("user_page.jsp");
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_page.jsp");
        dispatcher.forward(req, resp);
*/
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("id");

        User user = service.getUserById(id);

        req.setAttribute("user", user);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("user_page.jsp");
        requestDispatcher.forward(req, resp);

    }
}