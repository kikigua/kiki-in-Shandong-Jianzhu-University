package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharEncodingFilter
 */
@WebFilter(filterName="/CharEncodingFilter",urlPatterns="/*")
public class CharEncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CharEncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
//		System.out.println("CharEncodingFilter被销毁");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		System.out.println("CharEncodingFilter开始");
		request.setCharacterEncoding("utf-8");
		
		chain.doFilter(request, response);
		
		response.setCharacterEncoding("utf-8");
//		System.out.println("CharEncodingFilter结束");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("CharEncodingFilter被调用");
	}

}
