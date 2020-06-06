package filter;

import model.User;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user")
public class UserFilter implements Filter {

    private final UserServiceImpl userServiceImpl = UserServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        Long sessionId = (Long) session.getAttribute("id");

        if (sessionId == null) {
            resp.sendRedirect("/login");
        } else {
            User user = userServiceImpl.getById(sessionId);
            if (user.getRole().equalsIgnoreCase("user")) {
                filterChain.doFilter(req, resp);
            } else if (user.getRole().equalsIgnoreCase("admin")) {
                filterChain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
