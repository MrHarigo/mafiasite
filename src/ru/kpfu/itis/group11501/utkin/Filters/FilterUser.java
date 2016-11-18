package ru.kpfu.itis.group11501.utkin.Filters;

import ru.kpfu.itis.group11501.utkin.Helpers.CookieToSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 15.11.2016.
 */
public class FilterUser implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) req).getSession().getAttribute("current_user") == null) {
            ((HttpServletRequest) req).getSession().setAttribute("current_user", CookieToSession.add(req));

            if (((HttpServletRequest) req).getSession().getAttribute("current_user") != null) {
                chain.doFilter(req, resp);
                return;
            } else {
                ((HttpServletResponse) resp).sendRedirect("/login");
                return;
            }
        } else
            chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
