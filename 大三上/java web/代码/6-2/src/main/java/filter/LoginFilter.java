package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="/LoginFilter",urlPatterns="/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
//		System.out.println("LoginFilter被销毁");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		System.out.println("LoginFilter开始");
		HttpServletRequest hrequest=(HttpServletRequest) request;
		 HttpServletResponse hresponse = (HttpServletResponse) response;
		// 不过滤的uri
		String[] notFilter = new String[] {"index.jsp","LoginValidServlet"};
		// 请求的uri
		String uri = hrequest.getRequestURI();
		//不在notFilter中的才过滤，且为servlet和jsp
		if(uri.indexOf("Servlet")!=-1||uri.indexOf(".jsp")!=-1){
			boolean doFilter = true;// 是否过滤
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					// 如果uri中包含不过滤的uri，则不进行过滤
						doFilter = false;
						break;
				}
			}
			if (doFilter) {// 执行过滤
				Object obj = hrequest.getSession().getAttribute("user");//从session中获取user对象
				if (null == obj ) {
					//返回登陆页面
					hresponse.sendRedirect(hrequest.getContextPath() + "/index.jsp");
					return;
				}else{
					chain.doFilter(request, response);
				}
			}else {// 如果不执行过滤，则继续
					chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
//		System.out.println("LoginFilter结束");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("LoginFilter被调用");
	}

}
