package controllers;

import Services.DataProviders.NewsDataProvider;
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

    Integer firstId = null;
    String pathInfo = req.getPathInfo();
    if (pathInfo != null) {
      firstId = PathParser.getFirstId(pathInfo);
    }


    try {
      if (firstId != null) out.printf("%n%s%n", newsDP.findOne(firstId));
      else out.printf("%n%s%n", newsDP.findAll());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
