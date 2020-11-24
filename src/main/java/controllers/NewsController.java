package controllers;

import Services.DataProviders.NewsDataProvider;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "News controller", urlPatterns = "/news")
public class NewsController extends HttpServlet {

  @EJB
  NewsDataProvider newsDP;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("application/json");
    try {
      out.printf("%n%s%n", newsDP.findAll());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
