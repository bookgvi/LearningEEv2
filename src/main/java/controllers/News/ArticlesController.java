package controllers.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "articles of news", urlPatterns = "/article")
public class ArticlesController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");

    String[] pathParams = new String[0];

    if (req.getAttribute("pathInfo") != null) {
      pathParams = (String[]) req.getAttribute("pathParams");
    }

    for (String pathParam: pathParams) {
      out.printf("Article controller: %s%n", pathParam);
    }
  }
}
