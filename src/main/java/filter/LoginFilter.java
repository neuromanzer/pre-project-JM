package filter;

import model.User;
import service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/login")
public class LoginFilter implements Filter {

    private final Service service = Service.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = service.getUserByNamePassword(name, password);

        HttpSession session = req.getSession();

        Long sessionId = (Long) session.getAttribute("id");

        if (service.isExistUser(name, password)) {
            if (sessionId == null) {
                session.setAttribute("id", user.getId());
                if (service.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("admin")) {
                    resp.sendRedirect("/admin");
                } else if (service.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("user")) {
                    resp.sendRedirect("/user");
                }else {
                    out.println("No role");
                }
            } else if (sessionId == user.getId()) {
                if (service.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("admin")) {
                    resp.sendRedirect("/admin");
                } else if (service.getUserByNamePassword(name, password).getRole().equalsIgnoreCase("user")) {
                    resp.sendRedirect("/user");
                }else {
                    out.println("No role");
                }
            }
        } else {
            if (!service.isExistUser(name, password) || name == null || password == null) {
                RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
                rd.forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
