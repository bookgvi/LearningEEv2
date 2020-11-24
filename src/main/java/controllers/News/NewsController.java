package controllers.News;

import Services.DataProviders.NewsDataProvider;
import Services.Utils.ParamsValidator;
import Services.Utils.PathParser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "News controller", urlPatterns = "/news/*")
public class NewsController extends HttpServlet {

  @Inject
  NewsDataProvider newsDP;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");

    String pathInfo = req.getPathInfo();
    String[] pathParams = PathParser.getUrlParams(pathInfo);
    String firstParam = PathParser.getFirstId(pathInfo);
    Integer firstId = ParamsValidator.id(firstParam);

    try {
      if (firstId != null)
        out.printf("%n%s%n", newsDP.findOne(firstId));
      else if(pathParams.length < 2)
        out.printf("%n%s%n", newsDP.findAll());
      else if(pathParams.length > 2 && pathParams.length < 5)
        getServletContext().getNamedDispatcher("articles of news").forward(req, resp);
      else out.write("{}");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
