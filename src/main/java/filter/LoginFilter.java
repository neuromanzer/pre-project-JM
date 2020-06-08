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

        HttpSession session = req.getSession();

        if (name != null && password != null) {
            User user = userServiceImpl.getByNamePassword(name, password);
            if (user != null) {
                session.setAttribute("id", user.getId());
                if (user.getRole().equalsIgnoreCase("admin")) {
                    resp.sendRedirect("/admin");
                    return;
                } else if (user.getRole().equalsIgnoreCase("user")) {
                    resp.sendRedirect("/user");
                    return;
                }
            }
        }
        resp.sendRedirect("/logout");
    }

    @Override
    public void destroy() {

    }
}
