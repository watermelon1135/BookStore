package com.nchu.bookstore.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CharacterEncodeingFilter implements Filter {
    private String encodeing;//配置文件中采用哪种编码方式?
    private boolean forecEncodeing;//是否要采取强制编码？

    public void init(FilterConfig config) throws ServletException {
        encodeing=config.getInitParameter("encodeing");
        forecEncodeing=Boolean.valueOf(config.getInitParameter("force"));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //类型转换
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //设置编码
        //① 是否有编码方式？
        //② 原本是否有编码？是否采取强制编码？

        if (hasLength(encodeing)
                &&(req.getCharacterEncoding()!=null||forecEncodeing)){
            req.setCharacterEncoding(encodeing);
        }
        //放行
        chain.doFilter(req, resp);
    }

    private boolean hasLength(String str){
        return str!=null&&!"".equals(str.trim());
    }
    public void destroy() {

    }

}
