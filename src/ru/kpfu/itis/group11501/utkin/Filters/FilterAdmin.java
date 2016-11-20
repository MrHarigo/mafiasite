package ru.kpfu.itis.group11501.utkin.Filters;

import ru.kpfu.itis.group11501.utkin.Helpers.CookieToSession;
import ru.kpfu.itis.group11501.utkin.Models.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 23.11.2016.
 */
public class FilterAdmin implements Filter {
    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (((HttpServletRequest) req).getSession().getAttribute("current_user") == null) {
            ((HttpServletRequest) req).getSession().setAttribute("current_user", CookieToSession.add(req));
            if (((HttpServletRequest) req).getSession().getAttribute("current_user") == null) {
                ((HttpServletResponse) resp).sendRedirect("/login");
                return;
            } else {

                User current_user = (User) ((HttpServletRequest) req).getSession().getAttribute("current_user");

                if (current_user.is_admin()) {
                    chain.doFilter(req, resp);
                    return;
                } else {
                    ((HttpServletResponse) resp).sendRedirect("/home");
                    return;
                }
            }
        } else {
            User current_user = (User) ((HttpServletRequest) req).getSession().getAttribute("current_user");

            if (current_user.is_admin()) {
                chain.doFilter(req, resp);
                return;
            } else {
                ((HttpServletResponse) resp).sendRedirect("/home");
                return;
            }
        }


    }

}
