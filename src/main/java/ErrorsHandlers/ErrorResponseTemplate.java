package ErrorsHandlers;

import Services.JsonSerializer.SerializerDeserializer;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
  *  {
  *     "status": "422",
  *     "source": "/data/attributes/firstName",
  *     "title":  "Invalid Attribute"
  *   }
  * ]
  * */
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    PrintWriter writer = servletResponse.getWriter();

    filterChain.doFilter(servletRequest, servletResponse);

    Exception exception = (Exception) servletRequest.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    String errorUrl = (String) servletRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    Integer statusCode = (Integer) servletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

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
