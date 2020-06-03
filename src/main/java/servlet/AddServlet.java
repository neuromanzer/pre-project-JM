package servlet;

import model.User;
import service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/add")
public class AddServlet extends HttpServlet {

    private final Service service = Service.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/user_form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        User user = new User(name, email, password, role);

        service.addUser(user);

        resp.setContentType("text/html;charset=UTF-8");
        List<User> users = service.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/admin_page.jsp");
        dispatcher.forward(req, resp);
    }
}
