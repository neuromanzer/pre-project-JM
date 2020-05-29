package servlet;

import dao.UserHibernateDAO;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {

    //private final UserHibernateDAO userHibernateDAO = UserHibernateDAO.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        //User existingUser = userJdbcDAO.getUser(id);
        User existingUser = userService.getUser(id);
        req.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(id, name, email, password);

        //userJdbcDAO.updateUser(user);
        userService.updateUser(user);

        resp.setContentType("text/html;charset=UTF-8");
        //List<User> users = userJdbcDAO.getAllUsers();
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
