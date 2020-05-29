package servlet;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    //private final UserJdbcDAO userJdbcDAO = UserJdbcDAO.getInstance();
    //private final UserHibernateDAO userHibernateDAO = UserHibernateDAO.getInstance();
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User user = new User(id);

        //userJdbcDAO.deleteUser(user);
        userService.deleteUser(user);

        resp.setContentType("text/html;charset=UTF-8");
        //List<User> users = userJdbcDAO.getAllUsers();
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
