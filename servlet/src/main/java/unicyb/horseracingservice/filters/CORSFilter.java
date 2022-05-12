package unicyb.horseracingservice.filters;

import unicyb.horseracingservice.service.AuthorizationService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//CORS filter
@WebFilter(urlPatterns = "/*")
public class CORSFilter implements Filter {

    private AuthorizationService authorizationService = new AuthorizationService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestOrigin = request.getHeader("Origin");
        response.addHeader("Access-Control-Allow-Origin", requestOrigin);
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods",
                "GET, OPTIONS, HEAD, PUT, POST, DELETE");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
