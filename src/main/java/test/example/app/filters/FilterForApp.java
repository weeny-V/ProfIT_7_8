package test.example.app.filters;

import org.springframework.stereotype.Component;
import test.example.app.controllers.AuthController;
import test.example.app.controllers.MainController;

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
@WebFilter(urlPatterns = { "/*" })
public class FilterForApp extends HttpFilter {
    @Override
    public void doFilter(ServletRequest servletReq, ServletResponse servletRes, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletReq;
        HttpServletResponse response = (HttpServletResponse) servletRes;
        String token = (String)request.getSession().getAttribute("token");
        String url = request.getRequestURI().replace(request.getContextPath(), "");
        boolean isTrue = AuthController.AUTH_PAGES.contains(url);
        boolean iswerwer = MainController.APP_PAGES.contains(url);

        if (AuthController.AUTH_PAGES.contains(url) && token != null) {
            response.sendRedirect(request.getContextPath() + "/app");
        }
        if (MainController.APP_PAGES.contains(url) && token == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
