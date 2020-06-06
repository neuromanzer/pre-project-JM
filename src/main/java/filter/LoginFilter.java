package filter;

import model.User;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = userServiceImpl.getUserByNamePassword(name, password);

        HttpSession session = req.getSession();

        Long sessionId = (Long) session.getAttribute("id");


        if (userServiceImpl.isExistUser(name, password)) {
            session.setAttribute("id", user.getId());
            if (userServiceImpl.isExistUser(name, password)) {
                if (userServiceImpl.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("admin")) {
                    resp.sendRedirect("/admin");
                } else if (userServiceImpl.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("user")) {
                    resp.sendRedirect("/user");
                }
            }
        }else  {
            resp.sendRedirect("/logout");
        }

/*

            if (sessionId == null) {
                session.setAttribute("id", user.getId());
                if (userServiceImpl.isExistUser(name, password)) {
                    if (userServiceImpl.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("admin")) {
                        resp.sendRedirect("/admin");
                    } else if (userServiceImpl.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("user")) {
                        resp.sendRedirect("/user");
                    }
                } else if (!userServiceImpl.isExistUser(name, password) || name == null || password == null) {
                    resp.sendRedirect("/login");
                }
            }
*/

    }

    @Override
    public void destroy() {

    }
}
