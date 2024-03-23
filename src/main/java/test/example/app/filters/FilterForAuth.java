package test.example.app.filters;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = { "/login", "/sign-up" })
public class FilterForAuth extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletReq, ServletResponse servletRes, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletReq;
        HttpServletResponse response = (HttpServletResponse) servletRes;
        String token = (String)request.getSession().getAttribute("token");

        if (token != null) {
            response.sendRedirect(request.getContextPath() + "/app");
        } else {
            chain.doFilter(request, response);
        }
    }
}
