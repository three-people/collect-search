package com.baseinfo.collect.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登陆拦截器
 */
public class LoginFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("login filter init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        String url=req.getRequestURL().toString();
        HttpSession session= req.getSession();
        Object obj = req.getSession().getAttribute("loginId");
        if(url.contains("login") || StringUtils.isNotBlank((String)obj) || url.endsWith("number1.jsp") || url.endsWith(".css") ||
                url.endsWith(".js")|| url.endsWith(".gif")|| url.endsWith(".png")|| url.endsWith(".jpg")||
                url.endsWith("SSH_market/")){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            logger.error("has no auth.....");
            ((HttpServletResponse) servletResponse).sendRedirect("/loginpage");
        }
    }

    @Override
    public void destroy() {

    }
}
