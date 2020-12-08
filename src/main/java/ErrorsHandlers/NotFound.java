package ErrorsHandlers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
  filterName = "Not Found handler",
  urlPatterns = "/QQQ",
  dispatcherTypes = {DispatcherType.ERROR}
)
public class NotFound implements Filter {
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    filterChain.doFilter(servletRequest, servletResponse);
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
    if (httpServletResponse.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
      httpServletResponse.sendRedirect("404.jsp");
    } else {
      filterChain.doFilter(servletRequest, servletResponse);
    }
  }
}
