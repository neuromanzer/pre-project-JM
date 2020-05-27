package servlet;

import dao.UserDaoClass;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private final UserDaoClass userDaoClass = UserDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("user_form.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(name, email, password);

        userDaoClass.addUser(user);
        resp.setContentType("text/html;charset=UTF-8");
        List<User> users = userDaoClass.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
