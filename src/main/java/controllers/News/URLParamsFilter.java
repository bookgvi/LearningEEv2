package controllers.News;

import Services.Utils.PathParser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
  urlPatterns = "/news/*",
  dispatcherTypes = {DispatcherType.REQUEST}
)
public class URLParamsFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    String pathInfo;
    String[] pathParams;

    if (servletRequest instanceof HttpServletRequest) {
      HttpServletRequest request = (HttpServletRequest) servletRequest;
      pathInfo = request.getPathInfo();
      pathParams = PathParser.getUrlParams(pathInfo);

      if (servletRequest.getAttribute("pathInfo") == null) {
        servletRequest.setAttribute("pathInfo", pathInfo);
        servletRequest.setAttribute("pathParams", pathParams);
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
