package filter;

import model.User;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

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
            User user = userServiceImpl.getUserById(sessionId);
            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                filterChain.doFilter(req, resp);
            } else {
                resp.sendRedirect("/logout");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
