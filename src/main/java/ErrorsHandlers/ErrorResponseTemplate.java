package ErrorsHandlers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(
  filterName = "Error template",
  urlPatterns = "/*",
  dispatcherTypes = {DispatcherType.ERROR}
)
public class ErrorResponseTemplate implements Filter {

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    filterChain.doFilter(servletRequest, servletResponse);
    Exception exception = (Exception) servletRequest.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    String errorUrl = (String) servletRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    PrintWriter writer = servletResponse.getWriter();
    writer.printf("%nException: %s%n", exception);
    writer.printf("Exception URL: %s%n", errorUrl);
  }
}
