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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    private final UserDaoClass userDaoClass = UserDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        User user = new User(id);

        userDaoClass.deleteUser(user);

        resp.setContentType("text/html;charset=UTF-8");
        List<User> users = userDaoClass.getAllUsers();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }
}
