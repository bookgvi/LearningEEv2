package controllers;

import Services.DataProviders.NewsDataProvider;
import Services.Utils.ParamsValidator;
import Services.Utils.PathParser;

import javax.inject.Inject;
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
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");

    String pathInfo = req.getPathInfo();
    String[] pathParams = PathParser.getUrlParams(pathInfo);
    String firstParam = PathParser.getFirstId(pathInfo);
    Integer firstId = ParamsValidator.id(firstParam);

    try {
      System.out.printf("COntroller: %s%n", pathParams.length);
      System.out.printf("COntroller: %s%n", firstParam);
      if (firstId != null) out.printf("%n%s%n", newsDP.findOne(firstId));
      else if(pathParams.length < 2) out.printf("%n%s%n", newsDP.findAll());
      else out.write("{}");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
