package controllers.Filters;

import Services.Utils.PathParser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
  urlPatterns = "/*",
  dispatcherTypes = {DispatcherType.REQUEST}
)
public class URLParamsFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    if (servletRequest instanceof HttpServletRequest) {
      if (servletRequest.getAttribute("pathInfo") == null) {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String pathInfo = request.getPathInfo();
        String[] pathParams = PathParser.getUrlParams(pathInfo);

        servletRequest.setAttribute("pathInfo", pathInfo);
        servletRequest.setAttribute("pathParams", pathParams);
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
