package ErrorsHandlers;

import Services.JsonSerializer.SerializerDeserializer;
import com.sun.deploy.net.HttpResponse;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebFilter(
  filterName = "Error template",
  urlPatterns = "/*",
  dispatcherTypes = {DispatcherType.ERROR}
)
public class ErrorResponseTemplate implements Filter {

  @Inject
  SerializerDeserializer sd;

  /*
  *
  * "errors": [
  *     {
  *         "source": "/arithmex",
  *         "title": "/ by zero",
  *         "status": 400
  *     }
  * ]
  * */
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    PrintWriter writer = servletResponse.getWriter();

    filterChain.doFilter(servletRequest, servletResponse);

    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

    Exception exception = (Exception) servletRequest.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    String errorUrl = (String) servletRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    Integer statusCode = httpResponse.getStatus();

    HashMap<String, Object> responseMap = new HashMap<String, Object>();
    responseMap.put("status", statusCode);
    responseMap.put("source", errorUrl);
    responseMap.put("title", exception.getMessage());
    Object[] errArray = new Object[]{responseMap};
    String jsonResponse = this.sd.jsonSerialize(errArray, true);

    servletResponse.setContentType("application/json");
    writer.printf("%s%n", jsonResponse);
  }
}
