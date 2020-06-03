package filter;

import model.User;
import service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    private final Service service = Service.getInstance();

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
        } else if (sessionId != null) {
            User user = service.getUserById(sessionId);
            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                filterChain.doFilter(req,resp);
            } else if (user.getRole().equalsIgnoreCase("USER")) {
                resp.sendRedirect("/user");
            } else {
                resp.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
