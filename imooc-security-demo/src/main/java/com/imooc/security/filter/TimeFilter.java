package com.imooc.security.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Time Filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Time Filter start");
        long time = System.currentTimeMillis();
        chain.doFilter(request,response);
        System.out.println("time filter 耗时:"+ (System.currentTimeMillis()-time));
        System.out.println("Time Filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("Time Filter destroy");
    }
}
